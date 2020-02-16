package com.experis.formacion.alexa.poc.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.experis.formacion.alexa.poc.domain.HabilidadUsuario} entity.
 */
public class RegistroHabilidadDTO implements Serializable {

    private String grupoGeneral;
    private String grupoSecundario;
    private String habilidad;
    private Long usuarioId;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getGrupoGeneral() {
        return grupoGeneral;
    }

    public void setGrupoGeneral(String grupoGeneral) {
        this.grupoGeneral = grupoGeneral;
    }

    public String getGrupoSecundario() {
        return grupoSecundario;
    }

    public void setGrupoSecundario(String grupoSecundario) {
        this.grupoSecundario = grupoSecundario;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RegistroHabilidadDTO that = (RegistroHabilidadDTO) o;
        return Objects.equals(grupoGeneral, that.grupoGeneral) &&
            Objects.equals(grupoSecundario, that.grupoSecundario) &&
            Objects.equals(habilidad, that.habilidad) &&
            Objects.equals(usuarioId, that.usuarioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grupoGeneral, grupoSecundario, habilidad, usuarioId);
    }

    @Override
    public String toString() {
        return "RegistroHabilidadDTO{" +
            "grupoGeneral='" + grupoGeneral + '\'' +
            ", grupoSecundario='" + grupoSecundario + '\'' +
            ", habilidad='" + habilidad + '\'' +
            ", usuarioId=" + usuarioId +
            '}';
    }
}
