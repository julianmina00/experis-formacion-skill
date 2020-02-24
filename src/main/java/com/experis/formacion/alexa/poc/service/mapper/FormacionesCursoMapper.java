package com.experis.formacion.alexa.poc.service.mapper;

import com.experis.formacion.alexa.poc.domain.Curso;
import com.experis.formacion.alexa.poc.service.dto.CursoDTO;
import com.experis.formacion.alexa.poc.service.dto.FormacionesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Curso} and its DTO {@link CursoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FormacionesCursoMapper extends EntityMapper<FormacionesDTO, Curso> {


    @Mapping(target = "tipoFormacion", constant = "C")
    @Mapping(target = "idioma", ignore = true)
    @Mapping(target = "cursos", ignore = true)
    FormacionesDTO toDto(Curso curso);

    @Mapping(target = "cursoPlanFormativos", ignore = true)
    @Mapping(target = "removeCursoPlanFormativo", ignore = true)
    @Mapping(target = "cursoUsuarios", ignore = true)
    @Mapping(target = "removeCursoUsuario", ignore = true)
    @Mapping(target = "contenidoCursos", ignore = true)
    @Mapping(target = "removeContenidoCurso", ignore = true)
    Curso toEntity(FormacionesDTO formacionDTO);

    default Curso fromId(Long id) {
        if (id == null) {
            return null;
        }
        Curso curso = new Curso();
        curso.setId(id);
        return curso;
    }
}
