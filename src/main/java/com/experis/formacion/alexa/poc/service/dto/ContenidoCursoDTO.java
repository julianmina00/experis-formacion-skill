package com.experis.formacion.alexa.poc.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.experis.formacion.alexa.poc.domain.ContenidoCurso} entity.
 */
public class ContenidoCursoDTO implements Serializable {

    private Long id;

    @NotNull
    @Pattern(regexp = "^(H|I|h|i)$")
    private String habilidadInteres;


    private Long cursoId;

    private Long interesId;

    private Long habilidadId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHabilidadInteres() {
        return habilidadInteres;
    }

    public void setHabilidadInteres(String habilidadInteres) {
        this.habilidadInteres = habilidadInteres;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }

    public Long getInteresId() {
        return interesId;
    }

    public void setInteresId(Long interesId) {
        this.interesId = interesId;
    }

    public Long getHabilidadId() {
        return habilidadId;
    }

    public void setHabilidadId(Long habilidadId) {
        this.habilidadId = habilidadId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ContenidoCursoDTO contenidoCursoDTO = (ContenidoCursoDTO) o;
        if (contenidoCursoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contenidoCursoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ContenidoCursoDTO{" +
            "id=" + getId() +
            ", habilidadInteres='" + getHabilidadInteres() + "'" +
            ", cursoId=" + getCursoId() +
            ", interesId=" + getInteresId() +
            ", habilidadId=" + getHabilidadId() +
            "}";
    }
}
