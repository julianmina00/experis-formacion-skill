package com.experis.formacion.alexa.poc.service;

import com.experis.formacion.alexa.poc.service.dto.TipoInteresDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.experis.formacion.alexa.poc.domain.TipoInteres}.
 */
public interface TipoInteresService {

    /**
     * Save a tipoInteres.
     *
     * @param tipoInteresDTO the entity to save.
     * @return the persisted entity.
     */
    TipoInteresDTO save(TipoInteresDTO tipoInteresDTO);

    /**
     * Get all the tipoInteres.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TipoInteresDTO> findAll(Pageable pageable);


    /**
     * Get the "id" tipoInteres.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TipoInteresDTO> findOne(Long id);

    /**
     * Delete the "id" tipoInteres.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
