package com.experis.formacion.alexa.poc.service.mapper;

import com.experis.formacion.alexa.poc.domain.*;
import com.experis.formacion.alexa.poc.service.dto.InteresUsuarioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link InteresUsuario} and its DTO {@link InteresUsuarioDTO}.
 */
@Mapper(componentModel = "spring", uses = {InteresMapper.class, UsuarioMapper.class})
public interface InteresUsuarioMapper extends EntityMapper<InteresUsuarioDTO, InteresUsuario> {

    @Mapping(source = "interes.id", target = "interesId")
    @Mapping(source = "usuario.id", target = "usuarioId")
    InteresUsuarioDTO toDto(InteresUsuario interesUsuario);

    @Mapping(source = "interesId", target = "interes")
    @Mapping(source = "usuarioId", target = "usuario")
    InteresUsuario toEntity(InteresUsuarioDTO interesUsuarioDTO);

    default InteresUsuario fromId(Long id) {
        if (id == null) {
            return null;
        }
        InteresUsuario interesUsuario = new InteresUsuario();
        interesUsuario.setId(id);
        return interesUsuario;
    }
}
