package com.experis.formacion.alexa.poc.repository;

import com.experis.formacion.alexa.poc.domain.IdiomaUsuario;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the IdiomaUsuario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IdiomaUsuarioRepository extends JpaRepository<IdiomaUsuario, Long> {

    boolean existsByUsuarioId(Long usuarioId);
}
