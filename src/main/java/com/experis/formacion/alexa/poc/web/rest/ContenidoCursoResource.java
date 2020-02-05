package com.experis.formacion.alexa.poc.web.rest;

import com.experis.formacion.alexa.poc.service.ContenidoCursoService;
import com.experis.formacion.alexa.poc.web.rest.errors.BadRequestAlertException;
import com.experis.formacion.alexa.poc.service.dto.ContenidoCursoDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.experis.formacion.alexa.poc.domain.ContenidoCurso}.
 */
@RestController
@RequestMapping("/api")
public class ContenidoCursoResource {

    private final Logger log = LoggerFactory.getLogger(ContenidoCursoResource.class);

    private static final String ENTITY_NAME = "contenidoCurso";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContenidoCursoService contenidoCursoService;

    public ContenidoCursoResource(ContenidoCursoService contenidoCursoService) {
        this.contenidoCursoService = contenidoCursoService;
    }

    /**
     * {@code POST  /contenido-cursos} : Create a new contenidoCurso.
     *
     * @param contenidoCursoDTO the contenidoCursoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contenidoCursoDTO, or with status {@code 400 (Bad Request)} if the contenidoCurso has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/contenido-cursos")
    public ResponseEntity<ContenidoCursoDTO> createContenidoCurso(@Valid @RequestBody ContenidoCursoDTO contenidoCursoDTO) throws URISyntaxException {
        log.debug("REST request to save ContenidoCurso : {}", contenidoCursoDTO);
        if (contenidoCursoDTO.getId() != null) {
            throw new BadRequestAlertException("A new contenidoCurso cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ContenidoCursoDTO result = contenidoCursoService.save(contenidoCursoDTO);
        return ResponseEntity.created(new URI("/api/contenido-cursos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /contenido-cursos} : Updates an existing contenidoCurso.
     *
     * @param contenidoCursoDTO the contenidoCursoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contenidoCursoDTO,
     * or with status {@code 400 (Bad Request)} if the contenidoCursoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contenidoCursoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/contenido-cursos")
    public ResponseEntity<ContenidoCursoDTO> updateContenidoCurso(@Valid @RequestBody ContenidoCursoDTO contenidoCursoDTO) throws URISyntaxException {
        log.debug("REST request to update ContenidoCurso : {}", contenidoCursoDTO);
        if (contenidoCursoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ContenidoCursoDTO result = contenidoCursoService.save(contenidoCursoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contenidoCursoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /contenido-cursos} : get all the contenidoCursos.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contenidoCursos in body.
     */
    @GetMapping("/contenido-cursos")
    public ResponseEntity<List<ContenidoCursoDTO>> getAllContenidoCursos(Pageable pageable) {
        log.debug("REST request to get a page of ContenidoCursos");
        Page<ContenidoCursoDTO> page = contenidoCursoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /contenido-cursos/:id} : get the "id" contenidoCurso.
     *
     * @param id the id of the contenidoCursoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contenidoCursoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/contenido-cursos/{id}")
    public ResponseEntity<ContenidoCursoDTO> getContenidoCurso(@PathVariable Long id) {
        log.debug("REST request to get ContenidoCurso : {}", id);
        Optional<ContenidoCursoDTO> contenidoCursoDTO = contenidoCursoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contenidoCursoDTO);
    }

    /**
     * {@code DELETE  /contenido-cursos/:id} : delete the "id" contenidoCurso.
     *
     * @param id the id of the contenidoCursoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/contenido-cursos/{id}")
    public ResponseEntity<Void> deleteContenidoCurso(@PathVariable Long id) {
        log.debug("REST request to delete ContenidoCurso : {}", id);
        contenidoCursoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
