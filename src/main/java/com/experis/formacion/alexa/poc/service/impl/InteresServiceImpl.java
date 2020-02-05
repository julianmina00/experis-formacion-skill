package com.experis.formacion.alexa.poc.service.impl;

import com.experis.formacion.alexa.poc.service.InteresService;
import com.experis.formacion.alexa.poc.domain.Interes;
import com.experis.formacion.alexa.poc.repository.InteresRepository;
import com.experis.formacion.alexa.poc.service.dto.InteresDTO;
import com.experis.formacion.alexa.poc.service.mapper.InteresMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Interes}.
 */
@Service
@Transactional
public class InteresServiceImpl implements InteresService {

    private final Logger log = LoggerFactory.getLogger(InteresServiceImpl.class);

    private final InteresRepository interesRepository;

    private final InteresMapper interesMapper;

    public InteresServiceImpl(InteresRepository interesRepository, InteresMapper interesMapper) {
        this.interesRepository = interesRepository;
        this.interesMapper = interesMapper;
    }

    /**
     * Save a interes.
     *
     * @param interesDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public InteresDTO save(InteresDTO interesDTO) {
        log.debug("Request to save Interes : {}", interesDTO);
        Interes interes = interesMapper.toEntity(interesDTO);
        interes = interesRepository.save(interes);
        return interesMapper.toDto(interes);
    }

    /**
     * Get all the interes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<InteresDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Interes");
        return interesRepository.findAll(pageable)
            .map(interesMapper::toDto);
    }


    /**
     * Get one interes by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<InteresDTO> findOne(Long id) {
        log.debug("Request to get Interes : {}", id);
        return interesRepository.findById(id)
            .map(interesMapper::toDto);
    }

    /**
     * Delete the interes by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Interes : {}", id);
        interesRepository.deleteById(id);
    }
}
