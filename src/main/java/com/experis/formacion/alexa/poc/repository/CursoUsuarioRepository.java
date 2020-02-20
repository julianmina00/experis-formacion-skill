package com.experis.formacion.alexa.poc.repository;

import com.experis.formacion.alexa.poc.domain.CursoUsuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CursoUsuario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CursoUsuarioRepository extends JpaRepository<CursoUsuario, Long> {

    Optional<CursoUsuario> findOneByUsuarioIdAndCursoId(Long usuarioId, Long cursoId);
}
