package com.experis.formacion.alexa.poc.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.experis.formacion.alexa.poc.domain.CursoUsuario} entity.
 */
public class CursoUsuarioDTO implements Serializable {

    private Long id;


    private Long cursoId;

    private Long usuarioId;

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

        CursoUsuarioDTO cursoUsuarioDTO = (CursoUsuarioDTO) o;
        if (cursoUsuarioDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cursoUsuarioDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CursoUsuarioDTO{" +
            "id=" + getId() +
            ", cursoId=" + getCursoId() +
            ", usuarioId=" + getUsuarioId() +
            "}";
    }
}
