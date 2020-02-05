package com.experis.formacion.alexa.poc.service;

import com.experis.formacion.alexa.poc.service.dto.TipoHabilidadDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.experis.formacion.alexa.poc.domain.TipoHabilidad}.
 */
public interface TipoHabilidadService {

    /**
     * Save a tipoHabilidad.
     *
     * @param tipoHabilidadDTO the entity to save.
     * @return the persisted entity.
     */
    TipoHabilidadDTO save(TipoHabilidadDTO tipoHabilidadDTO);

    /**
     * Get all the tipoHabilidads.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TipoHabilidadDTO> findAll(Pageable pageable);


    /**
     * Get the "id" tipoHabilidad.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TipoHabilidadDTO> findOne(Long id);

    /**
     * Delete the "id" tipoHabilidad.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
