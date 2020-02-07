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
@Table(name = "pp_perfilplanformativo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PerfilPlanFormativo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "pp_idperfilplanformativo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Pattern(regexp = "^(I|H|i|h)$")
    @Column(name = "pp_intereshabilidad", nullable = false)
    private String interesHabilidad;

    @ManyToOne
    @JoinColumn(name = "pp_idhabilidad")
    @JsonIgnoreProperties("perfilPlanFormativos")
    private Habilidad habilidad;

    @ManyToOne
    @JoinColumn(name = "pp_idinteres")
    @JsonIgnoreProperties("perfilPlanFormativos")
    private Interes interes;

    @ManyToOne
    @JoinColumn(name = "pp_idplanformativo")
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
