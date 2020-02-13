package com.experis.formacion.alexa.poc.service.mapper;

import com.experis.formacion.alexa.poc.domain.*;
import com.experis.formacion.alexa.poc.service.dto.RelacionTipoHabilidadDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RelacionTipoHabilidad} and its DTO {@link RelacionTipoHabilidadDTO}.
 */
@Mapper(componentModel = "spring", uses = {TipoHabilidadMapper.class})
public interface RelacionTipoHabilidadMapper extends EntityMapper<RelacionTipoHabilidadDTO, RelacionTipoHabilidad> {

    @Mapping(source = "padre.id", target = "padreId")
    @Mapping(source = "hija.id", target = "hijaId")
    RelacionTipoHabilidadDTO toDto(RelacionTipoHabilidad relacionTipoHabilidad);

    @Mapping(source = "padreId", target = "padre")
    @Mapping(source = "hijaId", target = "hija")
    RelacionTipoHabilidad toEntity(RelacionTipoHabilidadDTO relacionTipoHabilidadDTO);

    default RelacionTipoHabilidad fromId(Long id) {
        if (id == null) {
            return null;
        }
        RelacionTipoHabilidad relacionTipoHabilidad = new RelacionTipoHabilidad();
        relacionTipoHabilidad.setId(id);
        return relacionTipoHabilidad;
    }
}
