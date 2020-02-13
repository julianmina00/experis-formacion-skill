package com.experis.formacion.alexa.poc.service;

import com.experis.formacion.alexa.poc.service.dto.RelacionTipoInteresDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.experis.formacion.alexa.poc.domain.RelacionTipoInteres}.
 */
public interface RelacionTipoInteresService {

    /**
     * Save a relacionTipoInteres.
     *
     * @param relacionTipoInteresDTO the entity to save.
     * @return the persisted entity.
     */
    RelacionTipoInteresDTO save(RelacionTipoInteresDTO relacionTipoInteresDTO);

    /**
     * Get all the relacionTipoInteres.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RelacionTipoInteresDTO> findAll(Pageable pageable);


    /**
     * Get the "id" relacionTipoInteres.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RelacionTipoInteresDTO> findOne(Long id);

    /**
     * Delete the "id" relacionTipoInteres.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
