package com.experis.formacion.alexa.poc.service;

import com.experis.formacion.alexa.poc.service.dto.HabilidadDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.experis.formacion.alexa.poc.domain.Habilidad}.
 */
public interface HabilidadService {

    /**
     * Save a habilidad.
     *
     * @param habilidadDTO the entity to save.
     * @return the persisted entity.
     */
    HabilidadDTO save(HabilidadDTO habilidadDTO);

    /**
     * Get all the habilidads.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<HabilidadDTO> findAll(Pageable pageable);


    /**
     * Get the "id" habilidad.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<HabilidadDTO> findOne(Long id);

    /**
     * Delete the "id" habilidad.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
