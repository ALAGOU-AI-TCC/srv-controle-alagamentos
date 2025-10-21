package br.com.alagouai.srv.controle.alagamentos.port.input;


import br.com.alagouai.srv.controle.alagamentos.core.domain.model.RelatoAlagamento;

public interface RegistrarRelatoInputPort {
    RelatoAlagamento registrar(double latitude, double longitude, String dataHoraIso, String bairro, String dispositivoId);
}
