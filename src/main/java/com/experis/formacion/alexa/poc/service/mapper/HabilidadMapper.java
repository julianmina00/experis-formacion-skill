package com.experis.formacion.alexa.poc.service.mapper;

import com.experis.formacion.alexa.poc.domain.*;
import com.experis.formacion.alexa.poc.service.dto.HabilidadDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Habilidad} and its DTO {@link HabilidadDTO}.
 */
@Mapper(componentModel = "spring", uses = {TipoHabilidadMapper.class})
public interface HabilidadMapper extends EntityMapper<HabilidadDTO, Habilidad> {

    @Mapping(source = "tipoHabilidad.id", target = "tipoHabilidadId")
    HabilidadDTO toDto(Habilidad habilidad);

    @Mapping(target = "perfilPlanFormativos", ignore = true)
    @Mapping(target = "removePerfilPlanFormativo", ignore = true)
    @Mapping(target = "habilidadUsuarios", ignore = true)
    @Mapping(target = "removeHabilidadUsuario", ignore = true)
    @Mapping(target = "contenidoCursos", ignore = true)
    @Mapping(target = "removeContenidoCurso", ignore = true)
    @Mapping(source = "tipoHabilidadId", target = "tipoHabilidad")
    Habilidad toEntity(HabilidadDTO habilidadDTO);

    default Habilidad fromId(Long id) {
        if (id == null) {
            return null;
        }
        Habilidad habilidad = new Habilidad();
        habilidad.setId(id);
        return habilidad;
    }
}
