package com.experis.formacion.alexa.poc.service.mapper;

import com.experis.formacion.alexa.poc.domain.*;
import com.experis.formacion.alexa.poc.service.dto.NivelIdiomaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link NivelIdioma} and its DTO {@link NivelIdiomaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NivelIdiomaMapper extends EntityMapper<NivelIdiomaDTO, NivelIdioma> {


    @Mapping(target = "idiomaUsuarios", ignore = true)
    @Mapping(target = "removeIdiomaUsuario", ignore = true)
    NivelIdioma toEntity(NivelIdiomaDTO nivelIdiomaDTO);

    default NivelIdioma fromId(Long id) {
        if (id == null) {
            return null;
        }
        NivelIdioma nivelIdioma = new NivelIdioma();
        nivelIdioma.setId(id);
        return nivelIdioma;
    }
}
