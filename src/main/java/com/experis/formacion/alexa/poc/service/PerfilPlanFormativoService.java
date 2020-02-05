package com.experis.formacion.alexa.poc.service;

import com.experis.formacion.alexa.poc.domain.PerfilPlanFormativo;
import com.experis.formacion.alexa.poc.repository.PerfilPlanFormativoRepository;
import com.experis.formacion.alexa.poc.service.dto.PerfilPlanFormativoDTO;
import com.experis.formacion.alexa.poc.service.mapper.PerfilPlanFormativoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link PerfilPlanFormativo}.
 */
@Service
@Transactional
public class PerfilPlanFormativoService {

    private final Logger log = LoggerFactory.getLogger(PerfilPlanFormativoService.class);

    private final PerfilPlanFormativoRepository perfilPlanFormativoRepository;

    private final PerfilPlanFormativoMapper perfilPlanFormativoMapper;

    public PerfilPlanFormativoService(PerfilPlanFormativoRepository perfilPlanFormativoRepository, PerfilPlanFormativoMapper perfilPlanFormativoMapper) {
        this.perfilPlanFormativoRepository = perfilPlanFormativoRepository;
        this.perfilPlanFormativoMapper = perfilPlanFormativoMapper;
    }

    /**
     * Save a perfilPlanFormativo.
     *
     * @param perfilPlanFormativoDTO the entity to save.
     * @return the persisted entity.
     */
    public PerfilPlanFormativoDTO save(PerfilPlanFormativoDTO perfilPlanFormativoDTO) {
        log.debug("Request to save PerfilPlanFormativo : {}", perfilPlanFormativoDTO);
        PerfilPlanFormativo perfilPlanFormativo = perfilPlanFormativoMapper.toEntity(perfilPlanFormativoDTO);
        perfilPlanFormativo = perfilPlanFormativoRepository.save(perfilPlanFormativo);
        return perfilPlanFormativoMapper.toDto(perfilPlanFormativo);
    }

    /**
     * Get all the perfilPlanFormativos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PerfilPlanFormativoDTO> findAll() {
        log.debug("Request to get all PerfilPlanFormativos");
        return perfilPlanFormativoRepository.findAll().stream()
            .map(perfilPlanFormativoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one perfilPlanFormativo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PerfilPlanFormativoDTO> findOne(Long id) {
        log.debug("Request to get PerfilPlanFormativo : {}", id);
        return perfilPlanFormativoRepository.findById(id)
            .map(perfilPlanFormativoMapper::toDto);
    }

    /**
     * Delete the perfilPlanFormativo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PerfilPlanFormativo : {}", id);
        perfilPlanFormativoRepository.deleteById(id);
    }
}
