package com.experis.formacion.alexa.poc.repository;

import com.experis.formacion.alexa.poc.domain.InteresUsuario;
import com.experis.formacion.alexa.poc.domain.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the InteresUsuario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InteresUsuarioRepository extends JpaRepository<InteresUsuario, Long> {

    boolean existsByUsuarioAndInteresDescripcionIgnoreCase(Usuario usuario, String interes);

    @Query(value = "select ui.ui_idusuariointereses, ui.ui_idinteres, ui.ui_idusuario "+
        "from ui_usuariointereses ui "+
        "where ui.ui_idusuario = :usuarioId and ui_idinteres in "+
        "	(select cc.cc_idinteres "+
        "	 from cc_contenidocurso cc"+
        "	 where cc.cc_idcurso = :cursoId and lower(cc.cc_habilidad_interes) = 'i')",
        nativeQuery = true)
    List<InteresUsuario> findMatchByUsuarioAndCurso(@Param("usuarioId") Long usuarioId, @Param("cursoId") Long cursoId);

    @Query(value = "select ui.ui_idusuariointereses, ui.ui_idinteres, ui.ui_idusuario "+
        "from ui_usuariointereses ui "+
        "where ui.ui_idusuario = :usuarioId and ui_idinteres in "+
        "	(select pp.pp_idinteres  "+
        "	 from pp_perfilplanformativo pp "+
        "	 where pp.pp_idplanformativo = :planFormativoId and lower(pp.pp_intereshabilidad) = 'i')",
        nativeQuery = true)
    List<InteresUsuario> findMatchByUsuarioAndPlanFormativo(@Param("usuarioId") Long usuarioId, @Param("planFormativoId") Long planFormativoId);


}
