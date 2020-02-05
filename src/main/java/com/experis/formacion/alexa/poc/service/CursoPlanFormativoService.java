package com.experis.formacion.alexa.poc.service;

import com.experis.formacion.alexa.poc.domain.CursoPlanFormativo;
import com.experis.formacion.alexa.poc.repository.CursoPlanFormativoRepository;
import com.experis.formacion.alexa.poc.service.dto.CursoPlanFormativoDTO;
import com.experis.formacion.alexa.poc.service.mapper.CursoPlanFormativoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link CursoPlanFormativo}.
 */
@Service
@Transactional
public class CursoPlanFormativoService {

    private final Logger log = LoggerFactory.getLogger(CursoPlanFormativoService.class);

    private final CursoPlanFormativoRepository cursoPlanFormativoRepository;

    private final CursoPlanFormativoMapper cursoPlanFormativoMapper;

    public CursoPlanFormativoService(CursoPlanFormativoRepository cursoPlanFormativoRepository, CursoPlanFormativoMapper cursoPlanFormativoMapper) {
        this.cursoPlanFormativoRepository = cursoPlanFormativoRepository;
        this.cursoPlanFormativoMapper = cursoPlanFormativoMapper;
    }

    /**
     * Save a cursoPlanFormativo.
     *
     * @param cursoPlanFormativoDTO the entity to save.
     * @return the persisted entity.
     */
    public CursoPlanFormativoDTO save(CursoPlanFormativoDTO cursoPlanFormativoDTO) {
        log.debug("Request to save CursoPlanFormativo : {}", cursoPlanFormativoDTO);
        CursoPlanFormativo cursoPlanFormativo = cursoPlanFormativoMapper.toEntity(cursoPlanFormativoDTO);
        cursoPlanFormativo = cursoPlanFormativoRepository.save(cursoPlanFormativo);
        return cursoPlanFormativoMapper.toDto(cursoPlanFormativo);
    }

    /**
     * Get all the cursoPlanFormativos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CursoPlanFormativoDTO> findAll() {
        log.debug("Request to get all CursoPlanFormativos");
        return cursoPlanFormativoRepository.findAll().stream()
            .map(cursoPlanFormativoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one cursoPlanFormativo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CursoPlanFormativoDTO> findOne(Long id) {
        log.debug("Request to get CursoPlanFormativo : {}", id);
        return cursoPlanFormativoRepository.findById(id)
            .map(cursoPlanFormativoMapper::toDto);
    }

    /**
     * Delete the cursoPlanFormativo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CursoPlanFormativo : {}", id);
        cursoPlanFormativoRepository.deleteById(id);
    }
}
