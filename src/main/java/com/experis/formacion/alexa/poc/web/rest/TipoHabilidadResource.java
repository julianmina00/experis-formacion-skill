package com.experis.formacion.alexa.poc.web.rest;

import com.experis.formacion.alexa.poc.service.TipoHabilidadService;
import com.experis.formacion.alexa.poc.web.rest.errors.BadRequestAlertException;
import com.experis.formacion.alexa.poc.service.dto.TipoHabilidadDTO;

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
 * REST controller for managing {@link com.experis.formacion.alexa.poc.domain.TipoHabilidad}.
 */
@RestController
@RequestMapping("/api")
public class TipoHabilidadResource {

    private final Logger log = LoggerFactory.getLogger(TipoHabilidadResource.class);

    private static final String ENTITY_NAME = "tipoHabilidad";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TipoHabilidadService tipoHabilidadService;

    public TipoHabilidadResource(TipoHabilidadService tipoHabilidadService) {
        this.tipoHabilidadService = tipoHabilidadService;
    }

    /**
     * {@code POST  /tipo-habilidads} : Create a new tipoHabilidad.
     *
     * @param tipoHabilidadDTO the tipoHabilidadDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tipoHabilidadDTO, or with status {@code 400 (Bad Request)} if the tipoHabilidad has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tipo-habilidads")
    public ResponseEntity<TipoHabilidadDTO> createTipoHabilidad(@Valid @RequestBody TipoHabilidadDTO tipoHabilidadDTO) throws URISyntaxException {
        log.debug("REST request to save TipoHabilidad : {}", tipoHabilidadDTO);
        if (tipoHabilidadDTO.getId() != null) {
            throw new BadRequestAlertException("A new tipoHabilidad cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoHabilidadDTO result = tipoHabilidadService.save(tipoHabilidadDTO);
        return ResponseEntity.created(new URI("/api/tipo-habilidads/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tipo-habilidads} : Updates an existing tipoHabilidad.
     *
     * @param tipoHabilidadDTO the tipoHabilidadDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tipoHabilidadDTO,
     * or with status {@code 400 (Bad Request)} if the tipoHabilidadDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tipoHabilidadDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tipo-habilidads")
    public ResponseEntity<TipoHabilidadDTO> updateTipoHabilidad(@Valid @RequestBody TipoHabilidadDTO tipoHabilidadDTO) throws URISyntaxException {
        log.debug("REST request to update TipoHabilidad : {}", tipoHabilidadDTO);
        if (tipoHabilidadDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoHabilidadDTO result = tipoHabilidadService.save(tipoHabilidadDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tipoHabilidadDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tipo-habilidads} : get all the tipoHabilidads.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tipoHabilidads in body.
     */
    @GetMapping("/tipo-habilidads")
    public ResponseEntity<List<TipoHabilidadDTO>> getAllTipoHabilidads(Pageable pageable) {
        log.debug("REST request to get a page of TipoHabilidads");
        Page<TipoHabilidadDTO> page = tipoHabilidadService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /tipo-habilidads/:id} : get the "id" tipoHabilidad.
     *
     * @param id the id of the tipoHabilidadDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tipoHabilidadDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tipo-habilidads/{id}")
    public ResponseEntity<TipoHabilidadDTO> getTipoHabilidad(@PathVariable Long id) {
        log.debug("REST request to get TipoHabilidad : {}", id);
        Optional<TipoHabilidadDTO> tipoHabilidadDTO = tipoHabilidadService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tipoHabilidadDTO);
    }

    /**
     * {@code DELETE  /tipo-habilidads/:id} : delete the "id" tipoHabilidad.
     *
     * @param id the id of the tipoHabilidadDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tipo-habilidads/{id}")
    public ResponseEntity<Void> deleteTipoHabilidad(@PathVariable Long id) {
        log.debug("REST request to delete TipoHabilidad : {}", id);
        tipoHabilidadService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
