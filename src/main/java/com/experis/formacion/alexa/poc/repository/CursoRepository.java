package com.experis.formacion.alexa.poc.repository;

import com.experis.formacion.alexa.poc.domain.Curso;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Curso entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    @Query(
        value = "select cu_idcurso, cu.cu_descripcion, cu.cu_descripcionlarga, cu.cu_fechaini, cu.cu_fechafin, cu.cu_hora, cu.cu_numhoras, cu.cu_telem_presenc, cu.cu_ubicacion "+
            "from cu_cursos cu, "+
            "( "+
            "	select cc.cc_idcurso curso, count(*) relaciones from cc_contenidocurso cc "+
            "	where cc.cc_idhabilidad in (:habilidades) or cc.cc_idinteres in (:intereses) "+
            "	group by cc.cc_idcurso "+
            ") cc "+
            "where cc.curso = cu.cu_idcurso and current_date >= cu.cu_fechaini "+
            "order by cc.relaciones ",
        nativeQuery = true)
    List<Curso> findMoreRelevantAvailableByHabilidadesAndIntereses(
        @Param("habilidades") List<Long> habilidades,
        @Param("intereses") List<Long> intereses,
        Pageable pageable);

}
