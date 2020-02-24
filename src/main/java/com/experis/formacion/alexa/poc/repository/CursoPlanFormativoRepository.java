package com.experis.formacion.alexa.poc.repository;

import com.experis.formacion.alexa.poc.domain.CursoPlanFormativo;
import com.experis.formacion.alexa.poc.domain.PlanFormativo;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CursoPlanFormativo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CursoPlanFormativoRepository extends JpaRepository<CursoPlanFormativo, Long> {

    List<CursoPlanFormativo> findByPlanFormativo(PlanFormativo planFormativo);

    List<CursoPlanFormativo> findByPlanFormativoId(Long planFormativoId);
}
