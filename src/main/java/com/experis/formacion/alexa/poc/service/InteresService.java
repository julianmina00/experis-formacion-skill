package com.experis.formacion.alexa.poc.service;

import com.experis.formacion.alexa.poc.service.dto.InteresDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.experis.formacion.alexa.poc.domain.Interes}.
 */
public interface InteresService {

    /**
     * Save a interes.
     *
     * @param interesDTO the entity to save.
     * @return the persisted entity.
     */
    InteresDTO save(InteresDTO interesDTO);

    /**
     * Get all the interes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<InteresDTO> findAll(Pageable pageable);


    /**
     * Get the "id" interes.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InteresDTO> findOne(Long id);

    /**
     * Delete the "id" interes.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
