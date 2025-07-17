package br.com.alagouai.srv.controle.alagamentos.adapter.output.repository;

import br.com.alagouai.srv.controle.alagamentos.adapter.output.repository.entity.AlagamentoEntity;
import br.com.alagouai.srv.controle.alagamentos.core.domain.model.Alagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlagamentosRepository extends JpaRepository<AlagamentoEntity, Long> {

   // List<AlagamentoEntity> findByIdBetween(Integer idControle, Integer limite);

    @Query("SELECT r FROM AlagamentoEntity r WHERE r.id BETWEEN :inicio AND :fim")
    List<AlagamentoEntity> findByIdBetween(@Param("inicio") Long inicio, @Param("fim") Long fim);


}
