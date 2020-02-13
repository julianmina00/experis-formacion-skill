package com.experis.formacion.alexa.poc.service.impl;

import com.experis.formacion.alexa.poc.service.RelacionTipoHabilidadService;
import com.experis.formacion.alexa.poc.domain.RelacionTipoHabilidad;
import com.experis.formacion.alexa.poc.repository.RelacionTipoHabilidadRepository;
import com.experis.formacion.alexa.poc.service.dto.RelacionTipoHabilidadDTO;
import com.experis.formacion.alexa.poc.service.mapper.RelacionTipoHabilidadMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link RelacionTipoHabilidad}.
 */
@Service
@Transactional
public class RelacionTipoHabilidadServiceImpl implements RelacionTipoHabilidadService {

    private final Logger log = LoggerFactory.getLogger(RelacionTipoHabilidadServiceImpl.class);

    private final RelacionTipoHabilidadRepository relacionTipoHabilidadRepository;

    private final RelacionTipoHabilidadMapper relacionTipoHabilidadMapper;

    public RelacionTipoHabilidadServiceImpl(RelacionTipoHabilidadRepository relacionTipoHabilidadRepository, RelacionTipoHabilidadMapper relacionTipoHabilidadMapper) {
        this.relacionTipoHabilidadRepository = relacionTipoHabilidadRepository;
        this.relacionTipoHabilidadMapper = relacionTipoHabilidadMapper;
    }

    /**
     * Save a relacionTipoHabilidad.
     *
     * @param relacionTipoHabilidadDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public RelacionTipoHabilidadDTO save(RelacionTipoHabilidadDTO relacionTipoHabilidadDTO) {
        log.debug("Request to save RelacionTipoHabilidad : {}", relacionTipoHabilidadDTO);
        RelacionTipoHabilidad relacionTipoHabilidad = relacionTipoHabilidadMapper.toEntity(relacionTipoHabilidadDTO);
        relacionTipoHabilidad = relacionTipoHabilidadRepository.save(relacionTipoHabilidad);
        return relacionTipoHabilidadMapper.toDto(relacionTipoHabilidad);
    }

    /**
     * Get all the relacionTipoHabilidads.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RelacionTipoHabilidadDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RelacionTipoHabilidads");
        return relacionTipoHabilidadRepository.findAll(pageable)
            .map(relacionTipoHabilidadMapper::toDto);
    }


    /**
     * Get one relacionTipoHabilidad by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RelacionTipoHabilidadDTO> findOne(Long id) {
        log.debug("Request to get RelacionTipoHabilidad : {}", id);
        return relacionTipoHabilidadRepository.findById(id)
            .map(relacionTipoHabilidadMapper::toDto);
    }

    /**
     * Delete the relacionTipoHabilidad by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RelacionTipoHabilidad : {}", id);
        relacionTipoHabilidadRepository.deleteById(id);
    }
}
