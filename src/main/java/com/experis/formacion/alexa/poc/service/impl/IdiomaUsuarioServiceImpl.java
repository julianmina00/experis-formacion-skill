package com.experis.formacion.alexa.poc.service.impl;

import com.experis.formacion.alexa.poc.service.IdiomaUsuarioService;
import com.experis.formacion.alexa.poc.domain.IdiomaUsuario;
import com.experis.formacion.alexa.poc.repository.IdiomaUsuarioRepository;
import com.experis.formacion.alexa.poc.service.dto.IdiomaUsuarioDTO;
import com.experis.formacion.alexa.poc.service.mapper.IdiomaUsuarioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link IdiomaUsuario}.
 */
@Service
@Transactional
public class IdiomaUsuarioServiceImpl implements IdiomaUsuarioService {

    private final Logger log = LoggerFactory.getLogger(IdiomaUsuarioServiceImpl.class);

    private final IdiomaUsuarioRepository idiomaUsuarioRepository;

    private final IdiomaUsuarioMapper idiomaUsuarioMapper;

    public IdiomaUsuarioServiceImpl(IdiomaUsuarioRepository idiomaUsuarioRepository, IdiomaUsuarioMapper idiomaUsuarioMapper) {
        this.idiomaUsuarioRepository = idiomaUsuarioRepository;
        this.idiomaUsuarioMapper = idiomaUsuarioMapper;
    }

    /**
     * Save a idiomaUsuario.
     *
     * @param idiomaUsuarioDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public IdiomaUsuarioDTO save(IdiomaUsuarioDTO idiomaUsuarioDTO) {
        log.debug("Request to save IdiomaUsuario : {}", idiomaUsuarioDTO);
        IdiomaUsuario idiomaUsuario = idiomaUsuarioMapper.toEntity(idiomaUsuarioDTO);
        idiomaUsuario = idiomaUsuarioRepository.save(idiomaUsuario);
        return idiomaUsuarioMapper.toDto(idiomaUsuario);
    }

    /**
     * Get all the idiomaUsuarios.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<IdiomaUsuarioDTO> findAll(Pageable pageable) {
        log.debug("Request to get all IdiomaUsuarios");
        return idiomaUsuarioRepository.findAll(pageable)
            .map(idiomaUsuarioMapper::toDto);
    }


    /**
     * Get one idiomaUsuario by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<IdiomaUsuarioDTO> findOne(Long id) {
        log.debug("Request to get IdiomaUsuario : {}", id);
        return idiomaUsuarioRepository.findById(id)
            .map(idiomaUsuarioMapper::toDto);
    }

    /**
     * Delete the idiomaUsuario by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete IdiomaUsuario : {}", id);
        idiomaUsuarioRepository.deleteById(id);
    }
}
