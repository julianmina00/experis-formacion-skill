package com.experis.formacion.alexa.poc.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Usuario.
 */
@Entity
@Table(name = "us_usuario")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "us_idusuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "us_documento", nullable = false)
    private String documento;

    @NotNull
    @Column(name = "us_tipdocumento", nullable = false)
    private String tipoDocumento;

    @NotNull
    @Column(name = "us_nombre", nullable = false)
    private String nombre;

    @NotNull
    @Column(name = "us_apellidos", nullable = false)
    private String apellidos;

    @NotNull
    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")
    @Column(name = "us_email", nullable = false)
    private String email;

    @Column(name = "us_tlf")
    private String telefono;

    @NotNull
    @Column(name = "us_rol", nullable = false)
    private String rol;

    @Column(name = "us_proyecto")
    private String proyecto;

    @Column(name = "us_ubicacion")
    private String ubicacion;

    @NotNull
    @Column(name = "us_managernombre", nullable = false)
    private String managerNombre;

    @Column(name = "us_manageremail")
    private String managerEmail;

    @Column(name = "us_talentmentornombre")
    private String talentMentorNombre;

    @Column(name = "us_talentmentoremail")
    private String talentMentorEmail;

    @OneToMany(mappedBy = "usuario")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CursoUsuario> cursoUsuarios = new HashSet<>();

    @OneToMany(mappedBy = "usuario")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<HabilidadUsuario> habilidadUsuarios = new HashSet<>();

    @OneToMany(mappedBy = "usuario")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<InteresUsuario> interesUsuarios = new HashSet<>();

    @OneToMany(mappedBy = "usuario")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PlanFormativoUsuario> planFormativoUsuarios = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public Usuario documento(String documento) {
        this.documento = documento;
        return this;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public Usuario tipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
        return this;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public Usuario nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public Usuario apellidos(String apellidos) {
        this.apellidos = apellidos;
        return this;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public Usuario email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public Usuario telefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRol() {
        return rol;
    }

    public Usuario rol(String rol) {
        this.rol = rol;
        return this;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getProyecto() {
        return proyecto;
    }

    public Usuario proyecto(String proyecto) {
        this.proyecto = proyecto;
        return this;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public Usuario ubicacion(String ubicacion) {
        this.ubicacion = ubicacion;
        return this;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getManagerNombre() {
        return managerNombre;
    }

    public Usuario managerNombre(String managerNombre) {
        this.managerNombre = managerNombre;
        return this;
    }

    public void setManagerNombre(String managerNombre) {
        this.managerNombre = managerNombre;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public Usuario managerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
        return this;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    public String getTalentMentorNombre() {
        return talentMentorNombre;
    }

    public Usuario talentMentorNombre(String talentMentorNombre) {
        this.talentMentorNombre = talentMentorNombre;
        return this;
    }

    public void setTalentMentorNombre(String talentMentorNombre) {
        this.talentMentorNombre = talentMentorNombre;
    }

    public String getTalentMentorEmail() {
        return talentMentorEmail;
    }

    public Usuario talentMentorEmail(String talentMentorEmail) {
        this.talentMentorEmail = talentMentorEmail;
        return this;
    }

    public void setTalentMentorEmail(String talentMentorEmail) {
        this.talentMentorEmail = talentMentorEmail;
    }

    public Set<CursoUsuario> getCursoUsuarios() {
        return cursoUsuarios;
    }

    public Usuario cursoUsuarios(Set<CursoUsuario> cursoUsuarios) {
        this.cursoUsuarios = cursoUsuarios;
        return this;
    }

    public Usuario addCursoUsuario(CursoUsuario cursoUsuario) {
        this.cursoUsuarios.add(cursoUsuario);
        cursoUsuario.setUsuario(this);
        return this;
    }

    public Usuario removeCursoUsuario(CursoUsuario cursoUsuario) {
        this.cursoUsuarios.remove(cursoUsuario);
        cursoUsuario.setUsuario(null);
        return this;
    }

    public void setCursoUsuarios(Set<CursoUsuario> cursoUsuarios) {
        this.cursoUsuarios = cursoUsuarios;
    }

    public Set<HabilidadUsuario> getHabilidadUsuarios() {
        return habilidadUsuarios;
    }

    public Usuario habilidadUsuarios(Set<HabilidadUsuario> habilidadUsuarios) {
        this.habilidadUsuarios = habilidadUsuarios;
        return this;
    }

    public Usuario addHabilidadUsuario(HabilidadUsuario habilidadUsuario) {
        this.habilidadUsuarios.add(habilidadUsuario);
        habilidadUsuario.setUsuario(this);
        return this;
    }

    public Usuario removeHabilidadUsuario(HabilidadUsuario habilidadUsuario) {
        this.habilidadUsuarios.remove(habilidadUsuario);
        habilidadUsuario.setUsuario(null);
        return this;
    }

    public void setHabilidadUsuarios(Set<HabilidadUsuario> habilidadUsuarios) {
        this.habilidadUsuarios = habilidadUsuarios;
    }

    public Set<InteresUsuario> getInteresUsuarios() {
        return interesUsuarios;
    }

    public Usuario interesUsuarios(Set<InteresUsuario> interesUsuarios) {
        this.interesUsuarios = interesUsuarios;
        return this;
    }

    public Usuario addInteresUsuario(InteresUsuario interesUsuario) {
        this.interesUsuarios.add(interesUsuario);
        interesUsuario.setUsuario(this);
        return this;
    }

    public Usuario removeInteresUsuario(InteresUsuario interesUsuario) {
        this.interesUsuarios.remove(interesUsuario);
        interesUsuario.setUsuario(null);
        return this;
    }

    public void setInteresUsuarios(Set<InteresUsuario> interesUsuarios) {
        this.interesUsuarios = interesUsuarios;
    }

    public Set<PlanFormativoUsuario> getPlanFormativoUsuarios() {
        return planFormativoUsuarios;
    }

    public Usuario planFormativoUsuarios(Set<PlanFormativoUsuario> planFormativoUsuarios) {
        this.planFormativoUsuarios = planFormativoUsuarios;
        return this;
    }

    public Usuario addPlanFormativoUsuario(PlanFormativoUsuario planFormativoUsuario) {
        this.planFormativoUsuarios.add(planFormativoUsuario);
        planFormativoUsuario.setUsuario(this);
        return this;
    }

    public Usuario removePlanFormativoUsuario(PlanFormativoUsuario planFormativoUsuario) {
        this.planFormativoUsuarios.remove(planFormativoUsuario);
        planFormativoUsuario.setUsuario(null);
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
        if (!(o instanceof Usuario)) {
            return false;
        }
        return id != null && id.equals(((Usuario) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Usuario{" +
            "id=" + getId() +
            ", documento='" + getDocumento() + "'" +
            ", tipoDocumento='" + getTipoDocumento() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", apellidos='" + getApellidos() + "'" +
            ", email='" + getEmail() + "'" +
            ", telefono='" + getTelefono() + "'" +
            ", rol='" + getRol() + "'" +
            ", proyecto='" + getProyecto() + "'" +
            ", ubicacion='" + getUbicacion() + "'" +
            ", managerNombre='" + getManagerNombre() + "'" +
            ", managerEmail='" + getManagerEmail() + "'" +
            ", talentMentorNombre='" + getTalentMentorNombre() + "'" +
            ", talentMentorEmail='" + getTalentMentorEmail() + "'" +
            "}";
    }
}
