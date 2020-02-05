package com.experis.formacion.alexa.poc.web.rest;

import com.experis.formacion.alexa.poc.service.InteresService;
import com.experis.formacion.alexa.poc.web.rest.errors.BadRequestAlertException;
import com.experis.formacion.alexa.poc.service.dto.InteresDTO;

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
 * REST controller for managing {@link com.experis.formacion.alexa.poc.domain.Interes}.
 */
@RestController
@RequestMapping("/api")
public class InteresResource {

    private final Logger log = LoggerFactory.getLogger(InteresResource.class);

    private static final String ENTITY_NAME = "interes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InteresService interesService;

    public InteresResource(InteresService interesService) {
        this.interesService = interesService;
    }

    /**
     * {@code POST  /interes} : Create a new interes.
     *
     * @param interesDTO the interesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new interesDTO, or with status {@code 400 (Bad Request)} if the interes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/interes")
    public ResponseEntity<InteresDTO> createInteres(@Valid @RequestBody InteresDTO interesDTO) throws URISyntaxException {
        log.debug("REST request to save Interes : {}", interesDTO);
        if (interesDTO.getId() != null) {
            throw new BadRequestAlertException("A new interes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InteresDTO result = interesService.save(interesDTO);
        return ResponseEntity.created(new URI("/api/interes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /interes} : Updates an existing interes.
     *
     * @param interesDTO the interesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated interesDTO,
     * or with status {@code 400 (Bad Request)} if the interesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the interesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/interes")
    public ResponseEntity<InteresDTO> updateInteres(@Valid @RequestBody InteresDTO interesDTO) throws URISyntaxException {
        log.debug("REST request to update Interes : {}", interesDTO);
        if (interesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InteresDTO result = interesService.save(interesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, interesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /interes} : get all the interes.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of interes in body.
     */
    @GetMapping("/interes")
    public ResponseEntity<List<InteresDTO>> getAllInteres(Pageable pageable) {
        log.debug("REST request to get a page of Interes");
        Page<InteresDTO> page = interesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /interes/:id} : get the "id" interes.
     *
     * @param id the id of the interesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the interesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/interes/{id}")
    public ResponseEntity<InteresDTO> getInteres(@PathVariable Long id) {
        log.debug("REST request to get Interes : {}", id);
        Optional<InteresDTO> interesDTO = interesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(interesDTO);
    }

    /**
     * {@code DELETE  /interes/:id} : delete the "id" interes.
     *
     * @param id the id of the interesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/interes/{id}")
    public ResponseEntity<Void> deleteInteres(@PathVariable Long id) {
        log.debug("REST request to delete Interes : {}", id);
        interesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
