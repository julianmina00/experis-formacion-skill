package com.experis.formacion.alexa.poc.web.rest;

import com.experis.formacion.alexa.poc.service.IdiomaUsuarioService;
import com.experis.formacion.alexa.poc.web.rest.errors.BadRequestAlertException;
import com.experis.formacion.alexa.poc.service.dto.IdiomaUsuarioDTO;

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
 * REST controller for managing {@link com.experis.formacion.alexa.poc.domain.IdiomaUsuario}.
 */
@RestController
@RequestMapping("/api")
public class IdiomaUsuarioResource {

    private final Logger log = LoggerFactory.getLogger(IdiomaUsuarioResource.class);

    private static final String ENTITY_NAME = "idiomaUsuario";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final IdiomaUsuarioService idiomaUsuarioService;

    public IdiomaUsuarioResource(IdiomaUsuarioService idiomaUsuarioService) {
        this.idiomaUsuarioService = idiomaUsuarioService;
    }

    /**
     * {@code POST  /idioma-usuarios} : Create a new idiomaUsuario.
     *
     * @param idiomaUsuarioDTO the idiomaUsuarioDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new idiomaUsuarioDTO, or with status {@code 400 (Bad Request)} if the idiomaUsuario has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/idioma-usuarios")
    public ResponseEntity<IdiomaUsuarioDTO> createIdiomaUsuario(@Valid @RequestBody IdiomaUsuarioDTO idiomaUsuarioDTO) throws URISyntaxException {
        log.debug("REST request to save IdiomaUsuario : {}", idiomaUsuarioDTO);
        if (idiomaUsuarioDTO.getId() != null) {
            throw new BadRequestAlertException("A new idiomaUsuario cannot already have an ID", ENTITY_NAME, "idexists");
        }
        IdiomaUsuarioDTO result = idiomaUsuarioService.save(idiomaUsuarioDTO);
        return ResponseEntity.created(new URI("/api/idioma-usuarios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /idioma-usuarios} : Updates an existing idiomaUsuario.
     *
     * @param idiomaUsuarioDTO the idiomaUsuarioDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated idiomaUsuarioDTO,
     * or with status {@code 400 (Bad Request)} if the idiomaUsuarioDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the idiomaUsuarioDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/idioma-usuarios")
    public ResponseEntity<IdiomaUsuarioDTO> updateIdiomaUsuario(@Valid @RequestBody IdiomaUsuarioDTO idiomaUsuarioDTO) throws URISyntaxException {
        log.debug("REST request to update IdiomaUsuario : {}", idiomaUsuarioDTO);
        if (idiomaUsuarioDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        IdiomaUsuarioDTO result = idiomaUsuarioService.save(idiomaUsuarioDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, idiomaUsuarioDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /idioma-usuarios} : get all the idiomaUsuarios.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of idiomaUsuarios in body.
     */
    @GetMapping("/idioma-usuarios")
    public ResponseEntity<List<IdiomaUsuarioDTO>> getAllIdiomaUsuarios(Pageable pageable) {
        log.debug("REST request to get a page of IdiomaUsuarios");
        Page<IdiomaUsuarioDTO> page = idiomaUsuarioService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /idioma-usuarios/:id} : get the "id" idiomaUsuario.
     *
     * @param id the id of the idiomaUsuarioDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the idiomaUsuarioDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/idioma-usuarios/{id}")
    public ResponseEntity<IdiomaUsuarioDTO> getIdiomaUsuario(@PathVariable Long id) {
        log.debug("REST request to get IdiomaUsuario : {}", id);
        Optional<IdiomaUsuarioDTO> idiomaUsuarioDTO = idiomaUsuarioService.findOne(id);
        return ResponseUtil.wrapOrNotFound(idiomaUsuarioDTO);
    }

    /**
     * {@code DELETE  /idioma-usuarios/:id} : delete the "id" idiomaUsuario.
     *
     * @param id the id of the idiomaUsuarioDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/idioma-usuarios/{id}")
    public ResponseEntity<Void> deleteIdiomaUsuario(@PathVariable Long id) {
        log.debug("REST request to delete IdiomaUsuario : {}", id);
        idiomaUsuarioService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
