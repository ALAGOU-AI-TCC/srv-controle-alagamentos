package br.com.alagouai.srv.controle.alagamentos.port.output;

import br.com.alagouai.srv.controle.alagamentos.core.domain.model.Alagamento;

import java.util.List;

public interface AlagamentosOutputPort {

    List<Alagamento> buscarRegostrosPorIdControle(Integer idControle, Integer limite);
}
