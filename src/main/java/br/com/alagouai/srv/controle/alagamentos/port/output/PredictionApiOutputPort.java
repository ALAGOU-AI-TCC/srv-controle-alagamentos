package br.com.alagouai.srv.controle.alagamentos.port.output;

import br.com.alagouai.srv.controle.alagamentos.core.domain.model.DadosClimaticos;
import br.com.alagouai.srv.controle.alagamentos.core.domain.model.Previsao;

public interface PredictionApiOutputPort {

    Previsao prever(DadosClimaticos dadosClimaticosAtual);
}

