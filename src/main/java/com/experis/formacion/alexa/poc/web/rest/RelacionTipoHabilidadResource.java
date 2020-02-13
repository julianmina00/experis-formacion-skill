package com.experis.formacion.alexa.poc.web.rest;

import com.experis.formacion.alexa.poc.service.RelacionTipoHabilidadService;
import com.experis.formacion.alexa.poc.web.rest.errors.BadRequestAlertException;
import com.experis.formacion.alexa.poc.service.dto.RelacionTipoHabilidadDTO;

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
 * REST controller for managing {@link com.experis.formacion.alexa.poc.domain.RelacionTipoHabilidad}.
 */
@RestController
@RequestMapping("/api")
public class RelacionTipoHabilidadResource {

    private final Logger log = LoggerFactory.getLogger(RelacionTipoHabilidadResource.class);

    private static final String ENTITY_NAME = "relacionTipoHabilidad";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RelacionTipoHabilidadService relacionTipoHabilidadService;

    public RelacionTipoHabilidadResource(RelacionTipoHabilidadService relacionTipoHabilidadService) {
        this.relacionTipoHabilidadService = relacionTipoHabilidadService;
    }

    /**
     * {@code POST  /relacion-tipo-habilidads} : Create a new relacionTipoHabilidad.
     *
     * @param relacionTipoHabilidadDTO the relacionTipoHabilidadDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new relacionTipoHabilidadDTO, or with status {@code 400 (Bad Request)} if the relacionTipoHabilidad has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/relacion-tipo-habilidads")
    public ResponseEntity<RelacionTipoHabilidadDTO> createRelacionTipoHabilidad(@Valid @RequestBody RelacionTipoHabilidadDTO relacionTipoHabilidadDTO) throws URISyntaxException {
        log.debug("REST request to save RelacionTipoHabilidad : {}", relacionTipoHabilidadDTO);
        if (relacionTipoHabilidadDTO.getId() != null) {
            throw new BadRequestAlertException("A new relacionTipoHabilidad cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RelacionTipoHabilidadDTO result = relacionTipoHabilidadService.save(relacionTipoHabilidadDTO);
        return ResponseEntity.created(new URI("/api/relacion-tipo-habilidads/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /relacion-tipo-habilidads} : Updates an existing relacionTipoHabilidad.
     *
     * @param relacionTipoHabilidadDTO the relacionTipoHabilidadDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated relacionTipoHabilidadDTO,
     * or with status {@code 400 (Bad Request)} if the relacionTipoHabilidadDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the relacionTipoHabilidadDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/relacion-tipo-habilidads")
    public ResponseEntity<RelacionTipoHabilidadDTO> updateRelacionTipoHabilidad(@Valid @RequestBody RelacionTipoHabilidadDTO relacionTipoHabilidadDTO) throws URISyntaxException {
        log.debug("REST request to update RelacionTipoHabilidad : {}", relacionTipoHabilidadDTO);
        if (relacionTipoHabilidadDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RelacionTipoHabilidadDTO result = relacionTipoHabilidadService.save(relacionTipoHabilidadDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, relacionTipoHabilidadDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /relacion-tipo-habilidads} : get all the relacionTipoHabilidads.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of relacionTipoHabilidads in body.
     */
    @GetMapping("/relacion-tipo-habilidads")
    public ResponseEntity<List<RelacionTipoHabilidadDTO>> getAllRelacionTipoHabilidads(Pageable pageable) {
        log.debug("REST request to get a page of RelacionTipoHabilidads");
        Page<RelacionTipoHabilidadDTO> page = relacionTipoHabilidadService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /relacion-tipo-habilidads/:id} : get the "id" relacionTipoHabilidad.
     *
     * @param id the id of the relacionTipoHabilidadDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the relacionTipoHabilidadDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/relacion-tipo-habilidads/{id}")
    public ResponseEntity<RelacionTipoHabilidadDTO> getRelacionTipoHabilidad(@PathVariable Long id) {
        log.debug("REST request to get RelacionTipoHabilidad : {}", id);
        Optional<RelacionTipoHabilidadDTO> relacionTipoHabilidadDTO = relacionTipoHabilidadService.findOne(id);
        return ResponseUtil.wrapOrNotFound(relacionTipoHabilidadDTO);
    }

    /**
     * {@code DELETE  /relacion-tipo-habilidads/:id} : delete the "id" relacionTipoHabilidad.
     *
     * @param id the id of the relacionTipoHabilidadDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/relacion-tipo-habilidads/{id}")
    public ResponseEntity<Void> deleteRelacionTipoHabilidad(@PathVariable Long id) {
        log.debug("REST request to delete RelacionTipoHabilidad : {}", id);
        relacionTipoHabilidadService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
