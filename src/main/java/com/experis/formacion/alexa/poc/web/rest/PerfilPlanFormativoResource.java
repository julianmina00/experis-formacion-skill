package com.experis.formacion.alexa.poc.web.rest;

import com.experis.formacion.alexa.poc.service.PerfilPlanFormativoService;
import com.experis.formacion.alexa.poc.web.rest.errors.BadRequestAlertException;
import com.experis.formacion.alexa.poc.service.dto.PerfilPlanFormativoDTO;

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
 * REST controller for managing {@link com.experis.formacion.alexa.poc.domain.PerfilPlanFormativo}.
 */
@RestController
@RequestMapping("/api")
public class PerfilPlanFormativoResource {

    private final Logger log = LoggerFactory.getLogger(PerfilPlanFormativoResource.class);

    private static final String ENTITY_NAME = "perfilPlanFormativo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PerfilPlanFormativoService perfilPlanFormativoService;

    public PerfilPlanFormativoResource(PerfilPlanFormativoService perfilPlanFormativoService) {
        this.perfilPlanFormativoService = perfilPlanFormativoService;
    }

    /**
     * {@code POST  /perfil-plan-formativos} : Create a new perfilPlanFormativo.
     *
     * @param perfilPlanFormativoDTO the perfilPlanFormativoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new perfilPlanFormativoDTO, or with status {@code 400 (Bad Request)} if the perfilPlanFormativo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/perfil-plan-formativos")
    public ResponseEntity<PerfilPlanFormativoDTO> createPerfilPlanFormativo(@RequestBody PerfilPlanFormativoDTO perfilPlanFormativoDTO) throws URISyntaxException {
        log.debug("REST request to save PerfilPlanFormativo : {}", perfilPlanFormativoDTO);
        if (perfilPlanFormativoDTO.getId() != null) {
            throw new BadRequestAlertException("A new perfilPlanFormativo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PerfilPlanFormativoDTO result = perfilPlanFormativoService.save(perfilPlanFormativoDTO);
        return ResponseEntity.created(new URI("/api/perfil-plan-formativos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /perfil-plan-formativos} : Updates an existing perfilPlanFormativo.
     *
     * @param perfilPlanFormativoDTO the perfilPlanFormativoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated perfilPlanFormativoDTO,
     * or with status {@code 400 (Bad Request)} if the perfilPlanFormativoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the perfilPlanFormativoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/perfil-plan-formativos")
    public ResponseEntity<PerfilPlanFormativoDTO> updatePerfilPlanFormativo(@RequestBody PerfilPlanFormativoDTO perfilPlanFormativoDTO) throws URISyntaxException {
        log.debug("REST request to update PerfilPlanFormativo : {}", perfilPlanFormativoDTO);
        if (perfilPlanFormativoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PerfilPlanFormativoDTO result = perfilPlanFormativoService.save(perfilPlanFormativoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, perfilPlanFormativoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /perfil-plan-formativos} : get all the perfilPlanFormativos.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of perfilPlanFormativos in body.
     */
    @GetMapping("/perfil-plan-formativos")
    public List<PerfilPlanFormativoDTO> getAllPerfilPlanFormativos() {
        log.debug("REST request to get all PerfilPlanFormativos");
        return perfilPlanFormativoService.findAll();
    }

    /**
     * {@code GET  /perfil-plan-formativos/:id} : get the "id" perfilPlanFormativo.
     *
     * @param id the id of the perfilPlanFormativoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the perfilPlanFormativoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/perfil-plan-formativos/{id}")
    public ResponseEntity<PerfilPlanFormativoDTO> getPerfilPlanFormativo(@PathVariable Long id) {
        log.debug("REST request to get PerfilPlanFormativo : {}", id);
        Optional<PerfilPlanFormativoDTO> perfilPlanFormativoDTO = perfilPlanFormativoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(perfilPlanFormativoDTO);
    }

    /**
     * {@code DELETE  /perfil-plan-formativos/:id} : delete the "id" perfilPlanFormativo.
     *
     * @param id the id of the perfilPlanFormativoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/perfil-plan-formativos/{id}")
    public ResponseEntity<Void> deletePerfilPlanFormativo(@PathVariable Long id) {
        log.debug("REST request to delete PerfilPlanFormativo : {}", id);
        perfilPlanFormativoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
