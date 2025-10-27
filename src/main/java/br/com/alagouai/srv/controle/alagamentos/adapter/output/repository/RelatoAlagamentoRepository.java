package br.com.alagouai.srv.controle.alagamentos.adapter.output.repository;

import br.com.alagouai.srv.controle.alagamentos.adapter.output.repository.entity.RelatoAlagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface RelatoAlagamentoRepository extends JpaRepository<RelatoAlagamentoEntity, Long> {

    @Query(value = """
  SELECT id,
         ST_Y(localizacao) AS latitude,
         ST_X(localizacao) AS longitude,
         bairro,
         instante_evento,
         instante_recebimento
  FROM relato_alagamento
  WHERE instante_evento >= (UTC_TIMESTAMP() - INTERVAL :desdeMinutos MINUTE)
    AND ST_Distance_Sphere(
          localizacao,
          ST_SRID(POINT(:longitude,:latitude),4326)
        ) <= :raioMetros
  ORDER BY instante_evento DESC
""", nativeQuery = true)
    List<Object[]> consultarPerto(@Param("latitude") double latitude,
                                  @Param("longitude") double longitude,
                                  @Param("raioMetros") int raioMetros,
                                  @Param("desdeMinutos") int desdeMinutos);


    @Query(value = """
  SELECT COUNT(*) AS total_relatos,
         COUNT(DISTINCT usuario_hash) AS pessoas_distintas,
         MAX(instante_evento) AS ultimo_evento
  FROM relato_alagamento
  WHERE instante_evento >= (UTC_TIMESTAMP() - INTERVAL :desdeMinutos MINUTE)
    AND ST_Distance_Sphere(
          localizacao,
          ST_SRID(POINT(:longitude,:latitude),4326)
        ) <= :raioMetros
""", nativeQuery = true)
    List<Object[]> contarNaArea(@Param("latitude") double latitude,
                                @Param("longitude") double longitude,
                                @Param("raioMetros") int raioMetros,
                                @Param("desdeMinutos") int desdeMinutos);

}