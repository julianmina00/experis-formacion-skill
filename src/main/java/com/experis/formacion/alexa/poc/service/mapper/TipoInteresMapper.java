package com.experis.formacion.alexa.poc.service.mapper;

import com.experis.formacion.alexa.poc.domain.*;
import com.experis.formacion.alexa.poc.service.dto.TipoInteresDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TipoInteres} and its DTO {@link TipoInteresDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TipoInteresMapper extends EntityMapper<TipoInteresDTO, TipoInteres> {


    @Mapping(target = "interes", ignore = true)
    @Mapping(target = "removeInteres", ignore = true)
    TipoInteres toEntity(TipoInteresDTO tipoInteresDTO);

    default TipoInteres fromId(Long id) {
        if (id == null) {
            return null;
        }
        TipoInteres tipoInteres = new TipoInteres();
        tipoInteres.setId(id);
        return tipoInteres;
    }
}
