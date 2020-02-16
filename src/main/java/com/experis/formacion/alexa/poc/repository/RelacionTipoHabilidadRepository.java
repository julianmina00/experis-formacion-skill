package com.experis.formacion.alexa.poc.repository;

import com.experis.formacion.alexa.poc.domain.RelacionTipoHabilidad;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RelacionTipoHabilidad entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RelacionTipoHabilidadRepository extends JpaRepository<RelacionTipoHabilidad, Long> {

    List<RelacionTipoHabilidad> findByPadreIdAndProfundidad(Long padreId, int profundidad);

    List<RelacionTipoHabilidad> findByHijaIdAndProfundidad(Long hijaId, int profundidad);

    @Query("SELECT max(r.profundidad) FROM RelacionTipoHabilidad r WHERE r.hija.id = :hijaId")
    Optional<Integer> findMaxProfundidadByHijaId(@Param("hijaId") Long hijaId);
}
