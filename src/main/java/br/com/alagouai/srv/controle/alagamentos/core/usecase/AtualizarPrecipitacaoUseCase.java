package br.com.alagouai.srv.controle.alagamentos.core.usecase;


import br.com.alagouai.srv.controle.alagamentos.core.domain.model.Alagamento;
import br.com.alagouai.srv.controle.alagamentos.port.input.AtualizarAlagamentoInputPort;
import br.com.alagouai.srv.controle.alagamentos.port.output.AlagamentosOutputPort;
import br.com.alagouai.srv.controle.alagamentos.port.output.LogControlOutputPort;

import java.util.List;

public class AtualizarPrecipitacaoUseCase implements AtualizarAlagamentoInputPort {

    private final LogControlOutputPort log;
    private final AlagamentosOutputPort alagamentosOutputPort;

    public AtualizarPrecipitacaoUseCase(LogControlOutputPort log, AlagamentosOutputPort alagamentosOutputPort) {
        this.log = log;
        this.alagamentosOutputPort = alagamentosOutputPort;
    }

    @Override
    public void atualizarPrecipitacaoAcumulada(Integer idControle, Integer limite) {
        log.logInfo("Iniciando processo de atualização da precipitacao acumulada e teempo de chuva para os registros a partir do Id {}", idControle);

        List<Alagamento> alagamentosList =  alagamentosOutputPort.buscarRegostrosPorIdControle(idControle, limite);
        alagamentosList.forEach(alagamento -> log.logInfo("Alagamento: {}", alagamento.toString()));
    }
}
