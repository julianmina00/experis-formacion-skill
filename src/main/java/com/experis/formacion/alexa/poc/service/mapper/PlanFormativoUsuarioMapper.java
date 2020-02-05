package com.experis.formacion.alexa.poc.service.mapper;

import com.experis.formacion.alexa.poc.domain.*;
import com.experis.formacion.alexa.poc.service.dto.PlanFormativoUsuarioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PlanFormativoUsuario} and its DTO {@link PlanFormativoUsuarioDTO}.
 */
@Mapper(componentModel = "spring", uses = {PlanFormativoMapper.class, UsuarioMapper.class})
public interface PlanFormativoUsuarioMapper extends EntityMapper<PlanFormativoUsuarioDTO, PlanFormativoUsuario> {

    @Mapping(source = "planFormativo.id", target = "planFormativoId")
    @Mapping(source = "usuario.id", target = "usuarioId")
    PlanFormativoUsuarioDTO toDto(PlanFormativoUsuario planFormativoUsuario);

    @Mapping(source = "planFormativoId", target = "planFormativo")
    @Mapping(source = "usuarioId", target = "usuario")
    PlanFormativoUsuario toEntity(PlanFormativoUsuarioDTO planFormativoUsuarioDTO);

    default PlanFormativoUsuario fromId(Long id) {
        if (id == null) {
            return null;
        }
        PlanFormativoUsuario planFormativoUsuario = new PlanFormativoUsuario();
        planFormativoUsuario.setId(id);
        return planFormativoUsuario;
    }
}
