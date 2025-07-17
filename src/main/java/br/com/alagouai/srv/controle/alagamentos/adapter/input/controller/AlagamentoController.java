package br.com.alagouai.srv.controle.alagamentos.adapter.input.controller;

import br.com.alagouai.srv.controle.alagamentos.port.input.AtualizarAlagamentoInputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/alagamentos")
@RequiredArgsConstructor
public class AlagamentoController {

    private final AtualizarAlagamentoInputPort atualizarAlagamentoInputPort;


    @PutMapping("/atualizar-precipitacao-acumulada-batch")
    public ResponseEntity<Void> atualizarPrecipitacaoAcumuladaBatch(@RequestParam Integer idControle, @RequestParam Integer limite) {

        atualizarAlagamentoInputPort.atualizarPrecipitacaoAcumulada(idControle, limite);

        return ResponseEntity.ok().build();
    }
}
