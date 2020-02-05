package com.experis.formacion.alexa.poc.repository;

import com.experis.formacion.alexa.poc.domain.PlanFormativo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PlanFormativo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlanFormativoRepository extends JpaRepository<PlanFormativo, Long> {

}
