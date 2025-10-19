package br.com.alagouai.srv.controle.alagamentos.port.output;

import br.com.alagouai.srv.controle.alagamentos.core.domain.model.DadosClimaticos;

import java.util.List;

public interface AlagamentosOutputPort {

    List<DadosClimaticos> buscarRegostrosPorIdControle(Integer idControle, Integer limite);

    void atualizarRegistros(List<DadosClimaticos> alagamentosListAtualizados);
}
