package br.com.alagouai.srv.controle.alagamentos.config;

import br.com.alagouai.srv.controle.alagamentos.adapter.output.repository.RelatoAlagamentoRepository;
import br.com.alagouai.srv.controle.alagamentos.core.usecase.*;
import br.com.alagouai.srv.controle.alagamentos.port.input.*;
import br.com.alagouai.srv.controle.alagamentos.port.output.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public AtualizarAlagamentoInputPort atualizarAlagamentoInputPort(AlagamentosOutputPort alagamentosOutputPort, LogControlOutputPort logControlOutputPort, OpenWeatherOutputPort openWeatherOutputPort) {
        return new AtualizarPrecipitacaoUseCase(logControlOutputPort,alagamentosOutputPort, openWeatherOutputPort);
    }

    @Bean
    public PreverAlagamentoInputPort preverAlagamentoInputPort(OpenWeatherOutputPort openWeatherOutputPort, PredictionApiOutputPort predictionApiOutputPort) {
        return new PreverAlagamentoUseCase(openWeatherOutputPort, predictionApiOutputPort);
    }

    @Bean
    public ObterDadosClimaticosInputPort obterDadosClimaticosInputPort(OpenWeatherOutputPort openWeatherOutputPort) {
        return new ObterDadosClimaticosUseCase(openWeatherOutputPort);
    }

    @Bean
    RegistrarRelatoInputPort registrarRelatoUseCase(RelatoAlagamentoOutputPort repo) {
        return new RegistrarRelatoUseCase(repo);
    }

    @Bean
    EncontrarPertoInputPort encontrarPertoUseCase(RelatoAlagamentoOutputPort repo) {
        return new EncontrarPertoUseCase(repo);
    }

    @Bean
    ContarPertoInputPort contarPertoUseCase(RelatoAlagamentoOutputPort repo) {
        return new ContarPertoUseCase(repo);
    }
}
