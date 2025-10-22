package br.com.alagouai.srv.controle.alagamentos.core.usecase;

import br.com.alagouai.srv.controle.alagamentos.core.domain.model.RelatoAlagamento;
import br.com.alagouai.srv.controle.alagamentos.port.input.RegistrarRelatoInputPort;
import br.com.alagouai.srv.controle.alagamentos.port.output.RelatoAlagamentoOutputPort;
import org.apache.commons.codec.digest.DigestUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class RegistrarRelatoUseCase implements RegistrarRelatoInputPort {

    private final RelatoAlagamentoOutputPort repositorio;

    public RegistrarRelatoUseCase(RelatoAlagamentoOutputPort repositorio) {
        this.repositorio = repositorio;
    }

    private static final ZoneId ZONA_SP = ZoneId.of("America/Sao_Paulo");
    private static final DateTimeFormatter FORMATO_BR = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public RelatoAlagamento registrar(double latitude, double longitude, String dataHoraIso, String bairro, String dispositivoId) {
        var relato = new RelatoAlagamento();
        relato.setLatitude(latitude);
        relato.setLongitude(longitude);
        relato.setBairro(bairro);

        LocalDateTime ldt = LocalDateTime.parse(dataHoraIso, FORMATO_BR);
        Instant instanteEventoUtc = ldt.atZone(ZONA_SP).toInstant();

        Instant recebSp  = LocalDateTime.now().atZone(ZONA_SP).toInstant();

        relato.setInstanteEvento(instanteEventoUtc);
        relato.setInstanteRecebimento(recebSp);

        if (dispositivoId != null && !dispositivoId.isBlank()) {
            relato.setUsuarioHash(org.apache.commons.codec.digest.DigestUtils.sha256Hex("alagouai:"+dispositivoId));
        }

        return repositorio.salvar(relato);
    }
}
