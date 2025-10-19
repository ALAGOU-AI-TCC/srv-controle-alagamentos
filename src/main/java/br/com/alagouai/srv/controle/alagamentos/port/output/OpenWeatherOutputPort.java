package br.com.alagouai.srv.controle.alagamentos.port.output;

import br.com.alagouai.srv.controle.alagamentos.core.domain.model.DadosClimaticos;

public interface OpenWeatherOutputPort {
    DadosClimaticos buscarDadosClimaticos(String latitude, String longitude, String dataHora);
}
