package com.experis.formacion.alexa.poc.service.mapper;

import com.experis.formacion.alexa.poc.domain.*;
import com.experis.formacion.alexa.poc.service.dto.HabilidadUsuarioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link HabilidadUsuario} and its DTO {@link HabilidadUsuarioDTO}.
 */
@Mapper(componentModel = "spring", uses = {HabilidadMapper.class, UsuarioMapper.class})
public interface HabilidadUsuarioMapper extends EntityMapper<HabilidadUsuarioDTO, HabilidadUsuario> {

    @Mapping(source = "habilidad.id", target = "habilidadId")
    @Mapping(source = "usuario.id", target = "usuarioId")
    HabilidadUsuarioDTO toDto(HabilidadUsuario habilidadUsuario);

    @Mapping(source = "habilidadId", target = "habilidad")
    @Mapping(source = "usuarioId", target = "usuario")
    HabilidadUsuario toEntity(HabilidadUsuarioDTO habilidadUsuarioDTO);

    default HabilidadUsuario fromId(Long id) {
        if (id == null) {
            return null;
        }
        HabilidadUsuario habilidadUsuario = new HabilidadUsuario();
        habilidadUsuario.setId(id);
        return habilidadUsuario;
    }
}
