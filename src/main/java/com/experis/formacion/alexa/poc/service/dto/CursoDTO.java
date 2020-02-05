package com.experis.formacion.alexa.poc.service.dto;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.experis.formacion.alexa.poc.domain.Curso} entity.
 */
public class CursoDTO implements Serializable {

    private Long id;

    @NotNull
    private String descripcion;

    private String descripcionLarga;

    @NotNull
    private LocalDate fechaInicio;

    @NotNull
    private LocalDate fechaFin;

    @NotNull
    @Pattern(regexp = "^(T|P|t|p)$")
    private String telematicoPresencial;

    private LocalTime hora;

    private String ubicacion;

    private Long numeroHoras;


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

    public String getTelematicoPresencial() {
        return telematicoPresencial;
    }

    public void setTelematicoPresencial(String telematicoPresencial) {
        this.telematicoPresencial = telematicoPresencial;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Long getNumeroHoras() {
        return numeroHoras;
    }

    public void setNumeroHoras(Long numeroHoras) {
        this.numeroHoras = numeroHoras;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CursoDTO cursoDTO = (CursoDTO) o;
        if (cursoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cursoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CursoDTO{" +
            "id=" + getId() +
            ", descripcion='" + getDescripcion() + "'" +
            ", descripcionLarga='" + getDescripcionLarga() + "'" +
            ", fechaInicio='" + getFechaInicio() + "'" +
            ", fechaFin='" + getFechaFin() + "'" +
            ", telematicoPresencial='" + getTelematicoPresencial() + "'" +
            ", hora='" + getHora() + "'" +
            ", ubicacion='" + getUbicacion() + "'" +
            ", numeroHoras=" + getNumeroHoras() +
            "}";
    }
}
