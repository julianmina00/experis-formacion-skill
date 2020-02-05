package com.experis.formacion.alexa.poc.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.experis.formacion.alexa.poc.domain.PerfilPlanFormativo} entity.
 */
public class PerfilPlanFormativoDTO implements Serializable {

    private Long id;

    @NotNull
    @Pattern(regexp = "^(H|I|h|i)$")
    private String interesHabilidad;


    private Long habilidadId;

    private Long interesId;

    private Long planFormativoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInteresHabilidad() {
        return interesHabilidad;
    }

    public void setInteresHabilidad(String interesHabilidad) {
        this.interesHabilidad = interesHabilidad;
    }

    public Long getHabilidadId() {
        return habilidadId;
    }

    public void setHabilidadId(Long habilidadId) {
        this.habilidadId = habilidadId;
    }

    public Long getInteresId() {
        return interesId;
    }

    public void setInteresId(Long interesId) {
        this.interesId = interesId;
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

        PerfilPlanFormativoDTO perfilPlanFormativoDTO = (PerfilPlanFormativoDTO) o;
        if (perfilPlanFormativoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), perfilPlanFormativoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PerfilPlanFormativoDTO{" +
            "id=" + getId() +
            ", interesHabilidad='" + getInteresHabilidad() + "'" +
            ", habilidadId=" + getHabilidadId() +
            ", interesId=" + getInteresId() +
            ", planFormativoId=" + getPlanFormativoId() +
            "}";
    }
}
