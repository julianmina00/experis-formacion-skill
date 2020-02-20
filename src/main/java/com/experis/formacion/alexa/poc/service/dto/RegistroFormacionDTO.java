package com.experis.formacion.alexa.poc.service.dto;

import java.util.Objects;

public class RegistroFormacionDTO {

    private Long id;
    private String tipoFormacion;
    private Long usuarioId;
    private Long formacionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoFormacion() {
        return tipoFormacion;
    }

    public void setTipoFormacion(String tipoFormacion) {
        this.tipoFormacion = tipoFormacion;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getFormacionId() {
        return formacionId;
    }

    public void setFormacionId(Long formacionId) {
        this.formacionId = formacionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RegistroFormacionDTO that = (RegistroFormacionDTO) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(tipoFormacion, that.tipoFormacion) &&
            Objects.equals(usuarioId, that.usuarioId) &&
            Objects.equals(formacionId, that.formacionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipoFormacion, usuarioId, formacionId);
    }

    @Override
    public String toString() {
        return "RegistroFormacionDTO{" +
            "id=" + id +
            ", tipoFormacion='" + tipoFormacion + '\'' +
            ", usuarioId=" + usuarioId +
            ", formacionId=" + formacionId +
            '}';
    }
}
