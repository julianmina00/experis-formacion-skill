package com.experis.formacion.alexa.poc.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.experis.formacion.alexa.poc.domain.CursoPlanFormativo} entity.
 */
public class CursoPlanFormativoDTO implements Serializable {

    private Long id;


    private Long cursoId;

    private Long planFormativoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }

    public Long getPlanFormativoId() {
        return planFormativoId;
    }

    public void setPlanFormativoId(Long planFormativoId) {
        this.planFormativoId = planFormativoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CursoPlanFormativoDTO cursoPlanFormativoDTO = (CursoPlanFormativoDTO) o;
        if (cursoPlanFormativoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cursoPlanFormativoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CursoPlanFormativoDTO{" +
            "id=" + getId() +
            ", cursoId=" + getCursoId() +
            ", planFormativoId=" + getPlanFormativoId() +
            "}";
    }
}
