package com.experis.formacion.alexa.poc.web.rest;

import com.experis.formacion.alexa.poc.service.TipoInteresService;
import com.experis.formacion.alexa.poc.web.rest.errors.BadRequestAlertException;
import com.experis.formacion.alexa.poc.service.dto.TipoInteresDTO;

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
 * REST controller for managing {@link com.experis.formacion.alexa.poc.domain.TipoInteres}.
 */
@RestController
@RequestMapping("/api")
public class TipoInteresResource {

    private final Logger log = LoggerFactory.getLogger(TipoInteresResource.class);

    private static final String ENTITY_NAME = "tipoInteres";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TipoInteresService tipoInteresService;

    public TipoInteresResource(TipoInteresService tipoInteresService) {
        this.tipoInteresService = tipoInteresService;
    }

    /**
     * {@code POST  /tipo-interes} : Create a new tipoInteres.
     *
     * @param tipoInteresDTO the tipoInteresDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tipoInteresDTO, or with status {@code 400 (Bad Request)} if the tipoInteres has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tipo-interes")
    public ResponseEntity<TipoInteresDTO> createTipoInteres(@Valid @RequestBody TipoInteresDTO tipoInteresDTO) throws URISyntaxException {
        log.debug("REST request to save TipoInteres : {}", tipoInteresDTO);
        if (tipoInteresDTO.getId() != null) {
            throw new BadRequestAlertException("A new tipoInteres cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoInteresDTO result = tipoInteresService.save(tipoInteresDTO);
        return ResponseEntity.created(new URI("/api/tipo-interes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tipo-interes} : Updates an existing tipoInteres.
     *
     * @param tipoInteresDTO the tipoInteresDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tipoInteresDTO,
     * or with status {@code 400 (Bad Request)} if the tipoInteresDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tipoInteresDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tipo-interes")
    public ResponseEntity<TipoInteresDTO> updateTipoInteres(@Valid @RequestBody TipoInteresDTO tipoInteresDTO) throws URISyntaxException {
        log.debug("REST request to update TipoInteres : {}", tipoInteresDTO);
        if (tipoInteresDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoInteresDTO result = tipoInteresService.save(tipoInteresDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tipoInteresDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tipo-interes} : get all the tipoInteres.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tipoInteres in body.
     */
    @GetMapping("/tipo-interes")
    public ResponseEntity<List<TipoInteresDTO>> getAllTipoInteres(Pageable pageable) {
        log.debug("REST request to get a page of TipoInteres");
        Page<TipoInteresDTO> page = tipoInteresService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /tipo-interes/:id} : get the "id" tipoInteres.
     *
     * @param id the id of the tipoInteresDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tipoInteresDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tipo-interes/{id}")
    public ResponseEntity<TipoInteresDTO> getTipoInteres(@PathVariable Long id) {
        log.debug("REST request to get TipoInteres : {}", id);
        Optional<TipoInteresDTO> tipoInteresDTO = tipoInteresService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tipoInteresDTO);
    }

    /**
     * {@code DELETE  /tipo-interes/:id} : delete the "id" tipoInteres.
     *
     * @param id the id of the tipoInteresDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tipo-interes/{id}")
    public ResponseEntity<Void> deleteTipoInteres(@PathVariable Long id) {
        log.debug("REST request to delete TipoInteres : {}", id);
        tipoInteresService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
