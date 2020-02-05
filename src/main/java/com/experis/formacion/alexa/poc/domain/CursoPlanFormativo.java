package com.experis.formacion.alexa.poc.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A CursoPlanFormativo.
 */
@Entity
@Table(name = "CP_CursoPlanFormativo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CursoPlanFormativo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CP_IdCursoPlanFormativo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CP_IdCurso")
    @JsonIgnoreProperties("cursoPlanFormativos")
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "CP_IdPlanFormativo")
    @JsonIgnoreProperties("cursoPlanFormativos")
    private PlanFormativo planFormativo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Curso getCurso() {
        return curso;
    }

    public CursoPlanFormativo curso(Curso curso) {
        this.curso = curso;
        return this;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public PlanFormativo getPlanFormativo() {
        return planFormativo;
    }

    public CursoPlanFormativo planFormativo(PlanFormativo planFormativo) {
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
        if (!(o instanceof CursoPlanFormativo)) {
            return false;
        }
        return id != null && id.equals(((CursoPlanFormativo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CursoPlanFormativo{" +
            "id=" + getId() +
            "}";
    }
}
