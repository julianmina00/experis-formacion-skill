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
 * A Interes.
 */
@Entity
@Table(name = "CI_CatalogInteres")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Interes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CI_IdInteres")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "CI_DescInteres", nullable = false)
    private String descripcion;

    @Column(name = "CI_DescLargInteres")
    private String descripcionLarga;

    @OneToMany(mappedBy = "interes")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PerfilPlanFormativo> perfilPlanFormativos = new HashSet<>();

    @OneToMany(mappedBy = "interes")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<InteresUsuario> interesUsuarios = new HashSet<>();

    @OneToMany(mappedBy = "interes")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ContenidoCurso> contenidoCursos = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "CI_IdTipInteres")
    @JsonIgnoreProperties("interes")
    private TipoInteres tipoInteres;

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

    public Interes descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcionLarga() {
        return descripcionLarga;
    }

    public Interes descripcionLarga(String descripcionLarga) {
        this.descripcionLarga = descripcionLarga;
        return this;
    }

    public void setDescripcionLarga(String descripcionLarga) {
        this.descripcionLarga = descripcionLarga;
    }

    public Set<PerfilPlanFormativo> getPerfilPlanFormativos() {
        return perfilPlanFormativos;
    }

    public Interes perfilPlanFormativos(Set<PerfilPlanFormativo> perfilPlanFormativos) {
        this.perfilPlanFormativos = perfilPlanFormativos;
        return this;
    }

    public Interes addPerfilPlanFormativo(PerfilPlanFormativo perfilPlanFormativo) {
        this.perfilPlanFormativos.add(perfilPlanFormativo);
        perfilPlanFormativo.setInteres(this);
        return this;
    }

    public Interes removePerfilPlanFormativo(PerfilPlanFormativo perfilPlanFormativo) {
        this.perfilPlanFormativos.remove(perfilPlanFormativo);
        perfilPlanFormativo.setInteres(null);
        return this;
    }

    public void setPerfilPlanFormativos(Set<PerfilPlanFormativo> perfilPlanFormativos) {
        this.perfilPlanFormativos = perfilPlanFormativos;
    }

    public Set<InteresUsuario> getInteresUsuarios() {
        return interesUsuarios;
    }

    public Interes interesUsuarios(Set<InteresUsuario> interesUsuarios) {
        this.interesUsuarios = interesUsuarios;
        return this;
    }

    public Interes addInteresUsuario(InteresUsuario interesUsuario) {
        this.interesUsuarios.add(interesUsuario);
        interesUsuario.setInteres(this);
        return this;
    }

    public Interes removeInteresUsuario(InteresUsuario interesUsuario) {
        this.interesUsuarios.remove(interesUsuario);
        interesUsuario.setInteres(null);
        return this;
    }

    public void setInteresUsuarios(Set<InteresUsuario> interesUsuarios) {
        this.interesUsuarios = interesUsuarios;
    }

    public Set<ContenidoCurso> getContenidoCursos() {
        return contenidoCursos;
    }

    public Interes contenidoCursos(Set<ContenidoCurso> contenidoCursos) {
        this.contenidoCursos = contenidoCursos;
        return this;
    }

    public Interes addContenidoCurso(ContenidoCurso contenidoCurso) {
        this.contenidoCursos.add(contenidoCurso);
        contenidoCurso.setInteres(this);
        return this;
    }

    public Interes removeContenidoCurso(ContenidoCurso contenidoCurso) {
        this.contenidoCursos.remove(contenidoCurso);
        contenidoCurso.setInteres(null);
        return this;
    }

    public void setContenidoCursos(Set<ContenidoCurso> contenidoCursos) {
        this.contenidoCursos = contenidoCursos;
    }

    public TipoInteres getTipoInteres() {
        return tipoInteres;
    }

    public Interes tipoInteres(TipoInteres tipoInteres) {
        this.tipoInteres = tipoInteres;
        return this;
    }

    public void setTipoInteres(TipoInteres tipoInteres) {
        this.tipoInteres = tipoInteres;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Interes)) {
            return false;
        }
        return id != null && id.equals(((Interes) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Interes{" +
            "id=" + getId() +
            ", descripcion='" + getDescripcion() + "'" +
            ", descripcionLarga='" + getDescripcionLarga() + "'" +
            "}";
    }
}
