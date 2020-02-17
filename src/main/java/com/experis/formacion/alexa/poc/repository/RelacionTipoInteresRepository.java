package com.experis.formacion.alexa.poc.repository;

import com.experis.formacion.alexa.poc.domain.RelacionTipoInteres;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RelacionTipoInteres entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RelacionTipoInteresRepository extends JpaRepository<RelacionTipoInteres, Long> {

    List<RelacionTipoInteres> findByPadreIdAndProfundidad(Long padreId, int profundidad);

    List<RelacionTipoInteres> findByHijaIdAndProfundidad(Long hijaId, int profundidad);

    @Query("SELECT max(r.profundidad) FROM RelacionTipoInteres r WHERE r.hija.id = :hijaId")
    Optional<Integer> findMaxProfundidadByHijaId(@Param("hijaId") Long hijaId);

}
