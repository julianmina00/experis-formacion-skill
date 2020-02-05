package com.experis.formacion.alexa.poc.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A PerfilPlanFormativo.
 */
@Entity
@Table(name = "PP_PerfilPlanFormativo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PerfilPlanFormativo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PP_IdPlanFormativo")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Pattern(regexp = "^(I|H|i|h)$")
    @Column(name = "PP_InteresHabilidad", nullable = false)
    private String interesHabilidad;

    @ManyToOne
    @Column(name = "PP_IdHabilidad")
    @JsonIgnoreProperties("perfilPlanFormativos")
    private Habilidad habilidad;

    @ManyToOne
    @Column(name = "PP_IdInteres")
    @JsonIgnoreProperties("perfilPlanFormativos")
    private Interes interes;

    @ManyToOne
    @Column(name = "PP_IdPlanFormativo")
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
