package com.experis.formacion.alexa.poc.service;

import com.experis.formacion.alexa.poc.service.dto.CursoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.experis.formacion.alexa.poc.domain.Curso}.
 */
public interface CursoService {

    /**
     * Save a curso.
     *
     * @param cursoDTO the entity to save.
     * @return the persisted entity.
     */
    CursoDTO save(CursoDTO cursoDTO);

    /**
     * Get all the cursos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CursoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" curso.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CursoDTO> findOne(Long id);

    /**
     * Delete the "id" curso.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
