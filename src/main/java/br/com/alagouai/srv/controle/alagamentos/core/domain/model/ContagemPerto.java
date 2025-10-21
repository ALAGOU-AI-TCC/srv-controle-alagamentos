package br.com.alagouai.srv.controle.alagamentos.core.domain.model;

import java.time.Instant;

public record ContagemPerto(
        long totalRelatos,
        Long pessoasDistintas,
        Instant ultimoInstanteEvento
) {}
