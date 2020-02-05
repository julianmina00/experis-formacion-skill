package com.experis.formacion.alexa.poc.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.experis.formacion.alexa.poc.domain.InteresUsuario} entity.
 */
public class InteresUsuarioDTO implements Serializable {

    private Long id;


    private Long interesId;

    private Long usuarioId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInteresId() {
        return interesId;
    }

    public void setInteresId(Long interesId) {
        this.interesId = interesId;
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

        InteresUsuarioDTO interesUsuarioDTO = (InteresUsuarioDTO) o;
        if (interesUsuarioDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), interesUsuarioDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InteresUsuarioDTO{" +
            "id=" + getId() +
            ", interesId=" + getInteresId() +
            ", usuarioId=" + getUsuarioId() +
            "}";
    }
}
