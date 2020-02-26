package com.experis.formacion.alexa.poc.repository;

import com.experis.formacion.alexa.poc.domain.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Usuario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findOneByNombreIgnoreCaseAndDocumento(String nombre, String documento);

}
