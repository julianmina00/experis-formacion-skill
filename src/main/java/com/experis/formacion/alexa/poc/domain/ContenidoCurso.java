package com.experis.formacion.alexa.poc.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A ContenidoCurso.
 */
@Entity
@Table(name = "CC_ContenidoCurso")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ContenidoCurso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CC_idContenidoCurso")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Pattern(regexp = "^(H|I|h|i)$")
    @Column(name = "CC_Habilidad_Interes", nullable = false)
    private String habilidadInteres;

    @ManyToOne
    @JsonIgnoreProperties("contenidoCursos")
    @JoinColumn(name = "CC_IdCurso", updatable = false, insertable = false)
    private Curso curso;

    @ManyToOne
    @JsonIgnoreProperties("contenidoCursos")
    @JoinColumn(name = "CC_IdInteres", updatable = false, insertable = false)
    private Interes interes;

    @ManyToOne
    @JsonIgnoreProperties("contenidoCursos")
    @JoinColumn(name = "CC_IdHabilidad", updatable = false, insertable = false)
    private Habilidad habilidad;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHabilidadInteres() {
        return habilidadInteres;
    }

    public ContenidoCurso habilidadInteres(String habilidadInteres) {
        this.habilidadInteres = habilidadInteres;
        return this;
    }

    public void setHabilidadInteres(String habilidadInteres) {
        this.habilidadInteres = habilidadInteres;
    }

    public Curso getCurso() {
        return curso;
    }

    public ContenidoCurso curso(Curso curso) {
        this.curso = curso;
        return this;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Interes getInteres() {
        return interes;
    }

    public ContenidoCurso interes(Interes interes) {
        this.interes = interes;
        return this;
    }

    public void setInteres(Interes interes) {
        this.interes = interes;
    }

    public Habilidad getHabilidad() {
        return habilidad;
    }

    public ContenidoCurso habilidad(Habilidad habilidad) {
        this.habilidad = habilidad;
        return this;
    }

    public void setHabilidad(Habilidad habilidad) {
        this.habilidad = habilidad;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContenidoCurso)) {
            return false;
        }
        return id != null && id.equals(((ContenidoCurso) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ContenidoCurso{" +
            "id=" + getId() +
            ", habilidadInteres='" + getHabilidadInteres() + "'" +
            "}";
    }
}
