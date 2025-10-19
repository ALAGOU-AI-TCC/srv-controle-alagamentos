package br.com.alagouai.srv.controle.alagamentos.port.input;

import br.com.alagouai.srv.controle.alagamentos.core.domain.model.DadosClimaticos;

public interface ObterDadosClimaticosInputPort {
    DadosClimaticos obterDadosClimaticos(String latitude, String longitude, String data);
}
