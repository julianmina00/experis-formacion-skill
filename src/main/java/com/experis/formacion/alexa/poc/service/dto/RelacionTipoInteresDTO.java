package com.experis.formacion.alexa.poc.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.experis.formacion.alexa.poc.domain.RelacionTipoInteres} entity.
 */
public class RelacionTipoInteresDTO implements Serializable {

    private Long id;

    @NotNull
    @Max(value = 10)
    private Integer profundidad;


    private Long padreId;

    private Long hijaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(Integer profundidad) {
        this.profundidad = profundidad;
    }

    public Long getPadreId() {
        return padreId;
    }

    public void setPadreId(Long tipoInteresId) {
        this.padreId = tipoInteresId;
    }

    public Long getHijaId() {
        return hijaId;
    }

    public void setHijaId(Long tipoInteresId) {
        this.hijaId = tipoInteresId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RelacionTipoInteresDTO relacionTipoInteresDTO = (RelacionTipoInteresDTO) o;
        if (relacionTipoInteresDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), relacionTipoInteresDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RelacionTipoInteresDTO{" +
            "id=" + getId() +
            ", profundidad=" + getProfundidad() +
            ", padreId=" + getPadreId() +
            ", hijaId=" + getHijaId() +
            "}";
    }
}
