package com.experis.formacion.alexa.poc.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A IdiomaUsuario.
 */
@Entity
@Table(name = "iu_idiomausuario")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class IdiomaUsuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "iu_ididiomausuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "iu_idioma", nullable = false)
    private String idioma;

    @ManyToOne
    @JsonIgnoreProperties("idiomaUsuarios")
    @JoinColumn(name = "iu_usuario")
    private Usuario usuario;

    @ManyToOne
    @JsonIgnoreProperties("idiomaUsuarios")
    @JoinColumn(name = "iu_nivelidioma")
    private NivelIdioma nivelIdioma;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdioma() {
        return idioma;
    }

    public IdiomaUsuario idioma(String idioma) {
        this.idioma = idioma;
        return this;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public IdiomaUsuario usuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public NivelIdioma getNivelIdioma() {
        return nivelIdioma;
    }

    public IdiomaUsuario nivelIdioma(NivelIdioma nivelIdioma) {
        this.nivelIdioma = nivelIdioma;
        return this;
    }

    public void setNivelIdioma(NivelIdioma nivelIdioma) {
        this.nivelIdioma = nivelIdioma;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IdiomaUsuario)) {
            return false;
        }
        return id != null && id.equals(((IdiomaUsuario) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "IdiomaUsuario{" +
            "id=" + getId() +
            ", idioma='" + getIdioma() + "'" +
            "}";
    }
}
