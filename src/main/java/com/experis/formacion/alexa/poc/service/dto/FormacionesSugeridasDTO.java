package com.experis.formacion.alexa.poc.service.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class FormacionesSugeridasDTO extends FormacionesDTO implements Serializable {

    private List<String> habilidadesRelacionadas;
    private List<String> interesesRelacionados;

    public List<String> getHabilidadesRelacionadas() {
        return habilidadesRelacionadas;
    }

    public void setHabilidadesRelacionadas(List<String> habilidadesRelacionadas) {
        this.habilidadesRelacionadas = habilidadesRelacionadas;
    }

    public List<String> getInteresesRelacionados() {
        return interesesRelacionados;
    }

    public void setInteresesRelacionados(List<String> interesesRelacionados) {
        this.interesesRelacionados = interesesRelacionados;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FormacionesSugeridasDTO that = (FormacionesSugeridasDTO) o;
        return Objects.equals(habilidadesRelacionadas, that.habilidadesRelacionadas) &&
            Objects.equals(interesesRelacionados, that.interesesRelacionados);
    }

    @Override
    public int hashCode() {
        return Objects
            .hash(habilidadesRelacionadas,interesesRelacionados);
    }

    @Override
    public String toString() {
        return "FormacionesSugeridasDTO{" +
            ", habilidadesRelacionadas=" + habilidadesRelacionadas +
            ", interesesRelacionados=" + interesesRelacionados +
            '}';
    }
}
