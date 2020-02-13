package com.experis.formacion.alexa.poc.service;

import com.experis.formacion.alexa.poc.service.dto.RelacionTipoHabilidadDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.experis.formacion.alexa.poc.domain.RelacionTipoHabilidad}.
 */
public interface RelacionTipoHabilidadService {

    /**
     * Save a relacionTipoHabilidad.
     *
     * @param relacionTipoHabilidadDTO the entity to save.
     * @return the persisted entity.
     */
    RelacionTipoHabilidadDTO save(RelacionTipoHabilidadDTO relacionTipoHabilidadDTO);

    /**
     * Get all the relacionTipoHabilidads.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RelacionTipoHabilidadDTO> findAll(Pageable pageable);


    /**
     * Get the "id" relacionTipoHabilidad.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RelacionTipoHabilidadDTO> findOne(Long id);

    /**
     * Delete the "id" relacionTipoHabilidad.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
