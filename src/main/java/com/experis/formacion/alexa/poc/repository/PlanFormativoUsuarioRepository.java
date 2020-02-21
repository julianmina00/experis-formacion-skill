package com.experis.formacion.alexa.poc.repository;

import com.experis.formacion.alexa.poc.domain.PlanFormativoUsuario;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PlanFormativoUsuario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlanFormativoUsuarioRepository extends JpaRepository<PlanFormativoUsuario, Long> {

    Optional<PlanFormativoUsuario> findOneByUsuarioIdAndPlanFormativoId(Long usuarioId, Long planFormativoId);


    @Query(value = "select up.up_idusuarioplanformativo, up.up_idusuario, up.up_idplanformativo  "+
        "from up_usuarioplanformativo up, pf_planformativo pf  "+
        "where up.up_idusuario = :usuarioId "+
        "and up.up_idplanformativo = pf.pf_planformativo  "+
        "and (:inicioRango between pf.pf_fechainicio and pf.pf_fechafin "+
        "	or :finRango between pf.pf_fechainicio and pf.pf_fechafin "+
        "	or (:inicioRango <= pf.pf_fechainicio and :finRango >= pf.pf_fechafin)) ",
        nativeQuery = true)
    List<PlanFormativoUsuario> findAvailableByUsuarioIdAndRangeOfDates(@Param("usuarioId") Long usuarioId,
        @Param("inicioRango") LocalDate inicioRango,
        @Param("finRango") LocalDate finRango);
}
