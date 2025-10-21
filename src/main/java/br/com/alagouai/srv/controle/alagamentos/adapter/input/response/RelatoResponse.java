package br.com.alagouai.srv.controle.alagamentos.adapter.input.response;


import java.time.Instant;

public record RelatoResponse(
        Long id, double latitude, double longitude, String bairro,
        Instant instanteEvento, Instant instanteRecebimento
) {}