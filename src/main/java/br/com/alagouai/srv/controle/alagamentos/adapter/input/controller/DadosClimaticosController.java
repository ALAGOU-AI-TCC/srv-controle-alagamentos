package br.com.alagouai.srv.controle.alagamentos.adapter.input.controller;

import br.com.alagouai.srv.controle.alagamentos.core.domain.model.DadosClimaticos;
import br.com.alagouai.srv.controle.alagamentos.port.input.ObterDadosClimaticosInputPort;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/dadosclimaticos")
@RequiredArgsConstructor
@Validated
public class DadosClimaticosController {

    private final ObterDadosClimaticosInputPort obterDadosClimaticosInputPort;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public DadosClimaticos obterTemperatura(
            @RequestParam
            @NotBlank(message = "latitude é obrigatória")
            @Pattern(
                    regexp = "^-?\\d{1,2}(\\.\\d+)?$|^-?90(\\.0+)?$",
                    message = "latitude deve ser numérica entre -90 e 90"
            )
            String latitude,

            @RequestParam
            @NotBlank(message = "longitude é obrigatória")
            @Pattern(
                    regexp = "^-?\\d{1,3}(\\.\\d+)?$|^-?180(\\.0+)?$",
                    message = "longitude deve ser numérica entre -180 e 180"
            )
            String longitude,

            @RequestParam(name = "data")
            @NotBlank(message = "data é obrigatória (formato yyyy-MM-dd HH:mm:ss)")
            @Pattern(
                    regexp = "^\\d{4}-\\d{2}-\\d{2}[ T]\\d{2}:\\d{2}:\\d{2}$",
                    message = "data deve seguir o formato yyyy-MM-dd HH:mm:ss (ex: 2023-11-28 20:46:00)"
            )
            String data
    ) {
        log.info("Obtendo dados climáticos...");
        DadosClimaticos response = obterDadosClimaticosInputPort.obterDadosClimaticos(latitude, longitude, data);
        log.info("Obtenção de dados climáticos finalizada.");
        return response;
    }
}
