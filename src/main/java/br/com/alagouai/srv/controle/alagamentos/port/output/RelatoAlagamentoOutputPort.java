package br.com.alagouai.srv.controle.alagamentos.port.output;

import br.com.alagouai.srv.controle.alagamentos.core.domain.model.ContagemPerto;
import br.com.alagouai.srv.controle.alagamentos.core.domain.model.RelatoAlagamento;

import java.util.List;

public interface RelatoAlagamentoOutputPort {
    RelatoAlagamento salvar(RelatoAlagamento relato);
    List<RelatoAlagamento> buscarPerto(double latitude, double longitude, int raioMetros, int desdeMinutos);
    ContagemPerto contarPerto(double latitude, double longitude, int raioMetros, int desdeMinutos);
}
