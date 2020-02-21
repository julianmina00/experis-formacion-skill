package com.experis.formacion.alexa.poc.repository;

import com.experis.formacion.alexa.poc.domain.CursoUsuario;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CursoUsuario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CursoUsuarioRepository extends JpaRepository<CursoUsuario, Long> {

    Optional<CursoUsuario> findOneByUsuarioIdAndCursoId(Long usuarioId, Long cursoId);

    @Query(value = "select uc.uc_idusuariocurso, uc.uc_idusuario, uc.uc_idcurso  "+
        "from uc_usuariocurso uc, cu_cursos cu "+
        "where uc.uc_idusuario = :usuarioId "+
        "and uc.uc_idcurso = cu.cu_idcurso "+
        "and (:inicioRango between cu.cu_fechaini and cu.cu_fechafin "+
        "	or :finRango  between cu.cu_fechaini and cu.cu_fechafin "+
        "	or (:inicioRango <= cu.cu_fechaini and :finRango >= cu.cu_fechafin)) ",
        nativeQuery = true)
    List<CursoUsuario> findAvailableByUsuarioIdAndRangeOfDates(
        @Param("usuarioId") Long usuarioId,
        @Param("inicioRango") LocalDate inicioRango,
        @Param("finRango") LocalDate finRango);
}
