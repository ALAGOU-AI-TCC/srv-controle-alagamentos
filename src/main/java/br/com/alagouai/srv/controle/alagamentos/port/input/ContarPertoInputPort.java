package br.com.alagouai.srv.controle.alagamentos.port.input;

import br.com.alagouai.srv.controle.alagamentos.core.domain.model.ContagemPerto;

public interface ContarPertoInputPort {
    ContagemPerto contarPerto(double latitude, double longitude, int raioMetros, int desdeMinutos);

}
