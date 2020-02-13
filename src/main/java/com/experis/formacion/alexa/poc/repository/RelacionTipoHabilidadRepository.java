package com.experis.formacion.alexa.poc.repository;

import com.experis.formacion.alexa.poc.domain.RelacionTipoHabilidad;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RelacionTipoHabilidad entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RelacionTipoHabilidadRepository extends JpaRepository<RelacionTipoHabilidad, Long> {

}
