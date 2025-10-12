package br.com.alagouai.srv.controle.alagamentos.port.input;

import br.com.alagouai.srv.controle.alagamentos.core.domain.model.Previsao;

public interface PreverAlagamentoInputPort {

    Previsao prever(Previsao previsao);
}
