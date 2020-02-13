package com.experis.formacion.alexa.poc.repository;

import com.experis.formacion.alexa.poc.domain.NivelIdioma;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NivelIdioma entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NivelIdiomaRepository extends JpaRepository<NivelIdioma, Long> {

}
