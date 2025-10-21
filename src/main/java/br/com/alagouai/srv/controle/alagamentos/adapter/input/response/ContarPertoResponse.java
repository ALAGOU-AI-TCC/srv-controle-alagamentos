package br.com.alagouai.srv.controle.alagamentos.adapter.input.response;

import java.time.Instant;

public record ContarPertoResponse(
        double latitude, double longitude,
        int raioMetros, int desdeMinutos,
        long totalRelatos, Long pessoasDistintas,
        Instant ultimoInstanteEvento
) {}
