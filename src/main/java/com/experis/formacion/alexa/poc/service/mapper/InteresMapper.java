package com.experis.formacion.alexa.poc.service.mapper;

import com.experis.formacion.alexa.poc.domain.*;
import com.experis.formacion.alexa.poc.service.dto.InteresDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Interes} and its DTO {@link InteresDTO}.
 */
@Mapper(componentModel = "spring", uses = {TipoInteresMapper.class})
public interface InteresMapper extends EntityMapper<InteresDTO, Interes> {

    @Mapping(source = "tipoInteres.id", target = "tipoInteresId")
    InteresDTO toDto(Interes interes);

    @Mapping(target = "perfilPlanFormativos", ignore = true)
    @Mapping(target = "removePerfilPlanFormativo", ignore = true)
    @Mapping(target = "interesUsuarios", ignore = true)
    @Mapping(target = "removeInteresUsuario", ignore = true)
    @Mapping(target = "contenidoCursos", ignore = true)
    @Mapping(target = "removeContenidoCurso", ignore = true)
    @Mapping(source = "tipoInteresId", target = "tipoInteres")
    Interes toEntity(InteresDTO interesDTO);

    default Interes fromId(Long id) {
        if (id == null) {
            return null;
        }
        Interes interes = new Interes();
        interes.setId(id);
        return interes;
    }
}
