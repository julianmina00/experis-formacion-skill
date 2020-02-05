package com.experis.formacion.alexa.poc.service.impl;

import com.experis.formacion.alexa.poc.service.PlanFormativoService;
import com.experis.formacion.alexa.poc.domain.PlanFormativo;
import com.experis.formacion.alexa.poc.repository.PlanFormativoRepository;
import com.experis.formacion.alexa.poc.service.dto.PlanFormativoDTO;
import com.experis.formacion.alexa.poc.service.mapper.PlanFormativoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link PlanFormativo}.
 */
@Service
@Transactional
public class PlanFormativoServiceImpl implements PlanFormativoService {

    private final Logger log = LoggerFactory.getLogger(PlanFormativoServiceImpl.class);

    private final PlanFormativoRepository planFormativoRepository;

    private final PlanFormativoMapper planFormativoMapper;

    public PlanFormativoServiceImpl(PlanFormativoRepository planFormativoRepository, PlanFormativoMapper planFormativoMapper) {
        this.planFormativoRepository = planFormativoRepository;
        this.planFormativoMapper = planFormativoMapper;
    }

    /**
     * Save a planFormativo.
     *
     * @param planFormativoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PlanFormativoDTO save(PlanFormativoDTO planFormativoDTO) {
        log.debug("Request to save PlanFormativo : {}", planFormativoDTO);
        PlanFormativo planFormativo = planFormativoMapper.toEntity(planFormativoDTO);
        planFormativo = planFormativoRepository.save(planFormativo);
        return planFormativoMapper.toDto(planFormativo);
    }

    /**
     * Get all the planFormativos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PlanFormativoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PlanFormativos");
        return planFormativoRepository.findAll(pageable)
            .map(planFormativoMapper::toDto);
    }


    /**
     * Get one planFormativo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PlanFormativoDTO> findOne(Long id) {
        log.debug("Request to get PlanFormativo : {}", id);
        return planFormativoRepository.findById(id)
            .map(planFormativoMapper::toDto);
    }

    /**
     * Delete the planFormativo by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PlanFormativo : {}", id);
        planFormativoRepository.deleteById(id);
    }
}
