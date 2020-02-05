package com.experis.formacion.alexa.poc.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A PerfilPlanFormativo.
 */
@Entity
@Table(name = "perfil_plan_formativo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PerfilPlanFormativo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Pattern(regexp = "^(H|I|h|i)$")
    @Column(name = "interes_habilidad", nullable = false)
    private String interesHabilidad;

    @ManyToOne
    @JsonIgnoreProperties("perfilPlanFormativos")
    private Habilidad habilidad;

    @ManyToOne
    @JsonIgnoreProperties("perfilPlanFormativos")
    private Interes interes;

    @ManyToOne
    @JsonIgnoreProperties("perfilPlanFormativos")
    private PlanFormativo planFormativo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInteresHabilidad() {
        return interesHabilidad;
    }

    public PerfilPlanFormativo interesHabilidad(String interesHabilidad) {
        this.interesHabilidad = interesHabilidad;
        return this;
    }

    public void setInteresHabilidad(String interesHabilidad) {
        this.interesHabilidad = interesHabilidad;
    }

    public Habilidad getHabilidad() {
        return habilidad;
    }

    public PerfilPlanFormativo habilidad(Habilidad habilidad) {
        this.habilidad = habilidad;
        return this;
    }

    public void setHabilidad(Habilidad habilidad) {
        this.habilidad = habilidad;
    }

    public Interes getInteres() {
        return interes;
    }

    public PerfilPlanFormativo interes(Interes interes) {
        this.interes = interes;
        return this;
    }

    public void setInteres(Interes interes) {
        this.interes = interes;
    }

    public PlanFormativo getPlanFormativo() {
        return planFormativo;
    }

    public PerfilPlanFormativo planFormativo(PlanFormativo planFormativo) {
        this.planFormativo = planFormativo;
        return this;
    }

    public void setPlanFormativo(PlanFormativo planFormativo) {
        this.planFormativo = planFormativo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PerfilPlanFormativo)) {
            return false;
        }
        return id != null && id.equals(((PerfilPlanFormativo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PerfilPlanFormativo{" +
            "id=" + getId() +
            ", interesHabilidad='" + getInteresHabilidad() + "'" +
            "}";
    }
}
