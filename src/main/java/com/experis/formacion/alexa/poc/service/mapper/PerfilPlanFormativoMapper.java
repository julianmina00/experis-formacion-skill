package com.experis.formacion.alexa.poc.service.mapper;

import com.experis.formacion.alexa.poc.domain.*;
import com.experis.formacion.alexa.poc.service.dto.PerfilPlanFormativoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PerfilPlanFormativo} and its DTO {@link PerfilPlanFormativoDTO}.
 */
@Mapper(componentModel = "spring", uses = {HabilidadMapper.class, InteresMapper.class, PlanFormativoMapper.class})
public interface PerfilPlanFormativoMapper extends EntityMapper<PerfilPlanFormativoDTO, PerfilPlanFormativo> {

    @Mapping(source = "habilidad.id", target = "habilidadId")
    @Mapping(source = "interes.id", target = "interesId")
    @Mapping(source = "planFormativo.id", target = "planFormativoId")
    PerfilPlanFormativoDTO toDto(PerfilPlanFormativo perfilPlanFormativo);

    @Mapping(source = "habilidadId", target = "habilidad")
    @Mapping(source = "interesId", target = "interes")
    @Mapping(source = "planFormativoId", target = "planFormativo")
    PerfilPlanFormativo toEntity(PerfilPlanFormativoDTO perfilPlanFormativoDTO);

    default PerfilPlanFormativo fromId(Long id) {
        if (id == null) {
            return null;
        }
        PerfilPlanFormativo perfilPlanFormativo = new PerfilPlanFormativo();
        perfilPlanFormativo.setId(id);
        return perfilPlanFormativo;
    }
}
