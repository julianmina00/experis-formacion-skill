package com.experis.formacion.alexa.poc.web.rest;

import com.experis.formacion.alexa.poc.service.NivelIdiomaService;
import com.experis.formacion.alexa.poc.web.rest.errors.BadRequestAlertException;
import com.experis.formacion.alexa.poc.service.dto.NivelIdiomaDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.experis.formacion.alexa.poc.domain.NivelIdioma}.
 */
@RestController
@RequestMapping("/api")
public class NivelIdiomaResource {

    private final Logger log = LoggerFactory.getLogger(NivelIdiomaResource.class);

    private static final String ENTITY_NAME = "nivelIdioma";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NivelIdiomaService nivelIdiomaService;

    public NivelIdiomaResource(NivelIdiomaService nivelIdiomaService) {
        this.nivelIdiomaService = nivelIdiomaService;
    }

    /**
     * {@code POST  /nivel-idiomas} : Create a new nivelIdioma.
     *
     * @param nivelIdiomaDTO the nivelIdiomaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nivelIdiomaDTO, or with status {@code 400 (Bad Request)} if the nivelIdioma has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nivel-idiomas")
    public ResponseEntity<NivelIdiomaDTO> createNivelIdioma(@Valid @RequestBody NivelIdiomaDTO nivelIdiomaDTO) throws URISyntaxException {
        log.debug("REST request to save NivelIdioma : {}", nivelIdiomaDTO);
        if (nivelIdiomaDTO.getId() != null) {
            throw new BadRequestAlertException("A new nivelIdioma cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NivelIdiomaDTO result = nivelIdiomaService.save(nivelIdiomaDTO);
        return ResponseEntity.created(new URI("/api/nivel-idiomas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nivel-idiomas} : Updates an existing nivelIdioma.
     *
     * @param nivelIdiomaDTO the nivelIdiomaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nivelIdiomaDTO,
     * or with status {@code 400 (Bad Request)} if the nivelIdiomaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nivelIdiomaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nivel-idiomas")
    public ResponseEntity<NivelIdiomaDTO> updateNivelIdioma(@Valid @RequestBody NivelIdiomaDTO nivelIdiomaDTO) throws URISyntaxException {
        log.debug("REST request to update NivelIdioma : {}", nivelIdiomaDTO);
        if (nivelIdiomaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NivelIdiomaDTO result = nivelIdiomaService.save(nivelIdiomaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nivelIdiomaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /nivel-idiomas} : get all the nivelIdiomas.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nivelIdiomas in body.
     */
    @GetMapping("/nivel-idiomas")
    public List<NivelIdiomaDTO> getAllNivelIdiomas() {
        log.debug("REST request to get all NivelIdiomas");
        return nivelIdiomaService.findAll();
    }

    /**
     * {@code GET  /nivel-idiomas/:id} : get the "id" nivelIdioma.
     *
     * @param id the id of the nivelIdiomaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nivelIdiomaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nivel-idiomas/{id}")
    public ResponseEntity<NivelIdiomaDTO> getNivelIdioma(@PathVariable Long id) {
        log.debug("REST request to get NivelIdioma : {}", id);
        Optional<NivelIdiomaDTO> nivelIdiomaDTO = nivelIdiomaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nivelIdiomaDTO);
    }

    /**
     * {@code DELETE  /nivel-idiomas/:id} : delete the "id" nivelIdioma.
     *
     * @param id the id of the nivelIdiomaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nivel-idiomas/{id}")
    public ResponseEntity<Void> deleteNivelIdioma(@PathVariable Long id) {
        log.debug("REST request to delete NivelIdioma : {}", id);
        nivelIdiomaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
