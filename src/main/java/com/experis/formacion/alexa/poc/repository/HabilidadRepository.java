package com.experis.formacion.alexa.poc.repository;

import com.experis.formacion.alexa.poc.domain.Habilidad;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Habilidad entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HabilidadRepository extends JpaRepository<Habilidad, Long> {

    Optional<Habilidad> findOneByDescripcionIgnoreCase(String habilidad);
}
