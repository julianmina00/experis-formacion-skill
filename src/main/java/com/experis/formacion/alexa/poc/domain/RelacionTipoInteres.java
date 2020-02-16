package com.experis.formacion.alexa.poc.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A RelacionTipoInteres.
 */
@Entity
@Table(name = "ri_relactipinteres")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RelacionTipoInteres implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ri_unica")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Max(value = 3)
    @Column(name = "ri_profundidad", nullable = false)
    private Integer profundidad;

    @ManyToOne
    @JsonIgnoreProperties("relacionTipoInteres")
    @JoinColumn(name = "ri_idtipinteres_padre")
    private TipoInteres padre;

    @ManyToOne
    @JsonIgnoreProperties("relacionTipoInteres")
    @JoinColumn(name = "ri_idtipinteres_hijo")
    private TipoInteres hija;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProfundidad() {
        return profundidad;
    }

    public RelacionTipoInteres profundidad(Integer profundidad) {
        this.profundidad = profundidad;
        return this;
    }

    public void setProfundidad(Integer profundidad) {
        this.profundidad = profundidad;
    }

    public TipoInteres getPadre() {
        return padre;
    }

    public RelacionTipoInteres padre(TipoInteres tipoInteres) {
        this.padre = tipoInteres;
        return this;
    }

    public void setPadre(TipoInteres tipoInteres) {
        this.padre = tipoInteres;
    }

    public TipoInteres getHija() {
        return hija;
    }

    public RelacionTipoInteres hija(TipoInteres tipoInteres) {
        this.hija = tipoInteres;
        return this;
    }

    public void setHija(TipoInteres tipoInteres) {
        this.hija = tipoInteres;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RelacionTipoInteres)) {
            return false;
        }
        return id != null && id.equals(((RelacionTipoInteres) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "RelacionTipoInteres{" +
            "id=" + getId() +
            ", profundidad=" + getProfundidad() +
            "}";
    }
}
