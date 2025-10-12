package br.com.alagouai.srv.controle.alagamentos.adapter.output.dto;


public class PredictionApiResponse {

    private Double risk;
    private String bucket;
    private String model;

    public PredictionApiResponse() {
    }

    public Double getRisk() {
        return risk;
    }

    public void setRisk(Double risk) {
        this.risk = risk;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
