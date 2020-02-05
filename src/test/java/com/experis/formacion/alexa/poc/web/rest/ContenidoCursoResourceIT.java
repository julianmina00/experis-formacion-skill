package com.experis.formacion.alexa.poc.web.rest;

import com.experis.formacion.alexa.poc.ExperisFormacionApp;
import com.experis.formacion.alexa.poc.domain.ContenidoCurso;
import com.experis.formacion.alexa.poc.repository.ContenidoCursoRepository;
import com.experis.formacion.alexa.poc.service.ContenidoCursoService;
import com.experis.formacion.alexa.poc.service.dto.ContenidoCursoDTO;
import com.experis.formacion.alexa.poc.service.mapper.ContenidoCursoMapper;
import com.experis.formacion.alexa.poc.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.experis.formacion.alexa.poc.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ContenidoCursoResource} REST controller.
 */
@SpringBootTest(classes = ExperisFormacionApp.class)
public class ContenidoCursoResourceIT {

    private static final String DEFAULT_HABILIDAD_INTERES = "I";
    private static final String UPDATED_HABILIDAD_INTERES = "H";

    @Autowired
    private ContenidoCursoRepository contenidoCursoRepository;

    @Autowired
    private ContenidoCursoMapper contenidoCursoMapper;

    @Autowired
    private ContenidoCursoService contenidoCursoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restContenidoCursoMockMvc;

