package com.experis.formacion.alexa.poc.service.mapper;

import com.experis.formacion.alexa.poc.domain.*;
import com.experis.formacion.alexa.poc.service.dto.TipoHabilidadDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TipoHabilidad} and its DTO {@link TipoHabilidadDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TipoHabilidadMapper extends EntityMapper<TipoHabilidadDTO, TipoHabilidad> {


    @Mapping(target = "habilidads", ignore = true)
    @Mapping(target = "removeHabilidad", ignore = true)
    TipoHabilidad toEntity(TipoHabilidadDTO tipoHabilidadDTO);

    default TipoHabilidad fromId(Long id) {
        if (id == null) {
            return null;
        }
        TipoHabilidad tipoHabilidad = new TipoHabilidad();
        tipoHabilidad.setId(id);
        return tipoHabilidad;
    }
}
