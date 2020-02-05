package com.experis.formacion.alexa.poc.web.rest;

import com.experis.formacion.alexa.poc.service.PlanFormativoUsuarioService;
import com.experis.formacion.alexa.poc.web.rest.errors.BadRequestAlertException;
import com.experis.formacion.alexa.poc.service.dto.PlanFormativoUsuarioDTO;

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
 * REST controller for managing {@link com.experis.formacion.alexa.poc.domain.PlanFormativoUsuario}.
 */
@RestController
@RequestMapping("/api")
public class PlanFormativoUsuarioResource {

    private final Logger log = LoggerFactory.getLogger(PlanFormativoUsuarioResource.class);

    private static final String ENTITY_NAME = "planFormativoUsuario";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PlanFormativoUsuarioService planFormativoUsuarioService;

    public PlanFormativoUsuarioResource(PlanFormativoUsuarioService planFormativoUsuarioService) {
        this.planFormativoUsuarioService = planFormativoUsuarioService;
    }

    /**
     * {@code POST  /plan-formativo-usuarios} : Create a new planFormativoUsuario.
     *
     * @param planFormativoUsuarioDTO the planFormativoUsuarioDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new planFormativoUsuarioDTO, or with status {@code 400 (Bad Request)} if the planFormativoUsuario has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/plan-formativo-usuarios")
    public ResponseEntity<PlanFormativoUsuarioDTO> createPlanFormativoUsuario(@RequestBody PlanFormativoUsuarioDTO planFormativoUsuarioDTO) throws URISyntaxException {
        log.debug("REST request to save PlanFormativoUsuario : {}", planFormativoUsuarioDTO);
        if (planFormativoUsuarioDTO.getId() != null) {
            throw new BadRequestAlertException("A new planFormativoUsuario cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlanFormativoUsuarioDTO result = planFormativoUsuarioService.save(planFormativoUsuarioDTO);
        return ResponseEntity.created(new URI("/api/plan-formativo-usuarios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /plan-formativo-usuarios} : Updates an existing planFormativoUsuario.
     *
     * @param planFormativoUsuarioDTO the planFormativoUsuarioDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated planFormativoUsuarioDTO,
     * or with status {@code 400 (Bad Request)} if the planFormativoUsuarioDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the planFormativoUsuarioDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/plan-formativo-usuarios")
    public ResponseEntity<PlanFormativoUsuarioDTO> updatePlanFormativoUsuario(@RequestBody PlanFormativoUsuarioDTO planFormativoUsuarioDTO) throws URISyntaxException {
        log.debug("REST request to update PlanFormativoUsuario : {}", planFormativoUsuarioDTO);
        if (planFormativoUsuarioDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlanFormativoUsuarioDTO result = planFormativoUsuarioService.save(planFormativoUsuarioDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, planFormativoUsuarioDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /plan-formativo-usuarios} : get all the planFormativoUsuarios.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of planFormativoUsuarios in body.
     */
    @GetMapping("/plan-formativo-usuarios")
    public List<PlanFormativoUsuarioDTO> getAllPlanFormativoUsuarios() {
        log.debug("REST request to get all PlanFormativoUsuarios");
        return planFormativoUsuarioService.findAll();
    }

    /**
     * {@code GET  /plan-formativo-usuarios/:id} : get the "id" planFormativoUsuario.
     *
     * @param id the id of the planFormativoUsuarioDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the planFormativoUsuarioDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/plan-formativo-usuarios/{id}")
    public ResponseEntity<PlanFormativoUsuarioDTO> getPlanFormativoUsuario(@PathVariable Long id) {
        log.debug("REST request to get PlanFormativoUsuario : {}", id);
        Optional<PlanFormativoUsuarioDTO> planFormativoUsuarioDTO = planFormativoUsuarioService.findOne(id);
        return ResponseUtil.wrapOrNotFound(planFormativoUsuarioDTO);
    }

    /**
     * {@code DELETE  /plan-formativo-usuarios/:id} : delete the "id" planFormativoUsuario.
     *
     * @param id the id of the planFormativoUsuarioDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/plan-formativo-usuarios/{id}")
    public ResponseEntity<Void> deletePlanFormativoUsuario(@PathVariable Long id) {
        log.debug("REST request to delete PlanFormativoUsuario : {}", id);
        planFormativoUsuarioService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
