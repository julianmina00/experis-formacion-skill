package com.experis.formacion.alexa.poc.repository;

import com.experis.formacion.alexa.poc.domain.PerfilPlanFormativo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PerfilPlanFormativo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PerfilPlanFormativoRepository extends JpaRepository<PerfilPlanFormativo, Long> {

}
