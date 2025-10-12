package br.com.alagouai.srv.controle.alagamentos.adapter.input.controller;

import br.com.alagouai.srv.controle.alagamentos.adapter.input.request.PrevisaoRequest;
import br.com.alagouai.srv.controle.alagamentos.adapter.input.response.PrevisaoResponse;
import br.com.alagouai.srv.controle.alagamentos.adapter.mapper.PrevisaoMapper;
import br.com.alagouai.srv.controle.alagamentos.core.domain.model.Previsao;
import br.com.alagouai.srv.controle.alagamentos.port.input.AtualizarAlagamentoInputPort;
import br.com.alagouai.srv.controle.alagamentos.port.input.PreverAlagamentoInputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/alagamentos")
@RequiredArgsConstructor
public class AlagamentoController {

    private final AtualizarAlagamentoInputPort atualizarAlagamentoInputPort;
    private final PreverAlagamentoInputPort preverAlagamentoInputPort;
    private final PrevisaoMapper previsaoMapper;


    @PutMapping("/atualizar-precipitacao-acumulada-batch")
    public ResponseEntity<Void> atualizarPrecipitacaoAcumuladaBatch(@RequestParam Integer idControle, @RequestParam Integer limite) {

        atualizarAlagamentoInputPort.atualizarPrecipitacaoAcumulada(idControle, limite);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/predict")
    @ResponseStatus(HttpStatus.OK)
    public PrevisaoResponse prever(@RequestBody PrevisaoRequest request) {
        log.info("Iniciando previsão de alagamento...");
        Previsao previsao = previsaoMapper.toDomain(request);

        Previsao previsaoResponse = preverAlagamentoInputPort.prever(previsao);

        log.info("Previsão de alagamento finalizada.");
        return previsaoMapper.toResponse(previsaoResponse);
    }
}
