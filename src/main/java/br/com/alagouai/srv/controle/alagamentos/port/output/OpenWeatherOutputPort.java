package br.com.alagouai.srv.controle.alagamentos.port.output;

import br.com.alagouai.srv.controle.alagamentos.core.domain.model.Alagamento;

public interface OpenWeatherOutputPort {
    Alagamento buscarDadosClimaticos(String latitude, String longitude, String dataHora);
}
