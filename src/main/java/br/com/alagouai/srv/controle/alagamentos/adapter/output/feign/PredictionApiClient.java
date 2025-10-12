package br.com.alagouai.srv.controle.alagamentos.adapter.output.feign;


import br.com.alagouai.srv.controle.alagamentos.adapter.output.dto.PredictionApiResponse;
import br.com.alagouai.srv.controle.alagamentos.core.domain.model.Alagamento;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PredictionApiClient", url = "${predictionapi.url}")
public interface PredictionApiClient {

    @PostMapping("${predictionapi.path-predict}")
    PredictionApiResponse predict(@RequestBody Alagamento alagamento);
}
