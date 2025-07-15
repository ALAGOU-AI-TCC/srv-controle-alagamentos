package br.com.alagouai.srv.controle.alagamentos.port.input;

public interface AtualizarAlagamentoInputPort {

    void atualizarPrecipitacaoAcumulada(Integer idControle, Integer limite);
}
