package com.experis.formacion.alexa.poc.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.experis.formacion.alexa.poc.domain.HabilidadUsuario} entity.
 */
public class HabilidadUsuarioDTO implements Serializable {

    private Long id;


    private Long habilidadId;

    private Long usuarioId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHabilidadId() {
        return habilidadId;
    }

    public void setHabilidadId(Long habilidadId) {
        this.habilidadId = habilidadId;
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

        HabilidadUsuarioDTO habilidadUsuarioDTO = (HabilidadUsuarioDTO) o;
        if (habilidadUsuarioDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), habilidadUsuarioDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HabilidadUsuarioDTO{" +
            "id=" + getId() +
            ", habilidadId=" + getHabilidadId() +
            ", usuarioId=" + getUsuarioId() +
            "}";
    }
}
