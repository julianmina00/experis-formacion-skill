package com.experis.formacion.alexa.poc.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Habilidad.
 */
@Entity
@Table(name = "CH_CatalogoHabilidad")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Habilidad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CH_IdHabilidad")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "CH_DescHabilidad", nullable = false)
    private String descripcion;

    @Column(name = "CH_DescLargHabilidad")
    private String descripcionLarga;

    @OneToMany(mappedBy = "habilidad")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PerfilPlanFormativo> perfilPlanFormativos = new HashSet<>();

    @OneToMany(mappedBy = "habilidad")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<HabilidadUsuario> habilidadUsuarios = new HashSet<>();

    @OneToMany(mappedBy = "habilidad")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ContenidoCurso> contenidoCursos = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("habilidads")
    @JoinColumn(name = "CH_TipHabilidad")
    private TipoHabilidad tipoHabilidad;

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

    public Habilidad descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcionLarga() {
        return descripcionLarga;
    }

    public Habilidad descripcionLarga(String descripcionLarga) {
        this.descripcionLarga = descripcionLarga;
        return this;
    }

    public void setDescripcionLarga(String descripcionLarga) {
        this.descripcionLarga = descripcionLarga;
    }

    public Set<PerfilPlanFormativo> getPerfilPlanFormativos() {
        return perfilPlanFormativos;
    }

    public Habilidad perfilPlanFormativos(Set<PerfilPlanFormativo> perfilPlanFormativos) {
        this.perfilPlanFormativos = perfilPlanFormativos;
        return this;
    }

    public Habilidad addPerfilPlanFormativo(PerfilPlanFormativo perfilPlanFormativo) {
        this.perfilPlanFormativos.add(perfilPlanFormativo);
        perfilPlanFormativo.setHabilidad(this);
        return this;
    }

    public Habilidad removePerfilPlanFormativo(PerfilPlanFormativo perfilPlanFormativo) {
        this.perfilPlanFormativos.remove(perfilPlanFormativo);
        perfilPlanFormativo.setHabilidad(null);
        return this;
    }

    public void setPerfilPlanFormativos(Set<PerfilPlanFormativo> perfilPlanFormativos) {
        this.perfilPlanFormativos = perfilPlanFormativos;
    }

    public Set<HabilidadUsuario> getHabilidadUsuarios() {
        return habilidadUsuarios;
    }

    public Habilidad habilidadUsuarios(Set<HabilidadUsuario> habilidadUsuarios) {
        this.habilidadUsuarios = habilidadUsuarios;
        return this;
    }

    public Habilidad addHabilidadUsuario(HabilidadUsuario habilidadUsuario) {
        this.habilidadUsuarios.add(habilidadUsuario);
        habilidadUsuario.setHabilidad(this);
        return this;
    }

    public Habilidad removeHabilidadUsuario(HabilidadUsuario habilidadUsuario) {
        this.habilidadUsuarios.remove(habilidadUsuario);
        habilidadUsuario.setHabilidad(null);
        return this;
    }

    public void setHabilidadUsuarios(Set<HabilidadUsuario> habilidadUsuarios) {
        this.habilidadUsuarios = habilidadUsuarios;
    }

    public Set<ContenidoCurso> getContenidoCursos() {
        return contenidoCursos;
    }

    public Habilidad contenidoCursos(Set<ContenidoCurso> contenidoCursos) {
        this.contenidoCursos = contenidoCursos;
        return this;
    }

    public Habilidad addContenidoCurso(ContenidoCurso contenidoCurso) {
        this.contenidoCursos.add(contenidoCurso);
        contenidoCurso.setHabilidad(this);
        return this;
    }

    public Habilidad removeContenidoCurso(ContenidoCurso contenidoCurso) {
        this.contenidoCursos.remove(contenidoCurso);
        contenidoCurso.setHabilidad(null);
        return this;
    }

    public void setContenidoCursos(Set<ContenidoCurso> contenidoCursos) {
        this.contenidoCursos = contenidoCursos;
    }

    public TipoHabilidad getTipoHabilidad() {
        return tipoHabilidad;
    }

    public Habilidad tipoHabilidad(TipoHabilidad tipoHabilidad) {
        this.tipoHabilidad = tipoHabilidad;
        return this;
    }

    public void setTipoHabilidad(TipoHabilidad tipoHabilidad) {
        this.tipoHabilidad = tipoHabilidad;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Habilidad)) {
            return false;
        }
        return id != null && id.equals(((Habilidad) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Habilidad{" +
            "id=" + getId() +
            ", descripcion='" + getDescripcion() + "'" +
            ", descripcionLarga='" + getDescripcionLarga() + "'" +
            "}";
    }
}
