package com.experis.formacion.alexa.poc.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A PlanFormativoUsuario.
 */
@Entity
@Table(name = "UP_UsuarioPlanFormativo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PlanFormativoUsuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "UP_idUsuarioPlanFormativo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties("planFormativoUsuarios")
    @JoinColumn(name = "UP_IdPlanFormativo")
    private PlanFormativo planFormativo;

    @ManyToOne
    @JsonIgnoreProperties("planFormativoUsuarios")
    @JoinColumn(name = "UP_IdUsuario")
    private Usuario usuario;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlanFormativo getPlanFormativo() {
        return planFormativo;
    }

    public PlanFormativoUsuario planFormativo(PlanFormativo planFormativo) {
        this.planFormativo = planFormativo;
        return this;
    }

    public void setPlanFormativo(PlanFormativo planFormativo) {
        this.planFormativo = planFormativo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public PlanFormativoUsuario usuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlanFormativoUsuario)) {
            return false;
        }
        return id != null && id.equals(((PlanFormativoUsuario) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PlanFormativoUsuario{" +
            "id=" + getId() +
            "}";
    }
}
