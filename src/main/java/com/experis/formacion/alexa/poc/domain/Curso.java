package com.experis.formacion.alexa.poc.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Curso.
 */
@Entity
@Table(name = "cu_cursos")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cu_idcurso")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "cu_descripcion", nullable = false)
    private String descripcion;

    @Column(name = "cu_descripcionlarga")
    private String descripcionLarga;

    @NotNull
    @Column(name = "cu_fechaini", nullable = false)
    private LocalDate fechaInicio;

    @NotNull
    @Column(name = "cu_fechafin", nullable = false)
    private LocalDate fechaFin;

    @NotNull
    @Pattern(regexp = "^(T|P|t|p)$")
    @Column(name = "cu_telem_presenc", nullable = false)
    private String telematicoPresencial;

    @Column(name = "cu_hora")
    private LocalTime hora;

    @Column(name = "cu_ubicacion")
    private String ubicacion;

    @Column(name = "cu_numhoras")
    private Long numeroHoras;

    @OneToMany(mappedBy = "curso")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CursoPlanFormativo> cursoPlanFormativos = new HashSet<>();

    @OneToMany(mappedBy = "curso")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CursoUsuario> cursoUsuarios = new HashSet<>();

    @OneToMany(mappedBy = "curso")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ContenidoCurso> contenidoCursos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Curso descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcionLarga() {
        return descripcionLarga;
    }

    public Curso descripcionLarga(String descripcionLarga) {
        this.descripcionLarga = descripcionLarga;
        return this;
    }

    public void setDescripcionLarga(String descripcionLarga) {
        this.descripcionLarga = descripcionLarga;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public Curso fechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
        return this;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public Curso fechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
        return this;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getTelematicoPresencial() {
        return telematicoPresencial;
    }

    public Curso telematicoPresencial(String telematicoPresencial) {
        this.telematicoPresencial = telematicoPresencial;
        return this;
    }

    public void setTelematicoPresencial(String telematicoPresencial) {
        this.telematicoPresencial = telematicoPresencial;
    }

    public LocalTime getHora() {
        return hora;
    }

    public Curso hora(LocalTime hora) {
        this.hora = hora;
        return this;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public Curso ubicacion(String ubicacion) {
        this.ubicacion = ubicacion;
        return this;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Long getNumeroHoras() {
        return numeroHoras;
    }

    public Curso numeroHoras(Long numeroHoras) {
        this.numeroHoras = numeroHoras;
        return this;
    }

    public void setNumeroHoras(Long numeroHoras) {
        this.numeroHoras = numeroHoras;
    }

    public Set<CursoPlanFormativo> getCursoPlanFormativos() {
        return cursoPlanFormativos;
    }

    public Curso cursoPlanFormativos(Set<CursoPlanFormativo> cursoPlanFormativos) {
        this.cursoPlanFormativos = cursoPlanFormativos;
        return this;
    }

    public Curso addCursoPlanFormativo(CursoPlanFormativo cursoPlanFormativo) {
        this.cursoPlanFormativos.add(cursoPlanFormativo);
        cursoPlanFormativo.setCurso(this);
        return this;
    }

    public Curso removeCursoPlanFormativo(CursoPlanFormativo cursoPlanFormativo) {
        this.cursoPlanFormativos.remove(cursoPlanFormativo);
        cursoPlanFormativo.setCurso(null);
        return this;
    }

    public void setCursoPlanFormativos(Set<CursoPlanFormativo> cursoPlanFormativos) {
        this.cursoPlanFormativos = cursoPlanFormativos;
    }

    public Set<CursoUsuario> getCursoUsuarios() {
        return cursoUsuarios;
    }

    public Curso cursoUsuarios(Set<CursoUsuario> cursoUsuarios) {
        this.cursoUsuarios = cursoUsuarios;
        return this;
    }

    public Curso addCursoUsuario(CursoUsuario cursoUsuario) {
        this.cursoUsuarios.add(cursoUsuario);
        cursoUsuario.setCurso(this);
        return this;
    }

    public Curso removeCursoUsuario(CursoUsuario cursoUsuario) {
        this.cursoUsuarios.remove(cursoUsuario);
        cursoUsuario.setCurso(null);
        return this;
    }

    public void setCursoUsuarios(Set<CursoUsuario> cursoUsuarios) {
        this.cursoUsuarios = cursoUsuarios;
    }

    public Set<ContenidoCurso> getContenidoCursos() {
        return contenidoCursos;
    }

    public Curso contenidoCursos(Set<ContenidoCurso> contenidoCursos) {
        this.contenidoCursos = contenidoCursos;
        return this;
    }

    public Curso addContenidoCurso(ContenidoCurso contenidoCurso) {
        this.contenidoCursos.add(contenidoCurso);
        contenidoCurso.setCurso(this);
        return this;
    }

    public Curso removeContenidoCurso(ContenidoCurso contenidoCurso) {
        this.contenidoCursos.remove(contenidoCurso);
        contenidoCurso.setCurso(null);
        return this;
    }

    public void setContenidoCursos(Set<ContenidoCurso> contenidoCursos) {
        this.contenidoCursos = contenidoCursos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Curso)) {
            return false;
        }
        return id != null && id.equals(((Curso) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Curso{" +
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
