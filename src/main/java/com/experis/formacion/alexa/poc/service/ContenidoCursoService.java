package com.experis.formacion.alexa.poc.service;

import com.experis.formacion.alexa.poc.service.dto.ContenidoCursoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.experis.formacion.alexa.poc.domain.ContenidoCurso}.
 */
public interface ContenidoCursoService {

    /**
     * Save a contenidoCurso.
     *
     * @param contenidoCursoDTO the entity to save.
     * @return the persisted entity.
     */
    ContenidoCursoDTO save(ContenidoCursoDTO contenidoCursoDTO);

    /**
     * Get all the contenidoCursos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ContenidoCursoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" contenidoCurso.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ContenidoCursoDTO> findOne(Long id);

    /**
     * Delete the "id" contenidoCurso.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
