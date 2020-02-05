package com.experis.formacion.alexa.poc.service.mapper;

import com.experis.formacion.alexa.poc.domain.*;
import com.experis.formacion.alexa.poc.service.dto.UsuarioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Usuario} and its DTO {@link UsuarioDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UsuarioMapper extends EntityMapper<UsuarioDTO, Usuario> {


    @Mapping(target = "cursoUsuarios", ignore = true)
    @Mapping(target = "removeCursoUsuario", ignore = true)
    @Mapping(target = "habilidadUsuarios", ignore = true)
    @Mapping(target = "removeHabilidadUsuario", ignore = true)
    @Mapping(target = "interesUsuarios", ignore = true)
    @Mapping(target = "removeInteresUsuario", ignore = true)
    @Mapping(target = "planFormativoUsuarios", ignore = true)
    @Mapping(target = "removePlanFormativoUsuario", ignore = true)
    Usuario toEntity(UsuarioDTO usuarioDTO);

    default Usuario fromId(Long id) {
        if (id == null) {
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setId(id);
        return usuario;
    }
}
