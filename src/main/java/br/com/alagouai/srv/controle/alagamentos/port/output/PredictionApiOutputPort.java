package br.com.alagouai.srv.controle.alagamentos.port.output;

import br.com.alagouai.srv.controle.alagamentos.core.domain.model.Alagamento;
import br.com.alagouai.srv.controle.alagamentos.core.domain.model.Previsao;

public interface PredictionApiOutputPort {

    Previsao prever(Alagamento alagamentoAtual);
}
