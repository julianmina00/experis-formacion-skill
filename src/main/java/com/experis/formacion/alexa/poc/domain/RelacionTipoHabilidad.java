package com.experis.formacion.alexa.poc.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A RelacionTipoHabilidad.
 */
@Entity
@Table(name = "rh_relactiphabilidad")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RelacionTipoHabilidad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "rh_unica")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Max(value = 3)
    @Column(name = "rh_profundidad", nullable = false)
    private Integer profundidad;

    @ManyToOne
    @JsonIgnoreProperties("relacionTipoHabilidads")
    @JoinColumn(name = "rh_idtiphabilidad_padre")
    private TipoHabilidad padre;

    @ManyToOne
    @JsonIgnoreProperties("relacionTipoHabilidads")
    @JoinColumn(name = "rh_idtiphabilidad_hija")
    private TipoHabilidad hija;

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

    public RelacionTipoHabilidad profundidad(Integer profundidad) {
        this.profundidad = profundidad;
        return this;
    }

    public void setProfundidad(Integer profundidad) {
        this.profundidad = profundidad;
    }

    public TipoHabilidad getPadre() {
        return padre;
    }

    public RelacionTipoHabilidad padre(TipoHabilidad tipoHabilidad) {
        this.padre = tipoHabilidad;
        return this;
    }

    public void setPadre(TipoHabilidad tipoHabilidad) {
        this.padre = tipoHabilidad;
    }

    public TipoHabilidad getHija() {
        return hija;
    }

    public RelacionTipoHabilidad hija(TipoHabilidad tipoHabilidad) {
        this.hija = tipoHabilidad;
        return this;
    }

    public void setHija(TipoHabilidad tipoHabilidad) {
        this.hija = tipoHabilidad;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RelacionTipoHabilidad)) {
            return false;
        }
        return id != null && id.equals(((RelacionTipoHabilidad) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "RelacionTipoHabilidad{" +
            "id=" + getId() +
            ", profundidad=" + getProfundidad() +
            "}";
    }
}
