package com.experis.formacion.alexa.poc.web.rest;

import com.experis.formacion.alexa.poc.service.PlanFormativoService;
import com.experis.formacion.alexa.poc.web.rest.errors.BadRequestAlertException;
import com.experis.formacion.alexa.poc.service.dto.PlanFormativoDTO;

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
 * REST controller for managing {@link com.experis.formacion.alexa.poc.domain.PlanFormativo}.
 */
@RestController
@RequestMapping("/api")
public class PlanFormativoResource {

    private final Logger log = LoggerFactory.getLogger(PlanFormativoResource.class);

    private static final String ENTITY_NAME = "planFormativo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PlanFormativoService planFormativoService;

    public PlanFormativoResource(PlanFormativoService planFormativoService) {
        this.planFormativoService = planFormativoService;
    }

    /**
     * {@code POST  /plan-formativos} : Create a new planFormativo.
     *
     * @param planFormativoDTO the planFormativoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new planFormativoDTO, or with status {@code 400 (Bad Request)} if the planFormativo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/plan-formativos")
    public ResponseEntity<PlanFormativoDTO> createPlanFormativo(@Valid @RequestBody PlanFormativoDTO planFormativoDTO) throws URISyntaxException {
        log.debug("REST request to save PlanFormativo : {}", planFormativoDTO);
        if (planFormativoDTO.getId() != null) {
            throw new BadRequestAlertException("A new planFormativo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlanFormativoDTO result = planFormativoService.save(planFormativoDTO);
        return ResponseEntity.created(new URI("/api/plan-formativos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /plan-formativos} : Updates an existing planFormativo.
     *
     * @param planFormativoDTO the planFormativoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated planFormativoDTO,
     * or with status {@code 400 (Bad Request)} if the planFormativoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the planFormativoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/plan-formativos")
    public ResponseEntity<PlanFormativoDTO> updatePlanFormativo(@Valid @RequestBody PlanFormativoDTO planFormativoDTO) throws URISyntaxException {
        log.debug("REST request to update PlanFormativo : {}", planFormativoDTO);
        if (planFormativoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlanFormativoDTO result = planFormativoService.save(planFormativoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, planFormativoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /plan-formativos} : get all the planFormativos.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of planFormativos in body.
     */
    @GetMapping("/plan-formativos")
    public ResponseEntity<List<PlanFormativoDTO>> getAllPlanFormativos(Pageable pageable) {
        log.debug("REST request to get a page of PlanFormativos");
        Page<PlanFormativoDTO> page = planFormativoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /plan-formativos/:id} : get the "id" planFormativo.
     *
     * @param id the id of the planFormativoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the planFormativoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/plan-formativos/{id}")
    public ResponseEntity<PlanFormativoDTO> getPlanFormativo(@PathVariable Long id) {
        log.debug("REST request to get PlanFormativo : {}", id);
        Optional<PlanFormativoDTO> planFormativoDTO = planFormativoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(planFormativoDTO);
    }

    /**
     * {@code DELETE  /plan-formativos/:id} : delete the "id" planFormativo.
     *
     * @param id the id of the planFormativoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/plan-formativos/{id}")
    public ResponseEntity<Void> deletePlanFormativo(@PathVariable Long id) {
        log.debug("REST request to delete PlanFormativo : {}", id);
        planFormativoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
