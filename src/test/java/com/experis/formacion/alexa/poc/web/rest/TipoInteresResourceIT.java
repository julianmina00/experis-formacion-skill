package com.experis.formacion.alexa.poc.web.rest;

import com.experis.formacion.alexa.poc.ExperisFormacionApp;
import com.experis.formacion.alexa.poc.domain.TipoInteres;
import com.experis.formacion.alexa.poc.repository.TipoInteresRepository;
import com.experis.formacion.alexa.poc.service.TipoInteresService;
import com.experis.formacion.alexa.poc.service.dto.TipoInteresDTO;
import com.experis.formacion.alexa.poc.service.mapper.TipoInteresMapper;
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
 * Integration tests for the {@link TipoInteresResource} REST controller.
 */
@SpringBootTest(classes = ExperisFormacionApp.class)
public class TipoInteresResourceIT {

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION_LARGA = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION_LARGA = "BBBBBBBBBB";

    @Autowired
    private TipoInteresRepository tipoInteresRepository;

    @Autowired
    private TipoInteresMapper tipoInteresMapper;

    @Autowired
    private TipoInteresService tipoInteresService;

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

    private MockMvc restTipoInteresMockMvc;

    private TipoInteres tipoInteres;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TipoInteresResource tipoInteresResource = new TipoInteresResource(tipoInteresService);
        this.restTipoInteresMockMvc = MockMvcBuilders.standaloneSetup(tipoInteresResource)
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
    public static TipoInteres createEntity(EntityManager em) {
        TipoInteres tipoInteres = new TipoInteres()
            .descripcion(DEFAULT_DESCRIPCION)
            .descripcionLarga(DEFAULT_DESCRIPCION_LARGA);
        return tipoInteres;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TipoInteres createUpdatedEntity(EntityManager em) {
        TipoInteres tipoInteres = new TipoInteres()
            .descripcion(UPDATED_DESCRIPCION)
            .descripcionLarga(UPDATED_DESCRIPCION_LARGA);
        return tipoInteres;
    }

    @BeforeEach
    public void initTest() {
        tipoInteres = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoInteres() throws Exception {
        int databaseSizeBeforeCreate = tipoInteresRepository.findAll().size();

        // Create the TipoInteres
        TipoInteresDTO tipoInteresDTO = tipoInteresMapper.toDto(tipoInteres);
        restTipoInteresMockMvc.perform(post("/api/tipo-interes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoInteresDTO)))
            .andExpect(status().isCreated());

        // Validate the TipoInteres in the database
        List<TipoInteres> tipoInteresList = tipoInteresRepository.findAll();
        assertThat(tipoInteresList).hasSize(databaseSizeBeforeCreate + 1);
        TipoInteres testTipoInteres = tipoInteresList.get(tipoInteresList.size() - 1);
        assertThat(testTipoInteres.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testTipoInteres.getDescripcionLarga()).isEqualTo(DEFAULT_DESCRIPCION_LARGA);
    }

    @Test
    @Transactional
    public void createTipoInteresWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoInteresRepository.findAll().size();

        // Create the TipoInteres with an existing ID
        tipoInteres.setId(1L);
        TipoInteresDTO tipoInteresDTO = tipoInteresMapper.toDto(tipoInteres);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoInteresMockMvc.perform(post("/api/tipo-interes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoInteresDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TipoInteres in the database
        List<TipoInteres> tipoInteresList = tipoInteresRepository.findAll();
        assertThat(tipoInteresList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDescripcionIsRequired() throws Exception {
        int databaseSizeBeforeTest = tipoInteresRepository.findAll().size();
        // set the field null
        tipoInteres.setDescripcion(null);

        // Create the TipoInteres, which fails.
        TipoInteresDTO tipoInteresDTO = tipoInteresMapper.toDto(tipoInteres);

        restTipoInteresMockMvc.perform(post("/api/tipo-interes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoInteresDTO)))
            .andExpect(status().isBadRequest());

        List<TipoInteres> tipoInteresList = tipoInteresRepository.findAll();
        assertThat(tipoInteresList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTipoInteres() throws Exception {
        // Initialize the database
        tipoInteresRepository.saveAndFlush(tipoInteres);

        // Get all the tipoInteresList
        restTipoInteresMockMvc.perform(get("/api/tipo-interes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoInteres.getId().intValue())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].descripcionLarga").value(hasItem(DEFAULT_DESCRIPCION_LARGA)));
    }
    
    @Test
    @Transactional
    public void getTipoInteres() throws Exception {
        // Initialize the database
        tipoInteresRepository.saveAndFlush(tipoInteres);

        // Get the tipoInteres
        restTipoInteresMockMvc.perform(get("/api/tipo-interes/{id}", tipoInteres.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipoInteres.getId().intValue()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION))
            .andExpect(jsonPath("$.descripcionLarga").value(DEFAULT_DESCRIPCION_LARGA));
    }

    @Test
    @Transactional
    public void getNonExistingTipoInteres() throws Exception {
        // Get the tipoInteres
        restTipoInteresMockMvc.perform(get("/api/tipo-interes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoInteres() throws Exception {
        // Initialize the database
        tipoInteresRepository.saveAndFlush(tipoInteres);

        int databaseSizeBeforeUpdate = tipoInteresRepository.findAll().size();

        // Update the tipoInteres
        TipoInteres updatedTipoInteres = tipoInteresRepository.findById(tipoInteres.getId()).get();
        // Disconnect from session so that the updates on updatedTipoInteres are not directly saved in db
        em.detach(updatedTipoInteres);
        updatedTipoInteres
            .descripcion(UPDATED_DESCRIPCION)
            .descripcionLarga(UPDATED_DESCRIPCION_LARGA);
        TipoInteresDTO tipoInteresDTO = tipoInteresMapper.toDto(updatedTipoInteres);

        restTipoInteresMockMvc.perform(put("/api/tipo-interes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoInteresDTO)))
            .andExpect(status().isOk());

        // Validate the TipoInteres in the database
        List<TipoInteres> tipoInteresList = tipoInteresRepository.findAll();
        assertThat(tipoInteresList).hasSize(databaseSizeBeforeUpdate);
        TipoInteres testTipoInteres = tipoInteresList.get(tipoInteresList.size() - 1);
        assertThat(testTipoInteres.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testTipoInteres.getDescripcionLarga()).isEqualTo(UPDATED_DESCRIPCION_LARGA);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoInteres() throws Exception {
        int databaseSizeBeforeUpdate = tipoInteresRepository.findAll().size();

        // Create the TipoInteres
        TipoInteresDTO tipoInteresDTO = tipoInteresMapper.toDto(tipoInteres);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipoInteresMockMvc.perform(put("/api/tipo-interes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoInteresDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TipoInteres in the database
        List<TipoInteres> tipoInteresList = tipoInteresRepository.findAll();
        assertThat(tipoInteresList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTipoInteres() throws Exception {
        // Initialize the database
        tipoInteresRepository.saveAndFlush(tipoInteres);

        int databaseSizeBeforeDelete = tipoInteresRepository.findAll().size();

        // Delete the tipoInteres
        restTipoInteresMockMvc.perform(delete("/api/tipo-interes/{id}", tipoInteres.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TipoInteres> tipoInteresList = tipoInteresRepository.findAll();
        assertThat(tipoInteresList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