    private ContenidoCurso contenidoCurso;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ContenidoCursoResource contenidoCursoResource = new ContenidoCursoResource(contenidoCursoService);
        this.restContenidoCursoMockMvc = MockMvcBuilders.standaloneSetup(contenidoCursoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContenidoCurso createEntity(EntityManager em) {
        ContenidoCurso contenidoCurso = new ContenidoCurso()
            .habilidadInteres(DEFAULT_HABILIDAD_INTERES);
        return contenidoCurso;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContenidoCurso createUpdatedEntity(EntityManager em) {
        ContenidoCurso contenidoCurso = new ContenidoCurso()
            .habilidadInteres(UPDATED_HABILIDAD_INTERES);
        return contenidoCurso;
    }

    @BeforeEach
    public void initTest() {
        contenidoCurso = createEntity(em);
    }

    @Test
    @Transactional
    public void createContenidoCurso() throws Exception {
        int databaseSizeBeforeCreate = contenidoCursoRepository.findAll().size();

        // Create the ContenidoCurso
        ContenidoCursoDTO contenidoCursoDTO = contenidoCursoMapper.toDto(contenidoCurso);
        restContenidoCursoMockMvc.perform(post("/api/contenido-cursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contenidoCursoDTO)))
            .andExpect(status().isCreated());

        // Validate the ContenidoCurso in the database
        List<ContenidoCurso> contenidoCursoList = contenidoCursoRepository.findAll();
        assertThat(contenidoCursoList).hasSize(databaseSizeBeforeCreate + 1);
        ContenidoCurso testContenidoCurso = contenidoCursoList.get(contenidoCursoList.size() - 1);
        assertThat(testContenidoCurso.getHabilidadInteres()).isEqualTo(DEFAULT_HABILIDAD_INTERES);
    }

    @Test
    @Transactional
    public void createContenidoCursoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = contenidoCursoRepository.findAll().size();

        // Create the ContenidoCurso with an existing ID
        contenidoCurso.setId(1L);
        ContenidoCursoDTO contenidoCursoDTO = contenidoCursoMapper.toDto(contenidoCurso);

        // An entity with an existing ID cannot be created, so this API call must fail
        restContenidoCursoMockMvc.perform(post("/api/contenido-cursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contenidoCursoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ContenidoCurso in the database
        List<ContenidoCurso> contenidoCursoList = contenidoCursoRepository.findAll();
        assertThat(contenidoCursoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkHabilidadInteresIsRequired() throws Exception {
        int databaseSizeBeforeTest = contenidoCursoRepository.findAll().size();
        // set the field null
        contenidoCurso.setHabilidadInteres(null);

        // Create the ContenidoCurso, which fails.
        ContenidoCursoDTO contenidoCursoDTO = contenidoCursoMapper.toDto(contenidoCurso);

        restContenidoCursoMockMvc.perform(post("/api/contenido-cursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contenidoCursoDTO)))
            .andExpect(status().isBadRequest());

        List<ContenidoCurso> contenidoCursoList = contenidoCursoRepository.findAll();
        assertThat(contenidoCursoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllContenidoCursos() throws Exception {
        // Initialize the database
        contenidoCursoRepository.saveAndFlush(contenidoCurso);

        // Get all the contenidoCursoList
        restContenidoCursoMockMvc.perform(get("/api/contenido-cursos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contenidoCurso.getId().intValue())))
            .andExpect(jsonPath("$.[*].habilidadInteres").value(hasItem(DEFAULT_HABILIDAD_INTERES)));
    }
    
    @Test
    @Transactional
    public void getContenidoCurso() throws Exception {
        // Initialize the database
        contenidoCursoRepository.saveAndFlush(contenidoCurso);

        // Get the contenidoCurso
        restContenidoCursoMockMvc.perform(get("/api/contenido-cursos/{id}", contenidoCurso.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(contenidoCurso.getId().intValue()))
            .andExpect(jsonPath("$.habilidadInteres").value(DEFAULT_HABILIDAD_INTERES));
    }

    @Test
    @Transactional
    public void getNonExistingContenidoCurso() throws Exception {
        // Get the contenidoCurso
        restContenidoCursoMockMvc.perform(get("/api/contenido-cursos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateContenidoCurso() throws Exception {
        // Initialize the database
        contenidoCursoRepository.saveAndFlush(contenidoCurso);

        int databaseSizeBeforeUpdate = contenidoCursoRepository.findAll().size();

        // Update the contenidoCurso
        ContenidoCurso updatedContenidoCurso = contenidoCursoRepository.findById(contenidoCurso.getId()).get();
        // Disconnect from session so that the updates on updatedContenidoCurso are not directly saved in db
        em.detach(updatedContenidoCurso);
        updatedContenidoCurso
            .habilidadInteres(UPDATED_HABILIDAD_INTERES);
        ContenidoCursoDTO contenidoCursoDTO = contenidoCursoMapper.toDto(updatedContenidoCurso);

        restContenidoCursoMockMvc.perform(put("/api/contenido-cursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contenidoCursoDTO)))
            .andExpect(status().isOk());

        // Validate the ContenidoCurso in the database
        List<ContenidoCurso> contenidoCursoList = contenidoCursoRepository.findAll();
        assertThat(contenidoCursoList).hasSize(databaseSizeBeforeUpdate);
        ContenidoCurso testContenidoCurso = contenidoCursoList.get(contenidoCursoList.size() - 1);
        assertThat(testContenidoCurso.getHabilidadInteres()).isEqualTo(UPDATED_HABILIDAD_INTERES);
    }

    @Test
    @Transactional
    public void updateNonExistingContenidoCurso() throws Exception {
        int databaseSizeBeforeUpdate = contenidoCursoRepository.findAll().size();

        // Create the ContenidoCurso
        ContenidoCursoDTO contenidoCursoDTO = contenidoCursoMapper.toDto(contenidoCurso);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContenidoCursoMockMvc.perform(put("/api/contenido-cursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contenidoCursoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ContenidoCurso in the database
        List<ContenidoCurso> contenidoCursoList = contenidoCursoRepository.findAll();
        assertThat(contenidoCursoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteContenidoCurso() throws Exception {
        // Initialize the database
        contenidoCursoRepository.saveAndFlush(contenidoCurso);

        int databaseSizeBeforeDelete = contenidoCursoRepository.findAll().size();

        // Delete the contenidoCurso
        restContenidoCursoMockMvc.perform(delete("/api/contenido-cursos/{id}", contenidoCurso.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ContenidoCurso> contenidoCursoList = contenidoCursoRepository.findAll();
        assertThat(contenidoCursoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
