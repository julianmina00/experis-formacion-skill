package com.experis.formacion.alexa.poc.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.experis.formacion.alexa.poc.domain.Interes} entity.
 */
public class InteresDTO implements Serializable {

    private Long id;

    @NotNull
    private String descripcion;

    private String descripcionLarga;


    private Long tipoInteresId;

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

    public Long getTipoInteresId() {
        return tipoInteresId;
    }

    public void setTipoInteresId(Long tipoInteresId) {
        this.tipoInteresId = tipoInteresId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InteresDTO interesDTO = (InteresDTO) o;
        if (interesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), interesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InteresDTO{" +
            "id=" + getId() +
            ", descripcion='" + getDescripcion() + "'" +
            ", descripcionLarga='" + getDescripcionLarga() + "'" +
            ", tipoInteresId=" + getTipoInteresId() +
            "}";
    }
}
