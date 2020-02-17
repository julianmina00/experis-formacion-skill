package com.experis.formacion.alexa.poc.repository;

import com.experis.formacion.alexa.poc.domain.TipoInteres;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TipoInteres entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoInteresRepository extends JpaRepository<TipoInteres, Long> {

    Optional<TipoInteres> findOneByDescripcionIgnoreCase(String descripcion);

}
