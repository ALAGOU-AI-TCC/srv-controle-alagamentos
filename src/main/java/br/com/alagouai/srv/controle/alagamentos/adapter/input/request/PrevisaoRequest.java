package br.com.alagouai.srv.controle.alagamentos.adapter.input.request;

public class PrevisaoRequest {

    private String latitude;
    private String longitude;
    private String time;

    public PrevisaoRequest() {
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
}
