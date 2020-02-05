package com.experis.formacion.alexa.poc.service.impl;

import com.experis.formacion.alexa.poc.service.ContenidoCursoService;
import com.experis.formacion.alexa.poc.domain.ContenidoCurso;
import com.experis.formacion.alexa.poc.repository.ContenidoCursoRepository;
import com.experis.formacion.alexa.poc.service.dto.ContenidoCursoDTO;
import com.experis.formacion.alexa.poc.service.mapper.ContenidoCursoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ContenidoCurso}.
 */
@Service
@Transactional
public class ContenidoCursoServiceImpl implements ContenidoCursoService {

    private final Logger log = LoggerFactory.getLogger(ContenidoCursoServiceImpl.class);

    private final ContenidoCursoRepository contenidoCursoRepository;

    private final ContenidoCursoMapper contenidoCursoMapper;

    public ContenidoCursoServiceImpl(ContenidoCursoRepository contenidoCursoRepository, ContenidoCursoMapper contenidoCursoMapper) {
        this.contenidoCursoRepository = contenidoCursoRepository;
        this.contenidoCursoMapper = contenidoCursoMapper;
    }

    /**
     * Save a contenidoCurso.
     *
     * @param contenidoCursoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ContenidoCursoDTO save(ContenidoCursoDTO contenidoCursoDTO) {
        log.debug("Request to save ContenidoCurso : {}", contenidoCursoDTO);
        ContenidoCurso contenidoCurso = contenidoCursoMapper.toEntity(contenidoCursoDTO);
        contenidoCurso = contenidoCursoRepository.save(contenidoCurso);
        return contenidoCursoMapper.toDto(contenidoCurso);
    }

    /**
     * Get all the contenidoCursos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ContenidoCursoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ContenidoCursos");
        return contenidoCursoRepository.findAll(pageable)
            .map(contenidoCursoMapper::toDto);
    }


    /**
     * Get one contenidoCurso by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ContenidoCursoDTO> findOne(Long id) {
        log.debug("Request to get ContenidoCurso : {}", id);
        return contenidoCursoRepository.findById(id)
            .map(contenidoCursoMapper::toDto);
    }

    /**
     * Delete the contenidoCurso by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ContenidoCurso : {}", id);
        contenidoCursoRepository.deleteById(id);
    }
}
