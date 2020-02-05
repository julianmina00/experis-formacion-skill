package com.experis.formacion.alexa.poc.repository;

import com.experis.formacion.alexa.poc.domain.TipoHabilidad;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TipoHabilidad entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoHabilidadRepository extends JpaRepository<TipoHabilidad, Long> {

}
