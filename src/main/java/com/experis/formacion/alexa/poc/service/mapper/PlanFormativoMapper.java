package com.experis.formacion.alexa.poc.service.mapper;

import com.experis.formacion.alexa.poc.domain.*;
import com.experis.formacion.alexa.poc.service.dto.PlanFormativoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PlanFormativo} and its DTO {@link PlanFormativoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PlanFormativoMapper extends EntityMapper<PlanFormativoDTO, PlanFormativo> {


    @Mapping(target = "cursoPlanFormativos", ignore = true)
    @Mapping(target = "removeCursoPlanFormativo", ignore = true)
    @Mapping(target = "perfilPlanFormativos", ignore = true)
    @Mapping(target = "removePerfilPlanFormativo", ignore = true)
    @Mapping(target = "planFormativoUsuarios", ignore = true)
    @Mapping(target = "removePlanFormativoUsuario", ignore = true)
    PlanFormativo toEntity(PlanFormativoDTO planFormativoDTO);

    default PlanFormativo fromId(Long id) {
        if (id == null) {
            return null;
        }
        PlanFormativo planFormativo = new PlanFormativo();
        planFormativo.setId(id);
        return planFormativo;
    }
}
