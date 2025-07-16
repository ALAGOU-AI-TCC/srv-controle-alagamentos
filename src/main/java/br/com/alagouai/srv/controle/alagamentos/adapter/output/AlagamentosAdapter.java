package br.com.alagouai.srv.controle.alagamentos.adapter.output;

import br.com.alagouai.srv.controle.alagamentos.adapter.exception.DataBaseException;
import br.com.alagouai.srv.controle.alagamentos.adapter.mapper.AlagamentoMapper;
import br.com.alagouai.srv.controle.alagamentos.adapter.output.repository.AlagamentosRepository;
import br.com.alagouai.srv.controle.alagamentos.core.domain.model.Alagamento;
import br.com.alagouai.srv.controle.alagamentos.port.output.AlagamentosOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class AlagamentosAdapter implements AlagamentosOutputPort {

    private final AlagamentosRepository alagamentosRepository;
    private final AlagamentoMapper alagamentoMapper;

    public AlagamentosAdapter(AlagamentosRepository alagamentosRepository, AlagamentoMapper alagamentoMapper) {
        this.alagamentosRepository = alagamentosRepository;
        this.alagamentoMapper = alagamentoMapper;
    }

    @Override
    public List<Alagamento> buscarRegostrosPorIdControle(Integer idControle, Integer limite) {
        int idFim = idControle + limite;
        log.info("Iniciando consultas do Id {}, até {}", idControle, idFim);
        try {
            return alagamentosRepository
                    .findByIdBetween(Long.valueOf(idControle), Long.valueOf(idFim))
                    .stream()
                    .map(alagamentoMapper::toAlagamento)
                    .toList();
        } catch (Exception e) {
            log.error("Erro ao buscar registros", e);
            throw new DataBaseException(e.getMessage());
        }
    }
}
