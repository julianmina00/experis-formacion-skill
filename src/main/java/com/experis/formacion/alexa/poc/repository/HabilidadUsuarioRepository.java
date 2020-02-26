package com.experis.formacion.alexa.poc.repository;

import com.experis.formacion.alexa.poc.domain.HabilidadUsuario;
import com.experis.formacion.alexa.poc.domain.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the HabilidadUsuario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HabilidadUsuarioRepository extends JpaRepository<HabilidadUsuario, Long> {

    boolean existsByUsuarioAndHabilidadDescripcionIgnoreCase(Usuario usuario, String habilidad);

    @Query(value = "select uh.uh_idusuariohabilidades, uh.uh_idhabilidad, uh.uh_idusuario "+
        "from uh_usuariohabilidades uh "+
        "where uh.uh_idusuario = :usuarioId and uh.uh_idhabilidad in "+
        "	(select cc.cc_idhabilidad "+
        "	 from cc_contenidocurso cc"+
        "	 where cc.cc_idcurso = :cursoId and lower(cc.cc_habilidad_interes) = 'h')",
        nativeQuery = true)
    List<HabilidadUsuario> findMatchByUsuarioAndCurso(@Param("usuarioId") Long usuarioId, @Param("cursoId") Long cursoId);

    @Query(value = "select uh.uh_idusuariohabilidades, uh.uh_idhabilidad, uh.uh_idusuario "+
        "from uh_usuariohabilidades uh "+
        "where uh.uh_idusuario = :usuarioId and uh.uh_idhabilidad in "+
        "	(select pp.pp_idhabilidad "+
        "	 from pp_perfilplanformativo pp "+
        "	 where pp.pp_idplanformativo = :planFormativoId and lower(pp.pp_intereshabilidad) = 'h')",
        nativeQuery = true)
    List<HabilidadUsuario> findMatchByUsuarioAndPlanFormativo(@Param("usuarioId") Long usuarioId, @Param("planFormativoId") Long planFormativoId);

    boolean existsByUsuarioId(Long usuarioId);

}
