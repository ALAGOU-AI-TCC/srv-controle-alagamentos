package br.com.alagouai.srv.controle.alagamentos.core.domain.model;

import java.time.Instant;

public class RelatoAlagamento {
    private Long id;
    private double latitude;
    private double longitude;
    private String bairro;
    private Instant instanteEvento;
    private Instant instanteRecebimento;
    private String usuarioHash;

    public RelatoAlagamento() {
    }

    public RelatoAlagamento(Long id, double latitude, double longitude, String bairro, Instant instanteEvento, Instant instanteRecebimento, String usuarioHash) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.bairro = bairro;
        this.instanteEvento = instanteEvento;
        this.instanteRecebimento = instanteRecebimento;
        this.usuarioHash = usuarioHash;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Instant getInstanteEvento() {
        return instanteEvento;
    }

    public void setInstanteEvento(Instant instanteEvento) {
        this.instanteEvento = instanteEvento;
    }

    public Instant getInstanteRecebimento() {
        return instanteRecebimento;
    }

    public void setInstanteRecebimento(Instant instanteRecebimento) {
        this.instanteRecebimento = instanteRecebimento;
    }

    public String getUsuarioHash() {
        return usuarioHash;
    }

    public void setUsuarioHash(String usuarioHash) {
        this.usuarioHash = usuarioHash;
    }
}