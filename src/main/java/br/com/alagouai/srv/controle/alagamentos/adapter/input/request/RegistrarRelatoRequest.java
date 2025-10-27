package br.com.alagouai.srv.controle.alagamentos.adapter.input.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegistrarRelatoRequest(
        @NotNull Double latitude,
        @NotNull Double longitude,
        @NotBlank String data_hora,
        @Size(max=120) String bairro
) {}
