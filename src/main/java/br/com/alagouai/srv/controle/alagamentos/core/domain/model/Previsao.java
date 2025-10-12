package br.com.alagouai.srv.controle.alagamentos.core.domain.model;

public class Previsao {

    private String latitude;
    private String longitude;
    private String time;
    private Double risco;
    private String nivel;

    public Previsao() {
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getRisco() {
        return risco;
    }

    public void setRisco(Double risco) {
        this.risco = risco;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}
