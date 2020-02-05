package com.experis.formacion.alexa.poc.service.impl;

import com.experis.formacion.alexa.poc.service.TipoInteresService;
import com.experis.formacion.alexa.poc.domain.TipoInteres;
import com.experis.formacion.alexa.poc.repository.TipoInteresRepository;
import com.experis.formacion.alexa.poc.service.dto.TipoInteresDTO;
import com.experis.formacion.alexa.poc.service.mapper.TipoInteresMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TipoInteres}.
 */
@Service
@Transactional
public class TipoInteresServiceImpl implements TipoInteresService {

    private final Logger log = LoggerFactory.getLogger(TipoInteresServiceImpl.class);

    private final TipoInteresRepository tipoInteresRepository;

    private final TipoInteresMapper tipoInteresMapper;

    public TipoInteresServiceImpl(TipoInteresRepository tipoInteresRepository, TipoInteresMapper tipoInteresMapper) {
        this.tipoInteresRepository = tipoInteresRepository;
        this.tipoInteresMapper = tipoInteresMapper;
    }

    /**
     * Save a tipoInteres.
     *
     * @param tipoInteresDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TipoInteresDTO save(TipoInteresDTO tipoInteresDTO) {
        log.debug("Request to save TipoInteres : {}", tipoInteresDTO);
        TipoInteres tipoInteres = tipoInteresMapper.toEntity(tipoInteresDTO);
        tipoInteres = tipoInteresRepository.save(tipoInteres);
        return tipoInteresMapper.toDto(tipoInteres);
    }

    /**
     * Get all the tipoInteres.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TipoInteresDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TipoInteres");
        return tipoInteresRepository.findAll(pageable)
            .map(tipoInteresMapper::toDto);
    }


    /**
     * Get one tipoInteres by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TipoInteresDTO> findOne(Long id) {
        log.debug("Request to get TipoInteres : {}", id);
        return tipoInteresRepository.findById(id)
            .map(tipoInteresMapper::toDto);
    }

    /**
     * Delete the tipoInteres by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TipoInteres : {}", id);
        tipoInteresRepository.deleteById(id);
    }
}
