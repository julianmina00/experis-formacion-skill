package com.experis.formacion.alexa.poc.service.impl;

import com.experis.formacion.alexa.poc.service.HabilidadService;
import com.experis.formacion.alexa.poc.domain.Habilidad;
import com.experis.formacion.alexa.poc.repository.HabilidadRepository;
import com.experis.formacion.alexa.poc.service.dto.HabilidadDTO;
import com.experis.formacion.alexa.poc.service.mapper.HabilidadMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Habilidad}.
 */
@Service
@Transactional
public class HabilidadServiceImpl implements HabilidadService {

    private final Logger log = LoggerFactory.getLogger(HabilidadServiceImpl.class);

    private final HabilidadRepository habilidadRepository;

    private final HabilidadMapper habilidadMapper;

    public HabilidadServiceImpl(HabilidadRepository habilidadRepository, HabilidadMapper habilidadMapper) {
        this.habilidadRepository = habilidadRepository;
        this.habilidadMapper = habilidadMapper;
    }

    /**
     * Save a habilidad.
     *
     * @param habilidadDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public HabilidadDTO save(HabilidadDTO habilidadDTO) {
        log.debug("Request to save Habilidad : {}", habilidadDTO);
        Habilidad habilidad = habilidadMapper.toEntity(habilidadDTO);
        habilidad = habilidadRepository.save(habilidad);
        return habilidadMapper.toDto(habilidad);
    }

    /**
     * Get all the habilidads.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<HabilidadDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Habilidads");
        return habilidadRepository.findAll(pageable)
            .map(habilidadMapper::toDto);
    }


    /**
     * Get one habilidad by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<HabilidadDTO> findOne(Long id) {
        log.debug("Request to get Habilidad : {}", id);
        return habilidadRepository.findById(id)
            .map(habilidadMapper::toDto);
    }

    /**
     * Delete the habilidad by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Habilidad : {}", id);
        habilidadRepository.deleteById(id);
    }
}
