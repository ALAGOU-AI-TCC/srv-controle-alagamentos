package br.com.alagouai.srv.controle.alagamentos.adapter.mapper;

import br.com.alagouai.srv.controle.alagamentos.adapter.input.request.PrevisaoRequest;
import br.com.alagouai.srv.controle.alagamentos.adapter.input.response.PrevisaoResponse;
import br.com.alagouai.srv.controle.alagamentos.adapter.output.dto.PredictionApiResponse;
import br.com.alagouai.srv.controle.alagamentos.core.domain.model.Previsao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PrevisaoMapper {

    Previsao toDomain(PrevisaoRequest request);

    @Mapping(target = "risco", source = "risco")
    @Mapping(target = "nivel", source = "nivel")
    PrevisaoResponse toResponse(Previsao previsao);

    @Mapping(target = "risco", source = "risk")
    @Mapping(target = "nivel", source = "bucket")
    Previsao fromResponsetoDomain(PredictionApiResponse predict);
}
