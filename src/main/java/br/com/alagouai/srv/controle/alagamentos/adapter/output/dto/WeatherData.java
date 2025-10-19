package br.com.alagouai.srv.controle.alagamentos.adapter.output.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class WeatherData {

    @JsonProperty("dt")
    private long dataHora;

    @JsonProperty("sunrise")
    private long nascerDoSol;

    @JsonProperty("sunset")
    private long porDoSol;

    @JsonProperty("temp")
    private double temperatura;

    @JsonProperty("feels_like")
    private double sensacaoTermica;

    @JsonProperty("pressure")
    private int pressao;

    @JsonProperty("humidity")
    private int umidade;

    @JsonProperty("dew_point")
    private double pontoOrvalho;

    @JsonProperty("clouds")
    private int nuvens;

    @JsonProperty("wind_speed")
    private double velocidadeVento;

    @JsonProperty("wind_deg")
    private int direcaoVento;

    @JsonProperty("weather")
    private List<CondicaoClima> condicoesClimaticas;

    @JsonProperty("rain")
    private Chuva chuva;

    public long getDataHora() {
        return dataHora;
    }

    public void setDataHora(long dataHora) {
        this.dataHora = dataHora;
    }

    public long getNascerDoSol() {
        return nascerDoSol;
    }

    public void setNascerDoSol(long nascerDoSol) {
        this.nascerDoSol = nascerDoSol;
    }

    public long getPorDoSol() {
        return porDoSol;
    }

    public void setPorDoSol(long porDoSol) {
        this.porDoSol = porDoSol;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getSensacaoTermica() {
        return sensacaoTermica;
    }

    public void setSensacaoTermica(double sensacaoTermica) {
        this.sensacaoTermica = sensacaoTermica;
    }

    public int getPressao() {
        return pressao;
    }

    public void setPressao(int pressao) {
        this.pressao = pressao;
    }

    public int getUmidade() {
        return umidade;
    }

    public void setUmidade(int umidade) {
        this.umidade = umidade;
    }

    public double getPontoOrvalho() {
        return pontoOrvalho;
    }

    public void setPontoOrvalho(double pontoOrvalho) {
        this.pontoOrvalho = pontoOrvalho;
    }

    public int getNuvens() {
        return nuvens;
    }

    public void setNuvens(int nuvens) {
        this.nuvens = nuvens;
    }

    public double getVelocidadeVento() {
        return velocidadeVento;
    }

    public void setVelocidadeVento(double velocidadeVento) {
        this.velocidadeVento = velocidadeVento;
    }

    public int getDirecaoVento() {
        return direcaoVento;
    }

    public void setDirecaoVento(int direcaoVento) {
        this.direcaoVento = direcaoVento;
    }

    public List<CondicaoClima> getCondicoesClimaticas() {
        return condicoesClimaticas;
    }

    public void setCondicoesClimaticas(List<CondicaoClima> condicoesClimaticas) {
        this.condicoesClimaticas = condicoesClimaticas;
    }

    public Chuva getChuva() {
        return chuva;
    }

    public void setChuva(Chuva chuva) {
        this.chuva = chuva;
    }
}

