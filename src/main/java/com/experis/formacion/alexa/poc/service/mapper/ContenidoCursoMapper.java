package com.experis.formacion.alexa.poc.service.mapper;

import com.experis.formacion.alexa.poc.domain.*;
import com.experis.formacion.alexa.poc.service.dto.ContenidoCursoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ContenidoCurso} and its DTO {@link ContenidoCursoDTO}.
 */
@Mapper(componentModel = "spring", uses = {CursoMapper.class, InteresMapper.class, HabilidadMapper.class})
public interface ContenidoCursoMapper extends EntityMapper<ContenidoCursoDTO, ContenidoCurso> {

    @Mapping(source = "curso.id", target = "cursoId")
    @Mapping(source = "interes.id", target = "interesId")
    @Mapping(source = "habilidad.id", target = "habilidadId")
    ContenidoCursoDTO toDto(ContenidoCurso contenidoCurso);

    @Mapping(source = "cursoId", target = "curso")
    @Mapping(source = "interesId", target = "interes")
    @Mapping(source = "habilidadId", target = "habilidad")
    ContenidoCurso toEntity(ContenidoCursoDTO contenidoCursoDTO);

    default ContenidoCurso fromId(Long id) {
        if (id == null) {
            return null;
        }
        ContenidoCurso contenidoCurso = new ContenidoCurso();
        contenidoCurso.setId(id);
        return contenidoCurso;
    }
}
