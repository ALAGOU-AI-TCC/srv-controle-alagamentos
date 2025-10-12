package br.com.alagouai.srv.controle.alagamentos.config;

import br.com.alagouai.srv.controle.alagamentos.core.usecase.AtualizarPrecipitacaoUseCase;
import br.com.alagouai.srv.controle.alagamentos.core.usecase.PreverAlagamentoUseCase;
import br.com.alagouai.srv.controle.alagamentos.port.input.AtualizarAlagamentoInputPort;
import br.com.alagouai.srv.controle.alagamentos.port.input.PreverAlagamentoInputPort;
import br.com.alagouai.srv.controle.alagamentos.port.output.AlagamentosOutputPort;
import br.com.alagouai.srv.controle.alagamentos.port.output.LogControlOutputPort;
import br.com.alagouai.srv.controle.alagamentos.port.output.OpenWeatherOutputPort;
import br.com.alagouai.srv.controle.alagamentos.port.output.PredictionApiOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public AtualizarAlagamentoInputPort atualizarAlagamentoInputPort(AlagamentosOutputPort alagamentosOutputPort, LogControlOutputPort logControlOutputPort, OpenWeatherOutputPort openWeatherOutputPort) {
        return new AtualizarPrecipitacaoUseCase(logControlOutputPort,alagamentosOutputPort, openWeatherOutputPort);
    }

    @Bean
    public PreverAlagamentoInputPort preverAlagamentoInputPort(LogControlOutputPort logControlOutputPort, OpenWeatherOutputPort openWeatherOutputPort, PredictionApiOutputPort predictionApiOutputPort) {
        return new PreverAlagamentoUseCase(logControlOutputPort, openWeatherOutputPort, predictionApiOutputPort);
    }
}
