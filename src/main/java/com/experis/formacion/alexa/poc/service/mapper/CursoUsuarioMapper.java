package com.experis.formacion.alexa.poc.service.mapper;

import com.experis.formacion.alexa.poc.domain.*;
import com.experis.formacion.alexa.poc.service.dto.CursoUsuarioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CursoUsuario} and its DTO {@link CursoUsuarioDTO}.
 */
@Mapper(componentModel = "spring", uses = {CursoMapper.class, UsuarioMapper.class})
public interface CursoUsuarioMapper extends EntityMapper<CursoUsuarioDTO, CursoUsuario> {

    @Mapping(source = "curso.id", target = "cursoId")
    @Mapping(source = "usuario.id", target = "usuarioId")
    CursoUsuarioDTO toDto(CursoUsuario cursoUsuario);

    @Mapping(source = "cursoId", target = "curso")
    @Mapping(source = "usuarioId", target = "usuario")
    CursoUsuario toEntity(CursoUsuarioDTO cursoUsuarioDTO);

    default CursoUsuario fromId(Long id) {
        if (id == null) {
            return null;
        }
        CursoUsuario cursoUsuario = new CursoUsuario();
        cursoUsuario.setId(id);
        return cursoUsuario;
    }
}
