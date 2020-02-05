package com.experis.formacion.alexa.poc.service;

import com.experis.formacion.alexa.poc.service.dto.PlanFormativoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.experis.formacion.alexa.poc.domain.PlanFormativo}.
 */
public interface PlanFormativoService {

    /**
     * Save a planFormativo.
     *
     * @param planFormativoDTO the entity to save.
     * @return the persisted entity.
     */
    PlanFormativoDTO save(PlanFormativoDTO planFormativoDTO);

    /**
     * Get all the planFormativos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PlanFormativoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" planFormativo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PlanFormativoDTO> findOne(Long id);

    /**
     * Delete the "id" planFormativo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
