package br.com.alagouai.srv.controle.alagamentos.adapter.output;


import br.com.alagouai.srv.controle.alagamentos.adapter.mapper.PrevisaoMapper;
import br.com.alagouai.srv.controle.alagamentos.adapter.output.dto.PredictionApiResponse;
import br.com.alagouai.srv.controle.alagamentos.adapter.output.feign.PredictionApiClient;
import br.com.alagouai.srv.controle.alagamentos.core.domain.model.Alagamento;
import br.com.alagouai.srv.controle.alagamentos.core.domain.model.Previsao;
import br.com.alagouai.srv.controle.alagamentos.port.output.PredictionApiOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PredictionApiAdapter implements PredictionApiOutputPort {

    private final PredictionApiClient predictionApiClient;
    private final PrevisaoMapper previsaoMapper;

    public Previsao prever(Alagamento alagamentoAtual) {
        PredictionApiResponse predict = predictionApiClient.predict(alagamentoAtual);
        return previsaoMapper.fromResponsetoDomain(predict);
    }
}
