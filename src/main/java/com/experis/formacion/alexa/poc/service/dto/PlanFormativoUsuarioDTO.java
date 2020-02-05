package com.experis.formacion.alexa.poc.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.experis.formacion.alexa.poc.domain.PlanFormativoUsuario} entity.
 */
public class PlanFormativoUsuarioDTO implements Serializable {

    private Long id;


    private Long planFormativoId;

    private Long usuarioId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlanFormativoId() {
        return planFormativoId;
    }

    public void setPlanFormativoId(Long planFormativoId) {
        this.planFormativoId = planFormativoId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PlanFormativoUsuarioDTO planFormativoUsuarioDTO = (PlanFormativoUsuarioDTO) o;
        if (planFormativoUsuarioDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), planFormativoUsuarioDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PlanFormativoUsuarioDTO{" +
            "id=" + getId() +
            ", planFormativoId=" + getPlanFormativoId() +
            ", usuarioId=" + getUsuarioId() +
            "}";
    }
}
