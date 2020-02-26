package com.experis.formacion.alexa.poc.service.dto;

import java.util.Objects;

public class SessionUsuarioDTO {

    private Long userId;
    private String userName;
    private boolean habilidadesRegistradas;
    private boolean interesesRegistrados;
    private boolean idiomasRegistrados;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isHabilidadesRegistradas() {
        return habilidadesRegistradas;
    }

    public void setHabilidadesRegistradas(boolean habilidadesRegistradas) {
        this.habilidadesRegistradas = habilidadesRegistradas;
    }

    public boolean isInteresesRegistrados() {
        return interesesRegistrados;
    }

    public void setInteresesRegistrados(boolean interesesRegistrados) {
        this.interesesRegistrados = interesesRegistrados;
    }

    public boolean isIdiomasRegistrados() {
        return idiomasRegistrados;
    }

    public void setIdiomasRegistrados(boolean idiomasRegistrados) {
        this.idiomasRegistrados = idiomasRegistrados;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SessionUsuarioDTO that = (SessionUsuarioDTO) o;
        return habilidadesRegistradas == that.habilidadesRegistradas &&
            interesesRegistrados == that.interesesRegistrados &&
            idiomasRegistrados == that.idiomasRegistrados &&
            Objects.equals(userId, that.userId) &&
            Objects.equals(userName, that.userName);
    }

    @Override
    public int hashCode() {
        return Objects
            .hash(userId, userName, habilidadesRegistradas, interesesRegistrados,
                idiomasRegistrados);
    }

    @Override
    public String toString() {
        return "SessionUsuarioDTO{" +
            "userId='" + userId + '\'' +
            ", userName='" + userName + '\'' +
            ", habilidadesRegistradas=" + habilidadesRegistradas +
            ", interesesRegistrados=" + interesesRegistrados +
            ", idiomasRegistrados=" + idiomasRegistrados +
            '}';
    }
}
