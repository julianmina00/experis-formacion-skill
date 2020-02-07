package com.experis.formacion.alexa.poc.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A TipoHabilidad.
 */
@Entity
@Table(name = "th_tiphabilidad")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TipoHabilidad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "th_idtiphabilidad")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "th_desctiphabilidad", nullable = false)
    private String descripcion;

    @Column(name = "th_desclargtiphabilidad")
    private String descripcionLarga;

    @OneToMany(mappedBy = "tipoHabilidad")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Habilidad> habilidads = new HashSet<>();

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

    public TipoHabilidad descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcionLarga() {
        return descripcionLarga;
    }

    public TipoHabilidad descripcionLarga(String descripcionLarga) {
        this.descripcionLarga = descripcionLarga;
        return this;
    }

    public void setDescripcionLarga(String descripcionLarga) {
        this.descripcionLarga = descripcionLarga;
    }

    public Set<Habilidad> getHabilidads() {
        return habilidads;
    }

    public TipoHabilidad habilidads(Set<Habilidad> habilidads) {
        this.habilidads = habilidads;
        return this;
    }

    public TipoHabilidad addHabilidad(Habilidad habilidad) {
        this.habilidads.add(habilidad);
        habilidad.setTipoHabilidad(this);
        return this;
    }

    public TipoHabilidad removeHabilidad(Habilidad habilidad) {
        this.habilidads.remove(habilidad);
        habilidad.setTipoHabilidad(null);
        return this;
    }

    public void setHabilidads(Set<Habilidad> habilidads) {
        this.habilidads = habilidads;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TipoHabilidad)) {
            return false;
        }
        return id != null && id.equals(((TipoHabilidad) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TipoHabilidad{" +
            "id=" + getId() +
            ", descripcion='" + getDescripcion() + "'" +
            ", descripcionLarga='" + getDescripcionLarga() + "'" +
            "}";
    }
}
