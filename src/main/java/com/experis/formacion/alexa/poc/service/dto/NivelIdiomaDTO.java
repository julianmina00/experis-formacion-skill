package com.experis.formacion.alexa.poc.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.experis.formacion.alexa.poc.domain.NivelIdioma} entity.
 */
public class NivelIdiomaDTO implements Serializable {

    private Long id;

    @NotNull
    private String nivel;

    private String descripcion;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NivelIdiomaDTO nivelIdiomaDTO = (NivelIdiomaDTO) o;
        if (nivelIdiomaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nivelIdiomaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NivelIdiomaDTO{" +
            "id=" + getId() +
            ", nivel='" + getNivel() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            "}";
    }
}
