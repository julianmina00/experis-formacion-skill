package com.experis.formacion.alexa.poc.service.impl;

import com.experis.formacion.alexa.poc.service.TipoHabilidadService;
import com.experis.formacion.alexa.poc.domain.TipoHabilidad;
import com.experis.formacion.alexa.poc.repository.TipoHabilidadRepository;
import com.experis.formacion.alexa.poc.service.dto.TipoHabilidadDTO;
import com.experis.formacion.alexa.poc.service.mapper.TipoHabilidadMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TipoHabilidad}.
 */
@Service
@Transactional
public class TipoHabilidadServiceImpl implements TipoHabilidadService {

    private final Logger log = LoggerFactory.getLogger(TipoHabilidadServiceImpl.class);

    private final TipoHabilidadRepository tipoHabilidadRepository;

    private final TipoHabilidadMapper tipoHabilidadMapper;

    public TipoHabilidadServiceImpl(TipoHabilidadRepository tipoHabilidadRepository, TipoHabilidadMapper tipoHabilidadMapper) {
        this.tipoHabilidadRepository = tipoHabilidadRepository;
        this.tipoHabilidadMapper = tipoHabilidadMapper;
    }

    /**
     * Save a tipoHabilidad.
     *
     * @param tipoHabilidadDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TipoHabilidadDTO save(TipoHabilidadDTO tipoHabilidadDTO) {
        log.debug("Request to save TipoHabilidad : {}", tipoHabilidadDTO);
        TipoHabilidad tipoHabilidad = tipoHabilidadMapper.toEntity(tipoHabilidadDTO);
        tipoHabilidad = tipoHabilidadRepository.save(tipoHabilidad);
        return tipoHabilidadMapper.toDto(tipoHabilidad);
    }

    /**
     * Get all the tipoHabilidads.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TipoHabilidadDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TipoHabilidads");
        return tipoHabilidadRepository.findAll(pageable)
            .map(tipoHabilidadMapper::toDto);
    }


    /**
     * Get one tipoHabilidad by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TipoHabilidadDTO> findOne(Long id) {
        log.debug("Request to get TipoHabilidad : {}", id);
        return tipoHabilidadRepository.findById(id)
            .map(tipoHabilidadMapper::toDto);
    }

    /**
     * Delete the tipoHabilidad by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TipoHabilidad : {}", id);
        tipoHabilidadRepository.deleteById(id);
    }
}
