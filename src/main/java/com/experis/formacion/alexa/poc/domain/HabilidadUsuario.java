package com.experis.formacion.alexa.poc.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A HabilidadUsuario.
 */
@Entity
@Table(name = "UH_UsuarioHabilidades")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class HabilidadUsuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "UH_idUsuarioHabilidades")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne
    @Column(name = "UH_IdHabilidad")
    @JsonIgnoreProperties("habilidadUsuarios")
    private Habilidad habilidad;

    @ManyToOne
    @Column(name = "UH_IdUsuario")
    @JsonIgnoreProperties("habilidadUsuarios")
    private Usuario usuario;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Habilidad getHabilidad() {
        return habilidad;
    }

    public HabilidadUsuario habilidad(Habilidad habilidad) {
        this.habilidad = habilidad;
        return this;
    }

    public void setHabilidad(Habilidad habilidad) {
        this.habilidad = habilidad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public HabilidadUsuario usuario(Usuario usuario) {
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
        if (!(o instanceof HabilidadUsuario)) {
            return false;
        }
        return id != null && id.equals(((HabilidadUsuario) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "HabilidadUsuario{" +
            "id=" + getId() +
            "}";
    }
}
