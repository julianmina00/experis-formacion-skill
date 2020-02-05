package com.experis.formacion.alexa.poc.web.rest;

import com.experis.formacion.alexa.poc.service.HabilidadUsuarioService;
import com.experis.formacion.alexa.poc.web.rest.errors.BadRequestAlertException;
import com.experis.formacion.alexa.poc.service.dto.HabilidadUsuarioDTO;

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
 * REST controller for managing {@link com.experis.formacion.alexa.poc.domain.HabilidadUsuario}.
 */
@RestController
@RequestMapping("/api")
public class HabilidadUsuarioResource {

    private final Logger log = LoggerFactory.getLogger(HabilidadUsuarioResource.class);

    private static final String ENTITY_NAME = "habilidadUsuario";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HabilidadUsuarioService habilidadUsuarioService;

    public HabilidadUsuarioResource(HabilidadUsuarioService habilidadUsuarioService) {
        this.habilidadUsuarioService = habilidadUsuarioService;
    }

    /**
     * {@code POST  /habilidad-usuarios} : Create a new habilidadUsuario.
     *
     * @param habilidadUsuarioDTO the habilidadUsuarioDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new habilidadUsuarioDTO, or with status {@code 400 (Bad Request)} if the habilidadUsuario has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/habilidad-usuarios")
    public ResponseEntity<HabilidadUsuarioDTO> createHabilidadUsuario(@RequestBody HabilidadUsuarioDTO habilidadUsuarioDTO) throws URISyntaxException {
        log.debug("REST request to save HabilidadUsuario : {}", habilidadUsuarioDTO);
        if (habilidadUsuarioDTO.getId() != null) {
            throw new BadRequestAlertException("A new habilidadUsuario cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HabilidadUsuarioDTO result = habilidadUsuarioService.save(habilidadUsuarioDTO);
        return ResponseEntity.created(new URI("/api/habilidad-usuarios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /habilidad-usuarios} : Updates an existing habilidadUsuario.
     *
     * @param habilidadUsuarioDTO the habilidadUsuarioDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated habilidadUsuarioDTO,
     * or with status {@code 400 (Bad Request)} if the habilidadUsuarioDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the habilidadUsuarioDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/habilidad-usuarios")
    public ResponseEntity<HabilidadUsuarioDTO> updateHabilidadUsuario(@RequestBody HabilidadUsuarioDTO habilidadUsuarioDTO) throws URISyntaxException {
        log.debug("REST request to update HabilidadUsuario : {}", habilidadUsuarioDTO);
        if (habilidadUsuarioDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HabilidadUsuarioDTO result = habilidadUsuarioService.save(habilidadUsuarioDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, habilidadUsuarioDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /habilidad-usuarios} : get all the habilidadUsuarios.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of habilidadUsuarios in body.
     */
    @GetMapping("/habilidad-usuarios")
    public List<HabilidadUsuarioDTO> getAllHabilidadUsuarios() {
        log.debug("REST request to get all HabilidadUsuarios");
        return habilidadUsuarioService.findAll();
    }

    /**
     * {@code GET  /habilidad-usuarios/:id} : get the "id" habilidadUsuario.
     *
     * @param id the id of the habilidadUsuarioDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the habilidadUsuarioDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/habilidad-usuarios/{id}")
    public ResponseEntity<HabilidadUsuarioDTO> getHabilidadUsuario(@PathVariable Long id) {
        log.debug("REST request to get HabilidadUsuario : {}", id);
        Optional<HabilidadUsuarioDTO> habilidadUsuarioDTO = habilidadUsuarioService.findOne(id);
        return ResponseUtil.wrapOrNotFound(habilidadUsuarioDTO);
    }

    /**
     * {@code DELETE  /habilidad-usuarios/:id} : delete the "id" habilidadUsuario.
     *
     * @param id the id of the habilidadUsuarioDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/habilidad-usuarios/{id}")
    public ResponseEntity<Void> deleteHabilidadUsuario(@PathVariable Long id) {
        log.debug("REST request to delete HabilidadUsuario : {}", id);
        habilidadUsuarioService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
