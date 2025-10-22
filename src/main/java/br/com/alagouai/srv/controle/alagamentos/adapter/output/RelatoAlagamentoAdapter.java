package br.com.alagouai.srv.controle.alagamentos.adapter.output;


import br.com.alagouai.srv.controle.alagamentos.adapter.output.repository.RelatoAlagamentoRepository;
import br.com.alagouai.srv.controle.alagamentos.adapter.output.repository.entity.RelatoAlagamentoEntity;
import br.com.alagouai.srv.controle.alagamentos.config.GeometryFac;
import br.com.alagouai.srv.controle.alagamentos.core.domain.model.ContagemPerto;
import br.com.alagouai.srv.controle.alagamentos.core.domain.model.RelatoAlagamento;
import br.com.alagouai.srv.controle.alagamentos.port.output.RelatoAlagamentoOutputPort;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Component
public class RelatoAlagamentoAdapter implements RelatoAlagamentoOutputPort {

    private final RelatoAlagamentoRepository jpa;
    private final GeometryFac geometryFac;

    public RelatoAlagamentoAdapter(RelatoAlagamentoRepository jpa, GeometryFac geometryFac) {
        this.jpa = jpa;
        this.geometryFac = geometryFac;
    }

    @Override
    public RelatoAlagamento salvar(RelatoAlagamento relato) {
        Point ponto = geometryFac.get().createPoint(new Coordinate(relato.getLongitude(), relato.getLatitude()));
        ponto.setSRID(4326);

        var relatoAlagamentoEntity = new RelatoAlagamentoEntity();
        relatoAlagamentoEntity.setBairro(relato.getBairro());
        relatoAlagamentoEntity.setInstanteEvento(relato.getInstanteEvento());
        relatoAlagamentoEntity.setInstanteRecebimento(relato.getInstanteRecebimento());
        relatoAlagamentoEntity.setUsuarioHash(relato.getUsuarioHash());
        relatoAlagamentoEntity.setLocalizacao(ponto);

        relatoAlagamentoEntity = jpa.save(relatoAlagamentoEntity);

        relato.setId(relatoAlagamentoEntity.getId());
        return relato;
    }

    @Override
    public List<RelatoAlagamento> buscarPerto(double latitude, double longitude, int raioMetros, int desdeMinutos) {
        return jpa.consultarPerto(latitude, longitude, raioMetros, desdeMinutos).stream().map(r -> {
            var dominio = new RelatoAlagamento();
            dominio.setId(((Number) r[0]).longValue());
            dominio.setLatitude(((Number) r[1]).doubleValue());
            dominio.setLongitude(((Number) r[2]).doubleValue());
            dominio.setBairro((String) r[3]);
            dominio.setInstanteEvento(((Timestamp) r[4]).toInstant());
            dominio.setInstanteRecebimento(((Timestamp) r[5]).toInstant());
            return dominio;
        }).toList();
    }

    @Override
    public ContagemPerto contarPerto(double latitude, double longitude, int raioMetros, int desdeMinutos) {
        List<Object[]> rows = jpa.contarNaArea(latitude, longitude, raioMetros, desdeMinutos);
        if (rows == null || rows.isEmpty()) {
            return new ContagemPerto(0L, 0L, null);
        }
        Object[] r = rows.get(0);

        long total = (r[0] == null) ? 0L : ((Number) r[0]).longValue();
        long pessoasRaw = (r[1] == null) ? 0L : ((Number) r[1]).longValue();

        Timestamp ts = (Timestamp) r[2];
        Instant ultimo = (ts == null) ? null : ts.toInstant();

        Long pessoasDistintas = (pessoasRaw == 0 && total > 0) ? null : pessoasRaw;

        return new ContagemPerto(total, pessoasDistintas, ultimo);
    }
}
