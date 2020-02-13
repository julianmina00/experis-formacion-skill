package com.experis.formacion.alexa.poc.service;

import com.experis.formacion.alexa.poc.domain.NivelIdioma;
import com.experis.formacion.alexa.poc.repository.NivelIdiomaRepository;
import com.experis.formacion.alexa.poc.service.dto.NivelIdiomaDTO;
import com.experis.formacion.alexa.poc.service.mapper.NivelIdiomaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link NivelIdioma}.
 */
@Service
@Transactional
public class NivelIdiomaService {

    private final Logger log = LoggerFactory.getLogger(NivelIdiomaService.class);

    private final NivelIdiomaRepository nivelIdiomaRepository;

    private final NivelIdiomaMapper nivelIdiomaMapper;

    public NivelIdiomaService(NivelIdiomaRepository nivelIdiomaRepository, NivelIdiomaMapper nivelIdiomaMapper) {
        this.nivelIdiomaRepository = nivelIdiomaRepository;
        this.nivelIdiomaMapper = nivelIdiomaMapper;
    }

    /**
     * Save a nivelIdioma.
     *
     * @param nivelIdiomaDTO the entity to save.
     * @return the persisted entity.
     */
    public NivelIdiomaDTO save(NivelIdiomaDTO nivelIdiomaDTO) {
        log.debug("Request to save NivelIdioma : {}", nivelIdiomaDTO);
        NivelIdioma nivelIdioma = nivelIdiomaMapper.toEntity(nivelIdiomaDTO);
        nivelIdioma = nivelIdiomaRepository.save(nivelIdioma);
        return nivelIdiomaMapper.toDto(nivelIdioma);
    }

    /**
     * Get all the nivelIdiomas.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<NivelIdiomaDTO> findAll() {
        log.debug("Request to get all NivelIdiomas");
        return nivelIdiomaRepository.findAll().stream()
            .map(nivelIdiomaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one nivelIdioma by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NivelIdiomaDTO> findOne(Long id) {
        log.debug("Request to get NivelIdioma : {}", id);
        return nivelIdiomaRepository.findById(id)
            .map(nivelIdiomaMapper::toDto);
    }

    /**
     * Delete the nivelIdioma by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete NivelIdioma : {}", id);
        nivelIdiomaRepository.deleteById(id);
    }
}
