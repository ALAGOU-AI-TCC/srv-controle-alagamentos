package br.com.alagouai.srv.controle.alagamentos.adapter.mapper;

import br.com.alagouai.srv.controle.alagamentos.adapter.output.repository.entity.AlagamentoEntity;
import br.com.alagouai.srv.controle.alagamentos.core.domain.model.Alagamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AlagamentoMapper {

    Alagamento toAlagamento(AlagamentoEntity alagamentoEntity);

}
