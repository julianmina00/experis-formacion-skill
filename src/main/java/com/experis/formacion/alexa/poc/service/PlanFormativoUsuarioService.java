package com.experis.formacion.alexa.poc.service;

import com.experis.formacion.alexa.poc.domain.PlanFormativoUsuario;
import com.experis.formacion.alexa.poc.repository.PlanFormativoUsuarioRepository;
import com.experis.formacion.alexa.poc.service.dto.PlanFormativoUsuarioDTO;
import com.experis.formacion.alexa.poc.service.mapper.PlanFormativoUsuarioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link PlanFormativoUsuario}.
 */
@Service
@Transactional
public class PlanFormativoUsuarioService {

    private final Logger log = LoggerFactory.getLogger(PlanFormativoUsuarioService.class);

    private final PlanFormativoUsuarioRepository planFormativoUsuarioRepository;

    private final PlanFormativoUsuarioMapper planFormativoUsuarioMapper;

    public PlanFormativoUsuarioService(PlanFormativoUsuarioRepository planFormativoUsuarioRepository, PlanFormativoUsuarioMapper planFormativoUsuarioMapper) {
        this.planFormativoUsuarioRepository = planFormativoUsuarioRepository;
        this.planFormativoUsuarioMapper = planFormativoUsuarioMapper;
    }

    /**
     * Save a planFormativoUsuario.
     *
     * @param planFormativoUsuarioDTO the entity to save.
     * @return the persisted entity.
     */
    public PlanFormativoUsuarioDTO save(PlanFormativoUsuarioDTO planFormativoUsuarioDTO) {
        log.debug("Request to save PlanFormativoUsuario : {}", planFormativoUsuarioDTO);
        PlanFormativoUsuario planFormativoUsuario = planFormativoUsuarioMapper.toEntity(planFormativoUsuarioDTO);
        planFormativoUsuario = planFormativoUsuarioRepository.save(planFormativoUsuario);
        return planFormativoUsuarioMapper.toDto(planFormativoUsuario);
    }

    /**
     * Get all the planFormativoUsuarios.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PlanFormativoUsuarioDTO> findAll() {
        log.debug("Request to get all PlanFormativoUsuarios");
        return planFormativoUsuarioRepository.findAll().stream()
            .map(planFormativoUsuarioMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one planFormativoUsuario by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PlanFormativoUsuarioDTO> findOne(Long id) {
        log.debug("Request to get PlanFormativoUsuario : {}", id);
        return planFormativoUsuarioRepository.findById(id)
            .map(planFormativoUsuarioMapper::toDto);
    }

    /**
     * Delete the planFormativoUsuario by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PlanFormativoUsuario : {}", id);
        planFormativoUsuarioRepository.deleteById(id);
    }
}
