package com.experis.formacion.alexa.poc.repository;

import com.experis.formacion.alexa.poc.domain.HabilidadUsuario;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the HabilidadUsuario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HabilidadUsuarioRepository extends JpaRepository<HabilidadUsuario, Long> {

}
