package com.experis.formacion.alexa.poc.repository;

import com.experis.formacion.alexa.poc.domain.ContenidoCurso;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ContenidoCurso entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContenidoCursoRepository extends JpaRepository<ContenidoCurso, Long> {

}
