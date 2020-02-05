package com.experis.formacion.alexa.poc.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A PlanFormativo.
 */
@Entity
@Table(name = "plan_formativo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PlanFormativo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "descripcion_larga")
    private String descripcionLarga;

    @NotNull
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @NotNull
    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @OneToMany(mappedBy = "planFormativo")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CursoPlanFormativo> cursoPlanFormativos = new HashSet<>();

    @OneToMany(mappedBy = "planFormativo")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PerfilPlanFormativo> perfilPlanFormativos = new HashSet<>();

    @OneToMany(mappedBy = "planFormativo")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PlanFormativoUsuario> planFormativoUsuarios = new HashSet<>();

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

    public PlanFormativo descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcionLarga() {
        return descripcionLarga;
    }

    public PlanFormativo descripcionLarga(String descripcionLarga) {
        this.descripcionLarga = descripcionLarga;
        return this;
    }

    public void setDescripcionLarga(String descripcionLarga) {
        this.descripcionLarga = descripcionLarga;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public PlanFormativo fechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
        return this;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public PlanFormativo fechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
        return this;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Set<CursoPlanFormativo> getCursoPlanFormativos() {
        return cursoPlanFormativos;
    }

    public PlanFormativo cursoPlanFormativos(Set<CursoPlanFormativo> cursoPlanFormativos) {
        this.cursoPlanFormativos = cursoPlanFormativos;
        return this;
    }

    public PlanFormativo addCursoPlanFormativo(CursoPlanFormativo cursoPlanFormativo) {
        this.cursoPlanFormativos.add(cursoPlanFormativo);
        cursoPlanFormativo.setPlanFormativo(this);
        return this;
    }

    public PlanFormativo removeCursoPlanFormativo(CursoPlanFormativo cursoPlanFormativo) {
        this.cursoPlanFormativos.remove(cursoPlanFormativo);
        cursoPlanFormativo.setPlanFormativo(null);
        return this;
    }

    public void setCursoPlanFormativos(Set<CursoPlanFormativo> cursoPlanFormativos) {
        this.cursoPlanFormativos = cursoPlanFormativos;
    }

    public Set<PerfilPlanFormativo> getPerfilPlanFormativos() {
        return perfilPlanFormativos;
    }

    public PlanFormativo perfilPlanFormativos(Set<PerfilPlanFormativo> perfilPlanFormativos) {
        this.perfilPlanFormativos = perfilPlanFormativos;
        return this;
    }

    public PlanFormativo addPerfilPlanFormativo(PerfilPlanFormativo perfilPlanFormativo) {
        this.perfilPlanFormativos.add(perfilPlanFormativo);
        perfilPlanFormativo.setPlanFormativo(this);
        return this;
    }

    public PlanFormativo removePerfilPlanFormativo(PerfilPlanFormativo perfilPlanFormativo) {
        this.perfilPlanFormativos.remove(perfilPlanFormativo);
        perfilPlanFormativo.setPlanFormativo(null);
        return this;
    }

    public void setPerfilPlanFormativos(Set<PerfilPlanFormativo> perfilPlanFormativos) {
        this.perfilPlanFormativos = perfilPlanFormativos;
    }

    public Set<PlanFormativoUsuario> getPlanFormativoUsuarios() {
        return planFormativoUsuarios;
    }

    public PlanFormativo planFormativoUsuarios(Set<PlanFormativoUsuario> planFormativoUsuarios) {
        this.planFormativoUsuarios = planFormativoUsuarios;
        return this;
    }

    public PlanFormativo addPlanFormativoUsuario(PlanFormativoUsuario planFormativoUsuario) {
        this.planFormativoUsuarios.add(planFormativoUsuario);
        planFormativoUsuario.setPlanFormativo(this);
        return this;
    }

    public PlanFormativo removePlanFormativoUsuario(PlanFormativoUsuario planFormativoUsuario) {
        this.planFormativoUsuarios.remove(planFormativoUsuario);
        planFormativoUsuario.setPlanFormativo(null);
        return this;
    }

    public void setPlanFormativoUsuarios(Set<PlanFormativoUsuario> planFormativoUsuarios) {
        this.planFormativoUsuarios = planFormativoUsuarios;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlanFormativo)) {
            return false;
        }
        return id != null && id.equals(((PlanFormativo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PlanFormativo{" +
            "id=" + getId() +
            ", descripcion='" + getDescripcion() + "'" +
            ", descripcionLarga='" + getDescripcionLarga() + "'" +
            ", fechaInicio='" + getFechaInicio() + "'" +
            ", fechaFin='" + getFechaFin() + "'" +
            "}";
    }
}
