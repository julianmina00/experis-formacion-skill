package com.experis.formacion.alexa.poc.repository;

import com.experis.formacion.alexa.poc.domain.PlanFormativoUsuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PlanFormativoUsuario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlanFormativoUsuarioRepository extends JpaRepository<PlanFormativoUsuario, Long> {

    Optional<PlanFormativoUsuario> findOneByUsuarioIdAndPlanFormativoId(Long usuarioId, Long planFormativoId);
}
