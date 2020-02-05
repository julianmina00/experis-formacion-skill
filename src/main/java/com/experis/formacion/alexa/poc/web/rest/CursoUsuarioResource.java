package com.experis.formacion.alexa.poc.web.rest;

import com.experis.formacion.alexa.poc.service.CursoUsuarioService;
import com.experis.formacion.alexa.poc.web.rest.errors.BadRequestAlertException;
import com.experis.formacion.alexa.poc.service.dto.CursoUsuarioDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.experis.formacion.alexa.poc.domain.CursoUsuario}.
 */
@RestController
@RequestMapping("/api")
public class CursoUsuarioResource {

    private final Logger log = LoggerFactory.getLogger(CursoUsuarioResource.class);

    private static final String ENTITY_NAME = "cursoUsuario";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CursoUsuarioService cursoUsuarioService;

    public CursoUsuarioResource(CursoUsuarioService cursoUsuarioService) {
        this.cursoUsuarioService = cursoUsuarioService;
    }

    /**
     * {@code POST  /curso-usuarios} : Create a new cursoUsuario.
     *
     * @param cursoUsuarioDTO the cursoUsuarioDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cursoUsuarioDTO, or with status {@code 400 (Bad Request)} if the cursoUsuario has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/curso-usuarios")
    public ResponseEntity<CursoUsuarioDTO> createCursoUsuario(@RequestBody CursoUsuarioDTO cursoUsuarioDTO) throws URISyntaxException {
        log.debug("REST request to save CursoUsuario : {}", cursoUsuarioDTO);
        if (cursoUsuarioDTO.getId() != null) {
            throw new BadRequestAlertException("A new cursoUsuario cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CursoUsuarioDTO result = cursoUsuarioService.save(cursoUsuarioDTO);
        return ResponseEntity.created(new URI("/api/curso-usuarios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /curso-usuarios} : Updates an existing cursoUsuario.
     *
     * @param cursoUsuarioDTO the cursoUsuarioDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cursoUsuarioDTO,
     * or with status {@code 400 (Bad Request)} if the cursoUsuarioDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cursoUsuarioDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/curso-usuarios")
    public ResponseEntity<CursoUsuarioDTO> updateCursoUsuario(@RequestBody CursoUsuarioDTO cursoUsuarioDTO) throws URISyntaxException {
        log.debug("REST request to update CursoUsuario : {}", cursoUsuarioDTO);
        if (cursoUsuarioDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CursoUsuarioDTO result = cursoUsuarioService.save(cursoUsuarioDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cursoUsuarioDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /curso-usuarios} : get all the cursoUsuarios.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cursoUsuarios in body.
     */
    @GetMapping("/curso-usuarios")
    public List<CursoUsuarioDTO> getAllCursoUsuarios() {
        log.debug("REST request to get all CursoUsuarios");
        return cursoUsuarioService.findAll();
    }

    /**
     * {@code GET  /curso-usuarios/:id} : get the "id" cursoUsuario.
     *
     * @param id the id of the cursoUsuarioDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cursoUsuarioDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/curso-usuarios/{id}")
    public ResponseEntity<CursoUsuarioDTO> getCursoUsuario(@PathVariable Long id) {
        log.debug("REST request to get CursoUsuario : {}", id);
        Optional<CursoUsuarioDTO> cursoUsuarioDTO = cursoUsuarioService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cursoUsuarioDTO);
    }

    /**
     * {@code DELETE  /curso-usuarios/:id} : delete the "id" cursoUsuario.
     *
     * @param id the id of the cursoUsuarioDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/curso-usuarios/{id}")
    public ResponseEntity<Void> deleteCursoUsuario(@PathVariable Long id) {
        log.debug("REST request to delete CursoUsuario : {}", id);
        cursoUsuarioService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
