package com.experis.formacion.alexa.poc.repository;

import com.experis.formacion.alexa.poc.domain.PlanFormativo;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PlanFormativo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlanFormativoRepository extends JpaRepository<PlanFormativo, Long> {

    @Query(
        value = "select pf.pf_planformativo, pf.pf_descplanformativo, pf.pf_desclargplanformativo, pf.pf_fechainicio, pf.pf_fechafin "+
            "from pf_planformativo pf,  "+
            "( "+
            "	select pp.pp_idplanformativo plan, count(*) relaciones from pp_perfilplanformativo pp  "+
            "	where pp.pp_idhabilidad in (:habilidades) or pp.pp_idinteres in (:intereses) "+
            "	group by pp.pp_idplanformativo "+
            ") pp "+
            "where pp.plan = pf.pf_planformativo and current_date <= pf.pf_fechainicio  "+
            "order by pp.relaciones desc ",
        nativeQuery = true)
    List<PlanFormativo> findMoreRelevantAvailableByHabilidadesAndIntereses(
        @Param("habilidades") List<Long> habilidades,
        @Param("intereses") List<Long> intereses,
        Pageable pageable);

}
