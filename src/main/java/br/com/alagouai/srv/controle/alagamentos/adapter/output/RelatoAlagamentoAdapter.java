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
        Object[] row = jpa.contarNaArea(latitude, longitude, raioMetros, desdeMinutos);
        long total = row != null ? ((Number) row[0]).longValue() : 0L;
        long pessoas = row != null ? ((Number) row[1]).longValue() : 0L;
        Timestamp ultimo = row != null ? (Timestamp) row[2] : null;
        Instant ultimoInstante = ultimo != null ? ultimo.toInstant() : null;

        Long pessoasDistintas = (pessoas == 0 && total > 0) ? null : pessoas;
        return new ContagemPerto(total, pessoasDistintas, ultimoInstante);
    }
}
