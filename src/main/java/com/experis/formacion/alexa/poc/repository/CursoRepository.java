package com.experis.formacion.alexa.poc.repository;

import com.experis.formacion.alexa.poc.domain.Curso;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Curso entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

}
