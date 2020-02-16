package com.experis.formacion.alexa.poc.repository;

import com.experis.formacion.alexa.poc.domain.HabilidadUsuario;
import com.experis.formacion.alexa.poc.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the HabilidadUsuario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HabilidadUsuarioRepository extends JpaRepository<HabilidadUsuario, Long> {

    boolean existsByUsuarioAndHabilidadDescripcionIgnoreCase(Usuario usuario, String habilidad);
}
