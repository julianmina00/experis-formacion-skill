package com.experis.formacion.alexa.poc.web.rest;

import com.experis.formacion.alexa.poc.service.RelacionTipoInteresService;
import com.experis.formacion.alexa.poc.web.rest.errors.BadRequestAlertException;
import com.experis.formacion.alexa.poc.service.dto.RelacionTipoInteresDTO;

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
 * REST controller for managing {@link com.experis.formacion.alexa.poc.domain.RelacionTipoInteres}.
 */
@RestController
@RequestMapping("/api")
public class RelacionTipoInteresResource {

    private final Logger log = LoggerFactory.getLogger(RelacionTipoInteresResource.class);

    private static final String ENTITY_NAME = "relacionTipoInteres";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RelacionTipoInteresService relacionTipoInteresService;

    public RelacionTipoInteresResource(RelacionTipoInteresService relacionTipoInteresService) {
        this.relacionTipoInteresService = relacionTipoInteresService;
    }

    /**
     * {@code POST  /relacion-tipo-interes} : Create a new relacionTipoInteres.
     *
     * @param relacionTipoInteresDTO the relacionTipoInteresDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new relacionTipoInteresDTO, or with status {@code 400 (Bad Request)} if the relacionTipoInteres has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/relacion-tipo-interes")
    public ResponseEntity<RelacionTipoInteresDTO> createRelacionTipoInteres(@Valid @RequestBody RelacionTipoInteresDTO relacionTipoInteresDTO) throws URISyntaxException {
        log.debug("REST request to save RelacionTipoInteres : {}", relacionTipoInteresDTO);
        if (relacionTipoInteresDTO.getId() != null) {
            throw new BadRequestAlertException("A new relacionTipoInteres cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RelacionTipoInteresDTO result = relacionTipoInteresService.save(relacionTipoInteresDTO);
        return ResponseEntity.created(new URI("/api/relacion-tipo-interes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /relacion-tipo-interes} : Updates an existing relacionTipoInteres.
     *
     * @param relacionTipoInteresDTO the relacionTipoInteresDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated relacionTipoInteresDTO,
     * or with status {@code 400 (Bad Request)} if the relacionTipoInteresDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the relacionTipoInteresDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/relacion-tipo-interes")
    public ResponseEntity<RelacionTipoInteresDTO> updateRelacionTipoInteres(@Valid @RequestBody RelacionTipoInteresDTO relacionTipoInteresDTO) throws URISyntaxException {
        log.debug("REST request to update RelacionTipoInteres : {}", relacionTipoInteresDTO);
        if (relacionTipoInteresDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RelacionTipoInteresDTO result = relacionTipoInteresService.save(relacionTipoInteresDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, relacionTipoInteresDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /relacion-tipo-interes} : get all the relacionTipoInteres.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of relacionTipoInteres in body.
     */
    @GetMapping("/relacion-tipo-interes")
    public ResponseEntity<List<RelacionTipoInteresDTO>> getAllRelacionTipoInteres(Pageable pageable) {
        log.debug("REST request to get a page of RelacionTipoInteres");
        Page<RelacionTipoInteresDTO> page = relacionTipoInteresService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /relacion-tipo-interes/:id} : get the "id" relacionTipoInteres.
     *
     * @param id the id of the relacionTipoInteresDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the relacionTipoInteresDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/relacion-tipo-interes/{id}")
    public ResponseEntity<RelacionTipoInteresDTO> getRelacionTipoInteres(@PathVariable Long id) {
        log.debug("REST request to get RelacionTipoInteres : {}", id);
        Optional<RelacionTipoInteresDTO> relacionTipoInteresDTO = relacionTipoInteresService.findOne(id);
        return ResponseUtil.wrapOrNotFound(relacionTipoInteresDTO);
    }

    /**
     * {@code DELETE  /relacion-tipo-interes/:id} : delete the "id" relacionTipoInteres.
     *
     * @param id the id of the relacionTipoInteresDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/relacion-tipo-interes/{id}")
    public ResponseEntity<Void> deleteRelacionTipoInteres(@PathVariable Long id) {
        log.debug("REST request to delete RelacionTipoInteres : {}", id);
        relacionTipoInteresService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
