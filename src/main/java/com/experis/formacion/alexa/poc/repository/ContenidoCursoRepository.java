package com.experis.formacion.alexa.poc.repository;

import com.experis.formacion.alexa.poc.domain.ContenidoCurso;
import com.experis.formacion.alexa.poc.domain.Habilidad;
import com.experis.formacion.alexa.poc.domain.HabilidadUsuario;
import com.experis.formacion.alexa.poc.domain.Interes;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ContenidoCurso entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContenidoCursoRepository extends JpaRepository<ContenidoCurso, Long> {

}
