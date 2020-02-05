package com.experis.formacion.alexa.poc.repository;

import com.experis.formacion.alexa.poc.domain.Habilidad;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Habilidad entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HabilidadRepository extends JpaRepository<Habilidad, Long> {

}
