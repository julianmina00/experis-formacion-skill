package com.experis.formacion.alexa.poc.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.experis.formacion.alexa.poc.domain.IdiomaUsuario} entity.
 */
public class IdiomaUsuarioDTO implements Serializable {

    private Long id;

    @NotNull
    private String idioma;


    private Long usuarioId;

    private Long nivelIdiomaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getNivelIdiomaId() {
        return nivelIdiomaId;
    }

    public void setNivelIdiomaId(Long nivelIdiomaId) {
        this.nivelIdiomaId = nivelIdiomaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        IdiomaUsuarioDTO idiomaUsuarioDTO = (IdiomaUsuarioDTO) o;
        if (idiomaUsuarioDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), idiomaUsuarioDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "IdiomaUsuarioDTO{" +
            "id=" + getId() +
            ", idioma='" + getIdioma() + "'" +
            ", usuarioId=" + getUsuarioId() +
            ", nivelIdiomaId=" + getNivelIdiomaId() +
            "}";
    }
}
