package com.experis.formacion.alexa.poc.service.mapper;

import com.experis.formacion.alexa.poc.domain.*;
import com.experis.formacion.alexa.poc.service.dto.RelacionTipoInteresDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RelacionTipoInteres} and its DTO {@link RelacionTipoInteresDTO}.
 */
@Mapper(componentModel = "spring", uses = {TipoInteresMapper.class})
public interface RelacionTipoInteresMapper extends EntityMapper<RelacionTipoInteresDTO, RelacionTipoInteres> {

    @Mapping(source = "padre.id", target = "padreId")
    @Mapping(source = "hija.id", target = "hijaId")
    RelacionTipoInteresDTO toDto(RelacionTipoInteres relacionTipoInteres);

    @Mapping(source = "padreId", target = "padre")
    @Mapping(source = "hijaId", target = "hija")
    RelacionTipoInteres toEntity(RelacionTipoInteresDTO relacionTipoInteresDTO);

    default RelacionTipoInteres fromId(Long id) {
        if (id == null) {
            return null;
        }
        RelacionTipoInteres relacionTipoInteres = new RelacionTipoInteres();
        relacionTipoInteres.setId(id);
        return relacionTipoInteres;
    }
}
