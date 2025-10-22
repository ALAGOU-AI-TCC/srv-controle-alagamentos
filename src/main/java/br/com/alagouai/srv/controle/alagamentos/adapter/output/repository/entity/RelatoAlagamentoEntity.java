package br.com.alagouai.srv.controle.alagamentos.adapter.output.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "relato_alagamento")
public class RelatoAlagamentoEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="instante_evento", nullable=false, columnDefinition="datetime(3)")
    private Instant instanteEvento;

    @Column(name="instante_recebimento", nullable=false, columnDefinition="timestamp(3)")
    private Instant instanteRecebimento;

    @Column(name="bairro", length=120)
    private String bairro;

    @Column(name="usuario_hash", length=64)
    private String usuarioHash;

    @Column(name="localizacao", columnDefinition="point SRID 4326", nullable=false)
    private Point localizacao;

}
