package com.experis.formacion.alexa.poc.service.mapper;

import com.experis.formacion.alexa.poc.domain.*;
import com.experis.formacion.alexa.poc.service.dto.IdiomaUsuarioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link IdiomaUsuario} and its DTO {@link IdiomaUsuarioDTO}.
 */
@Mapper(componentModel = "spring", uses = {UsuarioMapper.class, NivelIdiomaMapper.class})
public interface IdiomaUsuarioMapper extends EntityMapper<IdiomaUsuarioDTO, IdiomaUsuario> {

    @Mapping(source = "usuario.id", target = "usuarioId")
    @Mapping(source = "nivelIdioma.id", target = "nivelIdiomaId")
    IdiomaUsuarioDTO toDto(IdiomaUsuario idiomaUsuario);

    @Mapping(source = "usuarioId", target = "usuario")
    @Mapping(source = "nivelIdiomaId", target = "nivelIdioma")
    IdiomaUsuario toEntity(IdiomaUsuarioDTO idiomaUsuarioDTO);

    default IdiomaUsuario fromId(Long id) {
        if (id == null) {
            return null;
        }
        IdiomaUsuario idiomaUsuario = new IdiomaUsuario();
        idiomaUsuario.setId(id);
        return idiomaUsuario;
    }
}
