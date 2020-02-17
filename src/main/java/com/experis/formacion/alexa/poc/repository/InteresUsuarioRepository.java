package com.experis.formacion.alexa.poc.repository;

import com.experis.formacion.alexa.poc.domain.InteresUsuario;
import com.experis.formacion.alexa.poc.domain.Usuario;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the InteresUsuario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InteresUsuarioRepository extends JpaRepository<InteresUsuario, Long> {

    boolean existsByUsuarioAndInteresDescripcionIgnoreCase(Usuario usuario, String interes);

}
