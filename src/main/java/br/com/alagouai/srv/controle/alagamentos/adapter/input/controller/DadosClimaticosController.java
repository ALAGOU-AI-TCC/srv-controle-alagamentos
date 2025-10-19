package br.com.alagouai.srv.controle.alagamentos.adapter.input.controller;

import br.com.alagouai.srv.controle.alagamentos.core.domain.model.DadosClimaticos;
import br.com.alagouai.srv.controle.alagamentos.port.input.ObterDadosClimaticosInputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/dadosclimaticos")
@RequiredArgsConstructor
public class DadosClimaticosController {

    private final ObterDadosClimaticosInputPort obterDadosClimaticosInputPort;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public DadosClimaticos obterTemperatura(            @RequestParam String latitude,
                                                        @RequestParam String longitude,
                                                        @RequestParam String data) {
        log.info("Obtendo dados climáticos...");


        DadosClimaticos response = obterDadosClimaticosInputPort.obterDadosClimaticos(latitude, longitude, data);

        log.info("Ohtenção de dados climáticos finalizada.");
        return response;
    }
}
