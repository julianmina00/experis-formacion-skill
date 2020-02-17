package com.experis.formacion.alexa.poc.web.rest;

import com.experis.formacion.alexa.poc.service.InteresUsuarioService;
import com.experis.formacion.alexa.poc.service.dto.RegistroInteresDTO;
import com.experis.formacion.alexa.poc.web.rest.errors.BadRequestAlertException;
import com.experis.formacion.alexa.poc.service.dto.InteresUsuarioDTO;

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
 * REST controller for managing {@link com.experis.formacion.alexa.poc.domain.InteresUsuario}.
 */
@RestController
@RequestMapping("/api")
public class InteresUsuarioResource {

    private final Logger log = LoggerFactory.getLogger(InteresUsuarioResource.class);

    private static final String ENTITY_NAME = "interesUsuario";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InteresUsuarioService interesUsuarioService;

    public InteresUsuarioResource(InteresUsuarioService interesUsuarioService) {
        this.interesUsuarioService = interesUsuarioService;
    }

    @PostMapping("/registrar-interes")
    public ResponseEntity<InteresUsuarioDTO> registrarHabilidadUsuario(@RequestBody RegistroInteresDTO interesUsuarioDTO) throws URISyntaxException {
        log.debug("REST request to save InteresUsuario : {}", interesUsuarioDTO);
        List<InteresUsuarioDTO> result = interesUsuarioService.register(interesUsuarioDTO);
        if(result.isEmpty()){
            throw new RuntimeException("el usuario ya tenía registrado el interés "+interesUsuarioDTO.getInteres());
        }
        InteresUsuarioDTO habilidadUsuario = result.get(0);
        return ResponseEntity.created(new URI("/api/habilidad-usuarios/"+habilidadUsuario.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, interesUsuarioDTO.getInteres()))
            .body(habilidadUsuario);
    }

    /**
     * {@code POST  /interes-usuarios} : Create a new interesUsuario.
     *
     * @param interesUsuarioDTO the interesUsuarioDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new interesUsuarioDTO, or with status {@code 400 (Bad Request)} if the interesUsuario has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/interes-usuarios")
    public ResponseEntity<InteresUsuarioDTO> createInteresUsuario(@RequestBody InteresUsuarioDTO interesUsuarioDTO) throws URISyntaxException {
        log.debug("REST request to save InteresUsuario : {}", interesUsuarioDTO);
        if (interesUsuarioDTO.getId() != null) {
            throw new BadRequestAlertException("A new interesUsuario cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InteresUsuarioDTO result = interesUsuarioService.save(interesUsuarioDTO);
        return ResponseEntity.created(new URI("/api/interes-usuarios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /interes-usuarios} : Updates an existing interesUsuario.
     *
     * @param interesUsuarioDTO the interesUsuarioDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated interesUsuarioDTO,
     * or with status {@code 400 (Bad Request)} if the interesUsuarioDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the interesUsuarioDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/interes-usuarios")
    public ResponseEntity<InteresUsuarioDTO> updateInteresUsuario(@RequestBody InteresUsuarioDTO interesUsuarioDTO) throws URISyntaxException {
        log.debug("REST request to update InteresUsuario : {}", interesUsuarioDTO);
        if (interesUsuarioDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InteresUsuarioDTO result = interesUsuarioService.save(interesUsuarioDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, interesUsuarioDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /interes-usuarios} : get all the interesUsuarios.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of interesUsuarios in body.
     */
    @GetMapping("/interes-usuarios")
    public List<InteresUsuarioDTO> getAllInteresUsuarios() {
        log.debug("REST request to get all InteresUsuarios");
        return interesUsuarioService.findAll();
    }

    /**
     * {@code GET  /interes-usuarios/:id} : get the "id" interesUsuario.
     *
     * @param id the id of the interesUsuarioDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the interesUsuarioDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/interes-usuarios/{id}")
    public ResponseEntity<InteresUsuarioDTO> getInteresUsuario(@PathVariable Long id) {
        log.debug("REST request to get InteresUsuario : {}", id);
        Optional<InteresUsuarioDTO> interesUsuarioDTO = interesUsuarioService.findOne(id);
        return ResponseUtil.wrapOrNotFound(interesUsuarioDTO);
    }

    /**
     * {@code DELETE  /interes-usuarios/:id} : delete the "id" interesUsuario.
     *
     * @param id the id of the interesUsuarioDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/interes-usuarios/{id}")
    public ResponseEntity<Void> deleteInteresUsuario(@PathVariable Long id) {
        log.debug("REST request to delete InteresUsuario : {}", id);
        interesUsuarioService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
