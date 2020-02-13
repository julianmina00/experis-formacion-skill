package com.experis.formacion.alexa.poc.service.impl;

import com.experis.formacion.alexa.poc.service.RelacionTipoInteresService;
import com.experis.formacion.alexa.poc.domain.RelacionTipoInteres;
import com.experis.formacion.alexa.poc.repository.RelacionTipoInteresRepository;
import com.experis.formacion.alexa.poc.service.dto.RelacionTipoInteresDTO;
import com.experis.formacion.alexa.poc.service.mapper.RelacionTipoInteresMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link RelacionTipoInteres}.
 */
@Service
@Transactional
public class RelacionTipoInteresServiceImpl implements RelacionTipoInteresService {

    private final Logger log = LoggerFactory.getLogger(RelacionTipoInteresServiceImpl.class);

    private final RelacionTipoInteresRepository relacionTipoInteresRepository;

    private final RelacionTipoInteresMapper relacionTipoInteresMapper;

    public RelacionTipoInteresServiceImpl(RelacionTipoInteresRepository relacionTipoInteresRepository, RelacionTipoInteresMapper relacionTipoInteresMapper) {
        this.relacionTipoInteresRepository = relacionTipoInteresRepository;
        this.relacionTipoInteresMapper = relacionTipoInteresMapper;
    }

    /**
     * Save a relacionTipoInteres.
     *
     * @param relacionTipoInteresDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public RelacionTipoInteresDTO save(RelacionTipoInteresDTO relacionTipoInteresDTO) {
        log.debug("Request to save RelacionTipoInteres : {}", relacionTipoInteresDTO);
        RelacionTipoInteres relacionTipoInteres = relacionTipoInteresMapper.toEntity(relacionTipoInteresDTO);
        relacionTipoInteres = relacionTipoInteresRepository.save(relacionTipoInteres);
        return relacionTipoInteresMapper.toDto(relacionTipoInteres);
    }

    /**
     * Get all the relacionTipoInteres.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RelacionTipoInteresDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RelacionTipoInteres");
        return relacionTipoInteresRepository.findAll(pageable)
            .map(relacionTipoInteresMapper::toDto);
    }


    /**
     * Get one relacionTipoInteres by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RelacionTipoInteresDTO> findOne(Long id) {
        log.debug("Request to get RelacionTipoInteres : {}", id);
        return relacionTipoInteresRepository.findById(id)
            .map(relacionTipoInteresMapper::toDto);
    }

    /**
     * Delete the relacionTipoInteres by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RelacionTipoInteres : {}", id);
        relacionTipoInteresRepository.deleteById(id);
    }
}
