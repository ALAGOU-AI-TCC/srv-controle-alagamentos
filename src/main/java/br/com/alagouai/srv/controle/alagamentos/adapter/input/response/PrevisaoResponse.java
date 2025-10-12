package br.com.alagouai.srv.controle.alagamentos.adapter.input.response;

public class PrevisaoResponse {
    private Double risco;
    private String nivel;

    public PrevisaoResponse() {
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
