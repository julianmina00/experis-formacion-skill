package com.experis.formacion.alexa.poc.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.experis.formacion.alexa.poc.domain.Usuario} entity.
 */
public class UsuarioDTO implements Serializable {

    private Long id;

    @NotNull
    private String documento;

    @NotNull
    private String tipoDocumento;

    @NotNull
    private String nombre;

    @NotNull
    private String apellidos;

    @NotNull
    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")
    private String email;

    private String telefono;

    @NotNull
    private String rol;

    private String proyecto;

    private String ubicacion;

    @NotNull
    private String managerNombre;

    private String managerEmail;

    private String talentMentorNombre;

    private String talentMentorEmail;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getManagerNombre() {
        return managerNombre;
    }

    public void setManagerNombre(String managerNombre) {
        this.managerNombre = managerNombre;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    public String getTalentMentorNombre() {
        return talentMentorNombre;
    }

    public void setTalentMentorNombre(String talentMentorNombre) {
        this.talentMentorNombre = talentMentorNombre;
    }

    public String getTalentMentorEmail() {
        return talentMentorEmail;
    }

    public void setTalentMentorEmail(String talentMentorEmail) {
        this.talentMentorEmail = talentMentorEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UsuarioDTO usuarioDTO = (UsuarioDTO) o;
        if (usuarioDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), usuarioDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
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
