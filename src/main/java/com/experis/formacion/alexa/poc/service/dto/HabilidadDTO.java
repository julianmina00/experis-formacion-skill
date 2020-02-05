package com.experis.formacion.alexa.poc.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.experis.formacion.alexa.poc.domain.Habilidad} entity.
 */
public class HabilidadDTO implements Serializable {

    private Long id;

    @NotNull
    private String descripcion;

    private String descripcionLarga;


    private Long tipoHabilidadId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcionLarga() {
        return descripcionLarga;
    }

    public void setDescripcionLarga(String descripcionLarga) {
        this.descripcionLarga = descripcionLarga;
    }

    public Long getTipoHabilidadId() {
        return tipoHabilidadId;
    }

    public void setTipoHabilidadId(Long tipoHabilidadId) {
        this.tipoHabilidadId = tipoHabilidadId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HabilidadDTO habilidadDTO = (HabilidadDTO) o;
        if (habilidadDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), habilidadDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HabilidadDTO{" +
            "id=" + getId() +
            ", descripcion='" + getDescripcion() + "'" +
            ", descripcionLarga='" + getDescripcionLarga() + "'" +
            ", tipoHabilidadId=" + getTipoHabilidadId() +
            "}";
    }
}
