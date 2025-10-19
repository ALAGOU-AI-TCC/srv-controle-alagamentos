# srv-controle-alagamentos

Microsserviço do **AlagouAI** que orquestra a coleta de **dados climáticos históricos (OpenWeather One Call – timemachine)** e chama a **Prediction API** do modelo de ML para estimar **risco de alagamento**. Expõe endpoints REST para previsão e para consulta de dados climáticos.

---

## Sumário
- [Arquitetura](#arquitetura)
- [Configuração](#configuração)
- [Endpoints (detalhados)](#endpoints-detalhados)
- [Exemplos (cURL)](#exemplos-curl)

---

## Arquitetura
Arquitetura **hexagonal (Ports & Adapters)**. O caso de uso central (`PreverAlagamentoUseCase`) recebe `lat/lon/epoch`, busca clima histórico no OpenWeather, mapeia para `DadosClimaticos` e envia para a **Prediction API**. O resultado retorna como `Previsao` (`risco`, `nivel`).

---

## Configuração
> **Recomendação:** manter **apenas a chave do OpenWeather como variável de ambiente**. As configurações de MySQL e rotas internas ficam no `application.yaml` (fora do versionamento de segredos).


### `application.yaml` (exemplo)
```yaml
server:
  port: 8080

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/alagouai?useSSL=false&serverTimezone=UTC
    username: root
    password: secret
  jpa:
    hibernate:
      ddl-auto: none  
    properties:
      hibernate:
        format_sql: true
  jackson:
    serialization:
      WRITE_DATES_AS_TIMESTAMPS: false

openweathermap:
  url: https://api.openweathermap.org
  key: ${OPENWEATHER_API_KEY}  # coloque sua chave aqui
  lang: pt_br
  units: metric

predictionapi:
  url: http://localhost:8000
  path-predict: /predict
```

> ⚠️ **Segurança:** não versionar o `application.yaml` com credenciais reais. Use `application-local.yaml` no `.gitignore`.

---

## Endpoints (detalhados)

### 1) `POST /predict` — Previsão de risco de alagamento
Retorna a probabilidade/nível de risco para um ponto geográfico e instante no tempo.

**Request body (`application/json`)**
```json
{
  "latitude": "-23.509",
  "longitude": "-46.612",
  "time": "2023-11-28 20:46:00"
}
```

**Validações**
- `latitude`: string/decimal no intervalo **[-90, 90]** (obrigatório)
- `longitude`: string/decimal no intervalo **[-180, 180]** (obrigatório)
- `time`: **sempre em formato yyyy-mm-dd hh:mm:ss**, obrigatório; deve referir-se a um timestamp suportado pela rota **Timemachine** do OpenWeather (histórico recente).

**Comportamento**
1. Normaliza `time` para UTC e chama **OpenWeather One Call (timemachine)**.
2. Converte o retorno em `DadosClimaticos` (temperatura, umidade, pressão, precipitação, vento, etc.).
3. Envia `DadosClimaticos` para a **Prediction API** (`POST {predictionapi.url}{path-predict}`) e recebe a previsão.
4. Responde com `risco` (0..1) e `nivel` (enum: `BAIXO`, `MEDIO`, `ALTO`).

**Response 200**
```json
{
  "risco": 0.90,
  "nivel": "alto"
}
```

**Códigos de status**
- `200 OK` – previsão calculada.
- `400 Bad Request` – validação de parâmetros falhou (lat/lon/time inválidos ou ausentes).
- `422 Unprocessable Entity` – timestamp fora da janela suportada pelo OpenWeather.
- `502 Bad Gateway` – erro ao consultar OpenWeather ou Prediction API.
- `504 Gateway Timeout` – tempo excedido em integrações externas.
- `500 Internal Server Error` – erro inesperado.

**Erros (payload padrão)**
```json
{
  "code": "VALIDATION_ERROR|UPSTREAM_ERROR|TIMEOUT|INTERNAL_ERROR",
  "message": "descrição curta",
  "details": {"campo": "motivo"}
}
```


---

### 2) `GET /dadosclimaticos` — Clima bruto (debug)
Retorna o payload climático bruto utilizado como entrada para o modelo.
**Query params**
- `latitude` (obrigatório)
- `longitude` (obrigatório)
- `data` (obrigatório) – **sempre em formato yyyy-mm-dd hh:mm:ss**, mesma semântica de `time` do `/predict`.

**Exemplo de resposta 200 (recortado)**
```json
{
  "temperatura": 22.02,
  "umidade": 91,
  "pressao": 1014,
  "velocidadeVento": 2.06,
  "precipitacaoChuva": 19.81,
  "intensidadeChuva": "chuva muito forte",
  "pontoOrvalho": 20.48,
  "dataHora": "2023-11-28 20:46:00"
}
```

**Códigos de status**
- `200 OK` – payload encontrado.
- `400 Bad Request` – parâmetros inválidos ou ausentes.
- `422 Unprocessable Entity` – timestamp fora da janela suportada.
- `502/504` – falhas/timeout em OpenWeather.

**Notas**
- Esse endpoint não chama a Prediction API; ele apenas retorna os dados climático.

---

## Exemplos (cURL)
```bash
# 1) Previsão de alagamento
curl -X POST "http://localhost:8080/predict" \
  -H "Content-Type: application/json" \
  -d '{
        "latitude": "-23.508373",
        "longitude": "-46.611794",
        "time": "2023-11-28 20:46:00"
      }'

# 2) Dados climáticos brutos
curl -G "http://localhost:8080/dadosclimaticos" \
  --data-urlencode "latitude=-23.508373" \
  --data-urlencode "longitude=-46.611794" \
  --data-urlencode "data=2023-11-28 20:46:00"
```

