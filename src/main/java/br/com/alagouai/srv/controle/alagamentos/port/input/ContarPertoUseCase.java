package br.com.alagouai.srv.controle.alagamentos.port.input;

import br.com.alagouai.srv.controle.alagamentos.core.domain.model.ContagemPerto;
import br.com.alagouai.srv.controle.alagamentos.port.output.RelatoAlagamentoOutputPort;

public class ContarPertoUseCase implements ContarPertoInputPort {
    private final RelatoAlagamentoOutputPort repositorio;

    public ContarPertoUseCase(RelatoAlagamentoOutputPort repositorio) { this.repositorio = repositorio; }

    @Override
    public ContagemPerto contarPerto(double latitude, double longitude, int raioMetros, int desdeMinutos) {
        return repositorio.contarPerto(latitude, longitude, raioMetros, desdeMinutos);
    }
}
