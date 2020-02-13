package com.experis.formacion.alexa.poc.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A NivelIdioma.
 */
@Entity
@Table(name = "ni_nivelidioma")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NivelIdioma implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ni_idnivelidioma")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "ni_nivel", nullable = false)
    private String nivel;

    @Column(name = "ni_descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "nivelIdioma")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<IdiomaUsuario> idiomaUsuarios = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNivel() {
        return nivel;
    }

    public NivelIdioma nivel(String nivel) {
        this.nivel = nivel;
        return this;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public NivelIdioma descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<IdiomaUsuario> getIdiomaUsuarios() {
        return idiomaUsuarios;
    }

    public NivelIdioma idiomaUsuarios(Set<IdiomaUsuario> idiomaUsuarios) {
        this.idiomaUsuarios = idiomaUsuarios;
        return this;
    }

    public NivelIdioma addIdiomaUsuario(IdiomaUsuario idiomaUsuario) {
        this.idiomaUsuarios.add(idiomaUsuario);
        idiomaUsuario.setNivelIdioma(this);
        return this;
    }

    public NivelIdioma removeIdiomaUsuario(IdiomaUsuario idiomaUsuario) {
        this.idiomaUsuarios.remove(idiomaUsuario);
        idiomaUsuario.setNivelIdioma(null);
        return this;
    }

    public void setIdiomaUsuarios(Set<IdiomaUsuario> idiomaUsuarios) {
        this.idiomaUsuarios = idiomaUsuarios;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NivelIdioma)) {
            return false;
        }
        return id != null && id.equals(((NivelIdioma) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "NivelIdioma{" +
            "id=" + getId() +
            ", nivel='" + getNivel() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            "}";
    }
}
