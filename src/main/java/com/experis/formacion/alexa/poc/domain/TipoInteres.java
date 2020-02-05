package com.experis.formacion.alexa.poc.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A TipoInteres.
 */
@Entity
@Table(name = "TI_TipInteres")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TipoInteres implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "TI_IdTipInteres")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "TI_DescTipInteres", nullable = false)
    private String descripcion;

    @Column(name = "TI_DescLargTipInteres")
    private String descripcionLarga;

    @OneToMany(mappedBy = "tipoInteres")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Interes> interes = new HashSet<>();

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

    public TipoInteres descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcionLarga() {
        return descripcionLarga;
    }

    public TipoInteres descripcionLarga(String descripcionLarga) {
        this.descripcionLarga = descripcionLarga;
        return this;
    }

    public void setDescripcionLarga(String descripcionLarga) {
        this.descripcionLarga = descripcionLarga;
    }

    public Set<Interes> getInteres() {
        return interes;
    }

    public TipoInteres interes(Set<Interes> interes) {
        this.interes = interes;
        return this;
    }

    public TipoInteres addInteres(Interes interes) {
        this.interes.add(interes);
        interes.setTipoInteres(this);
        return this;
    }

    public TipoInteres removeInteres(Interes interes) {
        this.interes.remove(interes);
        interes.setTipoInteres(null);
        return this;
    }

    public void setInteres(Set<Interes> interes) {
        this.interes = interes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TipoInteres)) {
            return false;
        }
        return id != null && id.equals(((TipoInteres) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TipoInteres{" +
            "id=" + getId() +
            ", descripcion='" + getDescripcion() + "'" +
            ", descripcionLarga='" + getDescripcionLarga() + "'" +
            "}";
    }
}
