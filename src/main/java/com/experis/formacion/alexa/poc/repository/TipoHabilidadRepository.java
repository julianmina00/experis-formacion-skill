package com.experis.formacion.alexa.poc.repository;

import com.experis.formacion.alexa.poc.domain.TipoHabilidad;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TipoHabilidad entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoHabilidadRepository extends JpaRepository<TipoHabilidad, Long> {

    Optional<TipoHabilidad> findOneByDescripcionIgnoreCase(String descripcion);
}
