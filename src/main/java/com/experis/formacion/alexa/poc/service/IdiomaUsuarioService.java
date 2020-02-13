package com.experis.formacion.alexa.poc.service;

import com.experis.formacion.alexa.poc.service.dto.IdiomaUsuarioDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.experis.formacion.alexa.poc.domain.IdiomaUsuario}.
 */
public interface IdiomaUsuarioService {

    /**
     * Save a idiomaUsuario.
     *
     * @param idiomaUsuarioDTO the entity to save.
     * @return the persisted entity.
     */
    IdiomaUsuarioDTO save(IdiomaUsuarioDTO idiomaUsuarioDTO);

    /**
     * Get all the idiomaUsuarios.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<IdiomaUsuarioDTO> findAll(Pageable pageable);


    /**
     * Get the "id" idiomaUsuario.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<IdiomaUsuarioDTO> findOne(Long id);

    /**
     * Delete the "id" idiomaUsuario.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
