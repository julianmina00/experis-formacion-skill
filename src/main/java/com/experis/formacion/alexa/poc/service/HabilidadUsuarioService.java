package com.experis.formacion.alexa.poc.service;

import com.experis.formacion.alexa.poc.domain.HabilidadUsuario;
import com.experis.formacion.alexa.poc.repository.HabilidadUsuarioRepository;
import com.experis.formacion.alexa.poc.service.dto.HabilidadUsuarioDTO;
import com.experis.formacion.alexa.poc.service.mapper.HabilidadUsuarioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link HabilidadUsuario}.
 */
@Service
@Transactional
public class HabilidadUsuarioService {

    private final Logger log = LoggerFactory.getLogger(HabilidadUsuarioService.class);

    private final HabilidadUsuarioRepository habilidadUsuarioRepository;

    private final HabilidadUsuarioMapper habilidadUsuarioMapper;

    public HabilidadUsuarioService(HabilidadUsuarioRepository habilidadUsuarioRepository, HabilidadUsuarioMapper habilidadUsuarioMapper) {
        this.habilidadUsuarioRepository = habilidadUsuarioRepository;
        this.habilidadUsuarioMapper = habilidadUsuarioMapper;
    }

    /**
     * Save a habilidadUsuario.
     *
     * @param habilidadUsuarioDTO the entity to save.
     * @return the persisted entity.
     */
    public HabilidadUsuarioDTO save(HabilidadUsuarioDTO habilidadUsuarioDTO) {
        log.debug("Request to save HabilidadUsuario : {}", habilidadUsuarioDTO);
        HabilidadUsuario habilidadUsuario = habilidadUsuarioMapper.toEntity(habilidadUsuarioDTO);
        habilidadUsuario = habilidadUsuarioRepository.save(habilidadUsuario);
        return habilidadUsuarioMapper.toDto(habilidadUsuario);
    }

    /**
     * Get all the habilidadUsuarios.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<HabilidadUsuarioDTO> findAll() {
        log.debug("Request to get all HabilidadUsuarios");
        return habilidadUsuarioRepository.findAll().stream()
            .map(habilidadUsuarioMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one habilidadUsuario by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<HabilidadUsuarioDTO> findOne(Long id) {
        log.debug("Request to get HabilidadUsuario : {}", id);
        return habilidadUsuarioRepository.findById(id)
            .map(habilidadUsuarioMapper::toDto);
    }

    /**
     * Delete the habilidadUsuario by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete HabilidadUsuario : {}", id);
        habilidadUsuarioRepository.deleteById(id);
    }
}
