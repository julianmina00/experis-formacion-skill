package com.experis.formacion.alexa.poc.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

public class FormacionesDTO implements Serializable {

    private Long id;
    private String tipoFormacion;
    private String descripcion;
    private String descripcionLarga;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalTime hora;
    private Long numeroHoras;
    private String telematicoPresencial;
    private String ubicacion;
    private String idioma;
    private List<String> cursos;

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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Long getNumeroHoras() {
        return numeroHoras;
    }

    public void setNumeroHoras(Long numeroHoras) {
        this.numeroHoras = numeroHoras;
    }

    public String getTelematicoPresencial() {
        return telematicoPresencial;
    }

    public void setTelematicoPresencial(String telematicoPresencial) {
        this.telematicoPresencial = telematicoPresencial;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public List<String> getCursos() {
        return cursos;
    }

    public void setCursos(List<String> cursos) {
        this.cursos = cursos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FormacionesDTO that = (FormacionesDTO) o;
        return telematicoPresencial == that.telematicoPresencial &&
            Objects.equals(id, that.id) &&
            Objects.equals(tipoFormacion, that.tipoFormacion) &&
            Objects.equals(descripcion, that.descripcion) &&
            Objects.equals(descripcionLarga, that.descripcionLarga) &&
            Objects.equals(fechaInicio, that.fechaInicio) &&
            Objects.equals(fechaFin, that.fechaFin) &&
            Objects.equals(hora, that.hora) &&
            Objects.equals(numeroHoras, that.numeroHoras) &&
            Objects.equals(ubicacion, that.ubicacion) &&
            Objects.equals(idioma, that.idioma) &&
            Objects.equals(cursos, that.cursos);
    }

    @Override
    public int hashCode() {
        return Objects
            .hash(id, tipoFormacion, descripcion, descripcionLarga, fechaInicio, fechaFin, hora,
                numeroHoras, telematicoPresencial, ubicacion, idioma, cursos);
    }

    @Override
    public String toString() {
        return "FormacionesSugeridasDTO{" +
            "id=" + id +
            ", tipoFormacion='" + tipoFormacion + '\'' +
            ", descripcion='" + descripcion + '\'' +
            ", descripcionLarga='" + descripcionLarga + '\'' +
            ", fechaInicio=" + fechaInicio +
            ", fechaFin=" + fechaFin +
            ", hora=" + hora +
            ", numeroDeHoras=" + numeroHoras +
            ", esPresencial=" + telematicoPresencial +
            ", ubicacion='" + ubicacion + '\'' +
            ", idioma='" + idioma + '\'' +
            ", cursos=" + cursos +
            '}';
    }
}
