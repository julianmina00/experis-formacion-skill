package com.experis.formacion.alexa.poc.web.rest;

import com.experis.formacion.alexa.poc.service.HabilidadService;
import com.experis.formacion.alexa.poc.web.rest.errors.BadRequestAlertException;
import com.experis.formacion.alexa.poc.service.dto.HabilidadDTO;

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
 * REST controller for managing {@link com.experis.formacion.alexa.poc.domain.Habilidad}.
 */
@RestController
@RequestMapping("/api")
public class HabilidadResource {

    private final Logger log = LoggerFactory.getLogger(HabilidadResource.class);

    private static final String ENTITY_NAME = "habilidad";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HabilidadService habilidadService;

    public HabilidadResource(HabilidadService habilidadService) {
        this.habilidadService = habilidadService;
    }

    /**
     * {@code POST  /habilidads} : Create a new habilidad.
     *
     * @param habilidadDTO the habilidadDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new habilidadDTO, or with status {@code 400 (Bad Request)} if the habilidad has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/habilidads")
    public ResponseEntity<HabilidadDTO> createHabilidad(@Valid @RequestBody HabilidadDTO habilidadDTO) throws URISyntaxException {
        log.debug("REST request to save Habilidad : {}", habilidadDTO);
        if (habilidadDTO.getId() != null) {
            throw new BadRequestAlertException("A new habilidad cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HabilidadDTO result = habilidadService.save(habilidadDTO);
        return ResponseEntity.created(new URI("/api/habilidads/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /habilidads} : Updates an existing habilidad.
     *
     * @param habilidadDTO the habilidadDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated habilidadDTO,
     * or with status {@code 400 (Bad Request)} if the habilidadDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the habilidadDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/habilidads")
    public ResponseEntity<HabilidadDTO> updateHabilidad(@Valid @RequestBody HabilidadDTO habilidadDTO) throws URISyntaxException {
        log.debug("REST request to update Habilidad : {}", habilidadDTO);
        if (habilidadDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HabilidadDTO result = habilidadService.save(habilidadDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, habilidadDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /habilidads} : get all the habilidads.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of habilidads in body.
     */
    @GetMapping("/habilidads")
    public ResponseEntity<List<HabilidadDTO>> getAllHabilidads(Pageable pageable) {
        log.debug("REST request to get a page of Habilidads");
        Page<HabilidadDTO> page = habilidadService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /habilidads/:id} : get the "id" habilidad.
     *
     * @param id the id of the habilidadDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the habilidadDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/habilidads/{id}")
    public ResponseEntity<HabilidadDTO> getHabilidad(@PathVariable Long id) {
        log.debug("REST request to get Habilidad : {}", id);
        Optional<HabilidadDTO> habilidadDTO = habilidadService.findOne(id);
        return ResponseUtil.wrapOrNotFound(habilidadDTO);
    }

    /**
     * {@code DELETE  /habilidads/:id} : delete the "id" habilidad.
     *
     * @param id the id of the habilidadDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/habilidads/{id}")
    public ResponseEntity<Void> deleteHabilidad(@PathVariable Long id) {
        log.debug("REST request to delete Habilidad : {}", id);
        habilidadService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
