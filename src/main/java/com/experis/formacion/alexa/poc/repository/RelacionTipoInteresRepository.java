package com.experis.formacion.alexa.poc.repository;

import com.experis.formacion.alexa.poc.domain.RelacionTipoInteres;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RelacionTipoInteres entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RelacionTipoInteresRepository extends JpaRepository<RelacionTipoInteres, Long> {

}
