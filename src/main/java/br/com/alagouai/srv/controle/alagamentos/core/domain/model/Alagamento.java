package br.com.alagouai.srv.controle.alagamentos.core.domain.model;

public class Alagamento {

        private Long id;
        private Double temperatura;
        private Integer umidade;
        private Integer pressao;
        private Double velocidadeVento;
        private Double precipitacaoChuva;
        private Integer tempoChuva;
        private String intensidadeChuva;
        private Double pontoOrvalho;
        private Double latitude;
        private Double longitude;
        private String dataHora;
        private Integer historicoAlagamento;
        private String solo;
        private String bairro;
        private Double precipitacaoDiaria;
        private Double precipitacaoAcumulada;

    public Alagamento() {
    }
    public Alagamento(Long id, Double temperatura, Integer umidade, Integer pressao, Double velocidadeVento, Double precipitacaoChuva, Integer tempoChuva, String intensidadeChuva, Double pontoOrvalho, Double latitude, Double longitude, String dataHora, Integer historicoAlagamento, String solo, String bairro, Double precipitacaoDiaria, Double precipitacaoAcumulada) {
        this.id = id;
        this.temperatura = temperatura;
        this.umidade = umidade;
        this.pressao = pressao;
        this.velocidadeVento = velocidadeVento;
        this.precipitacaoChuva = precipitacaoChuva;
        this.tempoChuva = tempoChuva;
        this.intensidadeChuva = intensidadeChuva;
        this.pontoOrvalho = pontoOrvalho;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dataHora = dataHora;
        this.historicoAlagamento = historicoAlagamento;
        this.solo = solo;
        this.bairro = bairro;
        this.precipitacaoDiaria = precipitacaoDiaria;
        this.precipitacaoAcumulada = precipitacaoAcumulada;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Integer getUmidade() {
        return umidade;
    }

    public void setUmidade(Integer umidade) {
        this.umidade = umidade;
    }

    public Integer getPressao() {
        return pressao;
    }

    public void setPressao(Integer pressao) {
        this.pressao = pressao;
    }

    public Double getVelocidadeVento() {
        return velocidadeVento;
    }

    public void setVelocidadeVento(Double velocidadeVento) {
        this.velocidadeVento = velocidadeVento;
    }

    public Double getPrecipitacaoChuva() {
        return precipitacaoChuva;
    }

    public void setPrecipitacaoChuva(Double precipitacaoChuva) {
        this.precipitacaoChuva = precipitacaoChuva;
    }

    public Integer getTempoChuva() {
        return tempoChuva;
    }

    public void setTempoChuva(Integer tempoChuva) {
        this.tempoChuva = tempoChuva;
    }

    public String getIntensidadeChuva() {
        return intensidadeChuva;
    }

    public void setIntensidadeChuva(String intensidadeChuva) {
        this.intensidadeChuva = intensidadeChuva;
    }

    public Double getPontoOrvalho() {
        return pontoOrvalho;
    }

    public void setPontoOrvalho(Double pontoOrvalho) {
        this.pontoOrvalho = pontoOrvalho;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public Integer getHistoricoAlagamento() {
        return historicoAlagamento;
    }

    public void setHistoricoAlagamento(Integer historicoAlagamento) {
        this.historicoAlagamento = historicoAlagamento;
    }

    public String getSolo() {
        return solo;
    }

    public void setSolo(String solo) {
        this.solo = solo;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Double getPrecipitacaoDiaria() {
        return precipitacaoDiaria;
    }

    public void setPrecipitacaoDiaria(Double precipitacaoDiaria) {
        this.precipitacaoDiaria = precipitacaoDiaria;
    }

    public Double getPrecipitacaoAcumulada() {
        return precipitacaoAcumulada;
    }

    public void setPrecipitacaoAcumulada(Double precipitacaoAcumulada) {
        this.precipitacaoAcumulada = precipitacaoAcumulada;
    }

    @Override
        public String toString() {
            return "Alagamento{" +
                    "temperatura=" + temperatura +
                    ", umidade=" + umidade +
                    ", pressao=" + pressao +
                    ", velocidadeVento=" + velocidadeVento +
                    ", precipitacaoChuva=" + precipitacaoChuva +
                    ", tempoChuva=\'" + tempoChuva + '\'' +
                    ", intensidadeChuva=\'" + intensidadeChuva + '\'' +
                    ", pontoOrvalho=" + pontoOrvalho +
                    ", latitude=" + latitude +
                    ", longitude=" + longitude +
                    ", dataHora=\'" + dataHora + '\'' +
                    ", historicoAlagamento=" + historicoAlagamento +
                    ", solo=\'" + solo + '\'' +
                    ", bairro=\'" + bairro + '\'' +
                    ", precipitacaoDiaria=" + precipitacaoDiaria +
                    ", precipitacaoAcumulada=" + precipitacaoAcumulada +
                    '}' + "\n";
        }
    }
