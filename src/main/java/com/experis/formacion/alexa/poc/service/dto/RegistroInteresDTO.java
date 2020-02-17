package com.experis.formacion.alexa.poc.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.experis.formacion.alexa.poc.domain.HabilidadUsuario} entity.
 */
public class RegistroInteresDTO implements Serializable {

    private String tipoPrincipal;
    private String tipoSecundario;
    private String interes;
    private Long usuarioId;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getTipoPrincipal() {
        return tipoPrincipal;
    }

    public void setTipoPrincipal(String tipoPrincipal) {
        this.tipoPrincipal = tipoPrincipal;
    }

    public String getTipoSecundario() {
        return tipoSecundario;
    }

    public void setTipoSecundario(String tipoSecundario) {
        this.tipoSecundario = tipoSecundario;
    }

    public String getInteres() {
        return interes;
    }

    public void setInteres(String interes) {
        this.interes = interes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RegistroInteresDTO that = (RegistroInteresDTO) o;
        return Objects.equals(tipoPrincipal, that.tipoPrincipal) &&
            Objects.equals(tipoSecundario, that.tipoSecundario) &&
            Objects.equals(interes, that.interes) &&
            Objects.equals(usuarioId, that.usuarioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipoPrincipal, tipoSecundario, interes, usuarioId);
    }

    @Override
    public String toString() {
        return "RegistroHabilidadDTO{" +
            "grupoGeneral='" + tipoPrincipal + '\'' +
            ", grupoSecundario='" + tipoSecundario + '\'' +
            ", habilidad='" + interes + '\'' +
            ", usuarioId=" + usuarioId +
            '}';
    }
}
