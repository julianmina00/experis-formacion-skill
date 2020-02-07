package com.experis.formacion.alexa.poc.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A InteresUsuario.
 */
@Entity
@Table(name = "ui_usuariointereses")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class InteresUsuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ui_idusuariointereses")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ui_idinteres")
    @JsonIgnoreProperties("interesUsuarios")
    private Interes interes;

    @ManyToOne
    @JoinColumn(name = "ui_idusuario")
    @JsonIgnoreProperties("interesUsuarios")
    private Usuario usuario;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Interes getInteres() {
        return interes;
    }

    public InteresUsuario interes(Interes interes) {
        this.interes = interes;
        return this;
    }

    public void setInteres(Interes interes) {
        this.interes = interes;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public InteresUsuario usuario(Usuario usuario) {
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
        if (!(o instanceof InteresUsuario)) {
            return false;
        }
        return id != null && id.equals(((InteresUsuario) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "InteresUsuario{" +
            "id=" + getId() +
            "}";
    }
}
