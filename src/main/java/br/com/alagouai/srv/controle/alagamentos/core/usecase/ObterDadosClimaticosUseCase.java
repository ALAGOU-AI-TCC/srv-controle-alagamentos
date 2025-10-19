package br.com.alagouai.srv.controle.alagamentos.core.usecase;

import br.com.alagouai.srv.controle.alagamentos.core.domain.model.DadosClimaticos;
import br.com.alagouai.srv.controle.alagamentos.port.input.ObterDadosClimaticosInputPort;
import br.com.alagouai.srv.controle.alagamentos.port.output.OpenWeatherOutputPort;

import static br.com.alagouai.srv.controle.alagamentos.utils.Utils.formatadorDataUnix;

public class ObterDadosClimaticosUseCase implements ObterDadosClimaticosInputPort {

    private final OpenWeatherOutputPort openWeatherOutputPort;

    public ObterDadosClimaticosUseCase(OpenWeatherOutputPort openWeatherOutputPort) {
        this.openWeatherOutputPort = openWeatherOutputPort;
    }

    @Override
    public DadosClimaticos obterDadosClimaticos(String latitude, String longitude, String data) {
        final String tsAtual = formatadorDataUnix(data);
        return openWeatherOutputPort.buscarDadosClimaticos(latitude, longitude, tsAtual);
    }
}
