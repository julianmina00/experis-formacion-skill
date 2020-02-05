package com.experis.formacion.alexa.poc.web.rest;

import com.experis.formacion.alexa.poc.service.CursoPlanFormativoService;
import com.experis.formacion.alexa.poc.web.rest.errors.BadRequestAlertException;
import com.experis.formacion.alexa.poc.service.dto.CursoPlanFormativoDTO;

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
 * REST controller for managing {@link com.experis.formacion.alexa.poc.domain.CursoPlanFormativo}.
 */
@RestController
@RequestMapping("/api")
public class CursoPlanFormativoResource {

    private final Logger log = LoggerFactory.getLogger(CursoPlanFormativoResource.class);

    private static final String ENTITY_NAME = "cursoPlanFormativo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CursoPlanFormativoService cursoPlanFormativoService;

    public CursoPlanFormativoResource(CursoPlanFormativoService cursoPlanFormativoService) {
        this.cursoPlanFormativoService = cursoPlanFormativoService;
    }

    /**
     * {@code POST  /curso-plan-formativos} : Create a new cursoPlanFormativo.
     *
     * @param cursoPlanFormativoDTO the cursoPlanFormativoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cursoPlanFormativoDTO, or with status {@code 400 (Bad Request)} if the cursoPlanFormativo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/curso-plan-formativos")
    public ResponseEntity<CursoPlanFormativoDTO> createCursoPlanFormativo(@RequestBody CursoPlanFormativoDTO cursoPlanFormativoDTO) throws URISyntaxException {
        log.debug("REST request to save CursoPlanFormativo : {}", cursoPlanFormativoDTO);
        if (cursoPlanFormativoDTO.getId() != null) {
            throw new BadRequestAlertException("A new cursoPlanFormativo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CursoPlanFormativoDTO result = cursoPlanFormativoService.save(cursoPlanFormativoDTO);
        return ResponseEntity.created(new URI("/api/curso-plan-formativos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /curso-plan-formativos} : Updates an existing cursoPlanFormativo.
     *
     * @param cursoPlanFormativoDTO the cursoPlanFormativoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cursoPlanFormativoDTO,
     * or with status {@code 400 (Bad Request)} if the cursoPlanFormativoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cursoPlanFormativoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/curso-plan-formativos")
    public ResponseEntity<CursoPlanFormativoDTO> updateCursoPlanFormativo(@RequestBody CursoPlanFormativoDTO cursoPlanFormativoDTO) throws URISyntaxException {
        log.debug("REST request to update CursoPlanFormativo : {}", cursoPlanFormativoDTO);
        if (cursoPlanFormativoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CursoPlanFormativoDTO result = cursoPlanFormativoService.save(cursoPlanFormativoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cursoPlanFormativoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /curso-plan-formativos} : get all the cursoPlanFormativos.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cursoPlanFormativos in body.
     */
    @GetMapping("/curso-plan-formativos")
    public List<CursoPlanFormativoDTO> getAllCursoPlanFormativos() {
        log.debug("REST request to get all CursoPlanFormativos");
        return cursoPlanFormativoService.findAll();
    }

    /**
     * {@code GET  /curso-plan-formativos/:id} : get the "id" cursoPlanFormativo.
     *
     * @param id the id of the cursoPlanFormativoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cursoPlanFormativoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/curso-plan-formativos/{id}")
    public ResponseEntity<CursoPlanFormativoDTO> getCursoPlanFormativo(@PathVariable Long id) {
        log.debug("REST request to get CursoPlanFormativo : {}", id);
        Optional<CursoPlanFormativoDTO> cursoPlanFormativoDTO = cursoPlanFormativoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cursoPlanFormativoDTO);
    }

    /**
     * {@code DELETE  /curso-plan-formativos/:id} : delete the "id" cursoPlanFormativo.
     *
     * @param id the id of the cursoPlanFormativoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/curso-plan-formativos/{id}")
    public ResponseEntity<Void> deleteCursoPlanFormativo(@PathVariable Long id) {
        log.debug("REST request to delete CursoPlanFormativo : {}", id);
        cursoPlanFormativoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
