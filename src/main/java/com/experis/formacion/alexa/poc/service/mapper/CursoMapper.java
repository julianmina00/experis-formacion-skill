package com.experis.formacion.alexa.poc.service.mapper;

import com.experis.formacion.alexa.poc.domain.*;
import com.experis.formacion.alexa.poc.service.dto.CursoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Curso} and its DTO {@link CursoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CursoMapper extends EntityMapper<CursoDTO, Curso> {


    @Mapping(target = "cursoPlanFormativos", ignore = true)
    @Mapping(target = "removeCursoPlanFormativo", ignore = true)
    @Mapping(target = "cursoUsuarios", ignore = true)
    @Mapping(target = "removeCursoUsuario", ignore = true)
    @Mapping(target = "contenidoCursos", ignore = true)
    @Mapping(target = "removeContenidoCurso", ignore = true)
    Curso toEntity(CursoDTO cursoDTO);

    default Curso fromId(Long id) {
        if (id == null) {
            return null;
        }
        Curso curso = new Curso();
        curso.setId(id);
        return curso;
    }
}
