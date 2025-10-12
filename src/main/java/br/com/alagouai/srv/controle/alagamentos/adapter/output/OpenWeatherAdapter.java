package br.com.alagouai.srv.controle.alagamentos.adapter.output;


import br.com.alagouai.srv.controle.alagamentos.adapter.exception.IntegrationException;
import br.com.alagouai.srv.controle.alagamentos.adapter.mapper.AlagamentoMapper;
import br.com.alagouai.srv.controle.alagamentos.adapter.output.feign.OpenWeatherClient;
import br.com.alagouai.srv.controle.alagamentos.core.domain.model.Alagamento;
import br.com.alagouai.srv.controle.alagamentos.port.output.OpenWeatherOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class OpenWeatherAdapter implements OpenWeatherOutputPort {

    private final OpenWeatherClient openWeatherClient;
    private final AlagamentoMapper alagamentoMapper;

    @Value("${openweathermap.key}") String appId;
    @Value("${openweathermap.units}") String units;
    @Value("${openweathermap.lang}") String lang;


    public Alagamento buscarDadosClimaticos(String latitude, String longitude, String dataHora) {
        try {
            log.info("Pesquisando dados climáticos no OpenWeather...");
            return alagamentoMapper.openWeatherToAlagamento(openWeatherClient.getWeatherData(latitude, longitude, dataHora, appId, units,lang).getData().getFirst());
        } catch (Exception e) {
            log.error("Erro ao buscar dados climáticos", e);
            throw new IntegrationException(e.getMessage());
        }
    }

}
