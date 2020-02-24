package com.experis.formacion.alexa.poc.service.mapper;

import com.experis.formacion.alexa.poc.domain.Curso;
import com.experis.formacion.alexa.poc.domain.PlanFormativo;
import com.experis.formacion.alexa.poc.service.dto.CursoDTO;
import com.experis.formacion.alexa.poc.service.dto.FormacionesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Curso} and its DTO {@link CursoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FormacionesPlanMapper extends EntityMapper<FormacionesDTO, PlanFormativo> {

    @Mapping(target = "tipoFormacion", constant = "P")
    @Mapping(target = "hora", ignore = true)
    @Mapping(target = "numeroHoras", ignore = true)
    @Mapping(target = "telematicoPresencial", ignore = true)
    @Mapping(target = "ubicacion", ignore = true)
    @Mapping(target = "idioma", ignore = true)
    @Mapping(target = "cursos", ignore = true)
    FormacionesDTO toDto(PlanFormativo planFormativo);

    @Mapping(target = "cursoPlanFormativos", ignore = true)
    @Mapping(target = "removeCursoPlanFormativo", ignore = true)
    @Mapping(target = "perfilPlanFormativos", ignore = true)
    @Mapping(target = "removePerfilPlanFormativo", ignore = true)
    @Mapping(target = "planFormativoUsuarios", ignore = true)
    @Mapping(target = "removePlanFormativoUsuario", ignore = true)
    PlanFormativo toEntity(FormacionesDTO formacionDTO);

    default PlanFormativo fromId(Long id) {
        if (id == null) {
            return null;
        }
        PlanFormativo planFormativo = new PlanFormativo();
        planFormativo.setId(id);
        return planFormativo;
    }
}
