package br.com.alagouai.srv.controle.alagamentos.core.usecase;

import br.com.alagouai.srv.controle.alagamentos.core.domain.model.RelatoAlagamento;
import br.com.alagouai.srv.controle.alagamentos.port.input.EncontrarPertoInputPort;
import br.com.alagouai.srv.controle.alagamentos.port.output.RelatoAlagamentoOutputPort;

import java.util.List;

public class EncontrarPertoUseCase implements EncontrarPertoInputPort {

    private final RelatoAlagamentoOutputPort repositorio;
    public EncontrarPertoUseCase(RelatoAlagamentoOutputPort repositorio) { this.repositorio = repositorio; }
    @Override
    public List<RelatoAlagamento> encontrarPerto(double latitude, double longitude, int raioMetros, int desdeMinutos) {
        return repositorio.buscarPerto(latitude, longitude, raioMetros, desdeMinutos);
    }
}
