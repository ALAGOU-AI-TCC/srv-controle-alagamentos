package br.com.alagouai.srv.controle.alagamentos.adapter.output.repository.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "alagamento")
public class AlagamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double temperatura;
    private Integer umidade;
    private Integer pressao;
    private Double velocidadeVento;
    private Double precipitacaoChuva;
    private String tempoChuva;
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

    public String getTempoChuva() {
        return tempoChuva;
    }

    public void setTempoChuva(String tempoChuva) {
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
}
