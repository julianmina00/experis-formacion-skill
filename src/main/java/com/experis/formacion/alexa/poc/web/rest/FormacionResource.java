package com.experis.formacion.alexa.poc.web.rest;

import com.experis.formacion.alexa.poc.service.FormacionesService;
import com.experis.formacion.alexa.poc.service.dto.CursoUsuarioDTO;
import com.experis.formacion.alexa.poc.service.dto.FormacionesSugeridasDTO;
import com.experis.formacion.alexa.poc.service.dto.RegistroFormacionDTO;
import com.experis.formacion.alexa.poc.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.annotation.MatchesPattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing {@link com.experis.formacion.alexa.poc.domain.Curso}.
 */
@RestController
@RequestMapping("/api")
public class FormacionResource {

    private final Logger log = LoggerFactory.getLogger(FormacionResource.class);

    private static final String ENTITY_NAME = "FormacionUsuario";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FormacionesService formacionesService;

    public FormacionResource(FormacionesService formacionesService) {
        this.formacionesService = formacionesService;
    }

    @GetMapping("/formaciones-sugeridas/{usuarioId}/{tipoFormacion}/{pagina}/{registrosPorPagina}")
    public ResponseEntity<List<FormacionesSugeridasDTO>> getFormacionesSugeridas(
        @PathVariable(name = "usuarioId") long usuarioId,
        @PathVariable(name = "tipoFormacion") @MatchesPattern(value = "[C|P]") String tipoFormacion,
        @PathVariable(name = "pagina") int pagina,
        @PathVariable(name = "registrosPorPagina", required = false) int registrosPorPagina) {
        log.debug("REST request to get Cursos or Planes Formativos suggested");
        List<FormacionesSugeridasDTO> formacionesSugeridas = formacionesService
            .getFormacionesSugeridas(usuarioId, tipoFormacion, pagina, registrosPorPagina);
        return ResponseEntity.ok().body(formacionesSugeridas);
    }

    @PostMapping("/formacion-usuarios")
    public ResponseEntity<RegistroFormacionDTO> createCursoUsuario(@RequestBody RegistroFormacionDTO dto) throws URISyntaxException {
        log.debug("REST request to save CursoUsuario : {}", dto);
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new cursoUsuario cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RegistroFormacionDTO result = formacionesService.registerFormacionUsuario(dto);
        return ResponseEntity.created(new URI("/api/curso-usuarios/" + result.getId()))
            .headers(HeaderUtil
                .createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

}
