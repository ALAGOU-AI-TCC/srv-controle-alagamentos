package br.com.alagouai.srv.controle.alagamentos.adapter.mapper;

import br.com.alagouai.srv.controle.alagamentos.adapter.output.dto.OpenWeatherResponse;
import br.com.alagouai.srv.controle.alagamentos.adapter.output.dto.WeatherData;
import br.com.alagouai.srv.controle.alagamentos.adapter.output.repository.entity.AlagamentoEntity;
import br.com.alagouai.srv.controle.alagamentos.core.domain.model.Alagamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static br.com.alagouai.srv.controle.alagamentos.core.common.constant.Constants.TIMEZONE_SAO_PAULO;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AlagamentoMapper {

    Alagamento toAlagamento(AlagamentoEntity alagamentoEntity);

    AlagamentoEntity toEntity(Alagamento alagamento);


        @Mapping(target = "temperatura", ignore = true)
        @Mapping(target = "umidade", source = "umidade")
        @Mapping(target = "pressao", source = "pressao")
        @Mapping(target = "velocidadeVento", source = "velocidadeVento")
        @Mapping(target = "pontoOrvalho", source = "pontoOrvalho")
        @Mapping(target = "latitude", ignore = true)
        @Mapping(target = "longitude", ignore = true)
        @Mapping(target = "dataHora", source = "dataHora", qualifiedByName = "timestampToString")
        @Mapping(target = "intensidadeChuva", expression = "java(getDescricaoClima(weatherData))")
        @Mapping(target = "precipitacaoChuva", expression = "java(getUmaHora(weatherData))")
        @Mapping(target = "tempoChuva", ignore = true)
        @Mapping(target = "historicoAlagamento", ignore = true)
        @Mapping(target = "solo", ignore = true)
        @Mapping(target = "bairro", ignore = true)
        @Mapping(target = "precipitacaoDiaria", ignore = true)
        @Mapping(target = "precipitacaoAcumulada", ignore = true)
        @Mapping(target = "id", ignore = true)
        Alagamento openWeatherToAlagamento(WeatherData weatherData);

        @Named("timestampToString")
        static String timestampToString(long timestamp) {
            return Instant.ofEpochSecond(timestamp)
                    .atZone(ZoneId.of(TIMEZONE_SAO_PAULO))
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }

        default String getDescricaoClima(WeatherData data) {

            if (data.getCondicoesClimaticas() != null && !data.getCondicoesClimaticas().isEmpty()) {
                return data.getCondicoesClimaticas().get(0).getDescription();
            }
            return null;
        }

        default Double getUmaHora(WeatherData data) {

            return data.getChuva() != null ? data.getChuva().getUmaHora() : 0;
        }

}
