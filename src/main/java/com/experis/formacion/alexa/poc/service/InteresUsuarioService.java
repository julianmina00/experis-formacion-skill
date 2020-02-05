package com.experis.formacion.alexa.poc.service;

import com.experis.formacion.alexa.poc.domain.InteresUsuario;
import com.experis.formacion.alexa.poc.repository.InteresUsuarioRepository;
import com.experis.formacion.alexa.poc.service.dto.InteresUsuarioDTO;
import com.experis.formacion.alexa.poc.service.mapper.InteresUsuarioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link InteresUsuario}.
 */
@Service
@Transactional
public class InteresUsuarioService {

    private final Logger log = LoggerFactory.getLogger(InteresUsuarioService.class);

    private final InteresUsuarioRepository interesUsuarioRepository;

    private final InteresUsuarioMapper interesUsuarioMapper;

    public InteresUsuarioService(InteresUsuarioRepository interesUsuarioRepository, InteresUsuarioMapper interesUsuarioMapper) {
        this.interesUsuarioRepository = interesUsuarioRepository;
        this.interesUsuarioMapper = interesUsuarioMapper;
    }

    /**
     * Save a interesUsuario.
     *
     * @param interesUsuarioDTO the entity to save.
     * @return the persisted entity.
     */
    public InteresUsuarioDTO save(InteresUsuarioDTO interesUsuarioDTO) {
        log.debug("Request to save InteresUsuario : {}", interesUsuarioDTO);
        InteresUsuario interesUsuario = interesUsuarioMapper.toEntity(interesUsuarioDTO);
        interesUsuario = interesUsuarioRepository.save(interesUsuario);
        return interesUsuarioMapper.toDto(interesUsuario);
    }

    /**
     * Get all the interesUsuarios.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<InteresUsuarioDTO> findAll() {
        log.debug("Request to get all InteresUsuarios");
        return interesUsuarioRepository.findAll().stream()
            .map(interesUsuarioMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one interesUsuario by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InteresUsuarioDTO> findOne(Long id) {
        log.debug("Request to get InteresUsuario : {}", id);
        return interesUsuarioRepository.findById(id)
            .map(interesUsuarioMapper::toDto);
    }

    /**
     * Delete the interesUsuario by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InteresUsuario : {}", id);
        interesUsuarioRepository.deleteById(id);
    }
}
