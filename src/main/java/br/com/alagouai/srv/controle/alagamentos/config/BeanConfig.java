package br.com.alagouai.srv.controle.alagamentos.config;

import br.com.alagouai.srv.controle.alagamentos.core.usecase.AtualizarPrecipitacaoUseCase;
import br.com.alagouai.srv.controle.alagamentos.port.input.AtualizarAlagamentoInputPort;
import br.com.alagouai.srv.controle.alagamentos.port.output.AlagamentosOutputPort;
import br.com.alagouai.srv.controle.alagamentos.port.output.LogControlOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public AtualizarAlagamentoInputPort atualizarAlagamentoInputPort(AlagamentosOutputPort alagamentosOutputPort, LogControlOutputPort logControlOutputPort) {
        return new AtualizarPrecipitacaoUseCase(logControlOutputPort,alagamentosOutputPort);
    }
}
