package br.com.alagouai.srv.controle.alagamentos.port.input;

import br.com.alagouai.srv.controle.alagamentos.core.domain.model.RelatoAlagamento;

import java.util.List;

public interface EncontrarPertoInputPort {
    List<RelatoAlagamento> encontrarPerto(double latitude, double longitude, int raioMetros, int desdeMinutos);
}
