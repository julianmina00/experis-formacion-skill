package com.experis.formacion.alexa.poc.service.mapper;

import com.experis.formacion.alexa.poc.domain.*;
import com.experis.formacion.alexa.poc.service.dto.CursoPlanFormativoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CursoPlanFormativo} and its DTO {@link CursoPlanFormativoDTO}.
 */
@Mapper(componentModel = "spring", uses = {CursoMapper.class, PlanFormativoMapper.class})
public interface CursoPlanFormativoMapper extends EntityMapper<CursoPlanFormativoDTO, CursoPlanFormativo> {

    @Mapping(source = "curso.id", target = "cursoId")
    @Mapping(source = "planFormativo.id", target = "planFormativoId")
    CursoPlanFormativoDTO toDto(CursoPlanFormativo cursoPlanFormativo);

    @Mapping(source = "cursoId", target = "curso")
    @Mapping(source = "planFormativoId", target = "planFormativo")
    CursoPlanFormativo toEntity(CursoPlanFormativoDTO cursoPlanFormativoDTO);

    default CursoPlanFormativo fromId(Long id) {
        if (id == null) {
            return null;
        }
        CursoPlanFormativo cursoPlanFormativo = new CursoPlanFormativo();
        cursoPlanFormativo.setId(id);
        return cursoPlanFormativo;
    }
}
