package com.experis.formacion.alexa.poc.service;

import com.experis.formacion.alexa.poc.domain.CursoUsuario;
import com.experis.formacion.alexa.poc.repository.CursoUsuarioRepository;
import com.experis.formacion.alexa.poc.service.dto.CursoUsuarioDTO;
import com.experis.formacion.alexa.poc.service.mapper.CursoUsuarioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link CursoUsuario}.
 */
@Service
@Transactional
public class CursoUsuarioService {

    private final Logger log = LoggerFactory.getLogger(CursoUsuarioService.class);

    private final CursoUsuarioRepository cursoUsuarioRepository;

    private final CursoUsuarioMapper cursoUsuarioMapper;

    public CursoUsuarioService(CursoUsuarioRepository cursoUsuarioRepository, CursoUsuarioMapper cursoUsuarioMapper) {
        this.cursoUsuarioRepository = cursoUsuarioRepository;
        this.cursoUsuarioMapper = cursoUsuarioMapper;
    }

    /**
     * Save a cursoUsuario.
     *
     * @param cursoUsuarioDTO the entity to save.
     * @return the persisted entity.
     */
    public CursoUsuarioDTO save(CursoUsuarioDTO cursoUsuarioDTO) {
        log.debug("Request to save CursoUsuario : {}", cursoUsuarioDTO);
        CursoUsuario cursoUsuario = cursoUsuarioMapper.toEntity(cursoUsuarioDTO);
        cursoUsuario = cursoUsuarioRepository.save(cursoUsuario);
        return cursoUsuarioMapper.toDto(cursoUsuario);
    }

    /**
     * Get all the cursoUsuarios.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CursoUsuarioDTO> findAll() {
        log.debug("Request to get all CursoUsuarios");
        return cursoUsuarioRepository.findAll().stream()
            .map(cursoUsuarioMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one cursoUsuario by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CursoUsuarioDTO> findOne(Long id) {
        log.debug("Request to get CursoUsuario : {}", id);
        return cursoUsuarioRepository.findById(id)
            .map(cursoUsuarioMapper::toDto);
    }

    /**
     * Delete the cursoUsuario by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CursoUsuario : {}", id);
        cursoUsuarioRepository.deleteById(id);
    }
}
