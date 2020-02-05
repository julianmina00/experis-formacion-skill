package com.experis.formacion.alexa.poc.web.rest;

import com.experis.formacion.alexa.poc.ExperisFormacionApp;
import com.experis.formacion.alexa.poc.domain.Interes;
import com.experis.formacion.alexa.poc.repository.InteresRepository;
import com.experis.formacion.alexa.poc.service.InteresService;
import com.experis.formacion.alexa.poc.service.dto.InteresDTO;
import com.experis.formacion.alexa.poc.service.mapper.InteresMapper;
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
 * Integration tests for the {@link InteresResource} REST controller.
 */
@SpringBootTest(classes = ExperisFormacionApp.class)
public class InteresResourceIT {

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION_LARGA = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION_LARGA = "BBBBBBBBBB";

    @Autowired
    private InteresRepository interesRepository;

    @Autowired
    private InteresMapper interesMapper;

    @Autowired
    private InteresService interesService;

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

    private MockMvc restInteresMockMvc;

    private Interes interes;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final InteresResource interesResource = new InteresResource(interesService);
        this.restInteresMockMvc = MockMvcBuilders.standaloneSetup(interesResource)
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
    public static Interes createEntity(EntityManager em) {
        Interes interes = new Interes()
            .descripcion(DEFAULT_DESCRIPCION)
            .descripcionLarga(DEFAULT_DESCRIPCION_LARGA);
        return interes;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Interes createUpdatedEntity(EntityManager em) {
        Interes interes = new Interes()
            .descripcion(UPDATED_DESCRIPCION)
            .descripcionLarga(UPDATED_DESCRIPCION_LARGA);
        return interes;
    }

    @BeforeEach
    public void initTest() {
        interes = createEntity(em);
    }

    @Test
    @Transactional
    public void createInteres() throws Exception {
        int databaseSizeBeforeCreate = interesRepository.findAll().size();

        // Create the Interes
        InteresDTO interesDTO = interesMapper.toDto(interes);
        restInteresMockMvc.perform(post("/api/interes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(interesDTO)))
            .andExpect(status().isCreated());

        // Validate the Interes in the database
        List<Interes> interesList = interesRepository.findAll();
        assertThat(interesList).hasSize(databaseSizeBeforeCreate + 1);
        Interes testInteres = interesList.get(interesList.size() - 1);
        assertThat(testInteres.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testInteres.getDescripcionLarga()).isEqualTo(DEFAULT_DESCRIPCION_LARGA);
    }

    @Test
    @Transactional
    public void createInteresWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = interesRepository.findAll().size();

        // Create the Interes with an existing ID
        interes.setId(1L);
        InteresDTO interesDTO = interesMapper.toDto(interes);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInteresMockMvc.perform(post("/api/interes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(interesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Interes in the database
        List<Interes> interesList = interesRepository.findAll();
        assertThat(interesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDescripcionIsRequired() throws Exception {
        int databaseSizeBeforeTest = interesRepository.findAll().size();
        // set the field null
        interes.setDescripcion(null);

        // Create the Interes, which fails.
        InteresDTO interesDTO = interesMapper.toDto(interes);

        restInteresMockMvc.perform(post("/api/interes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(interesDTO)))
            .andExpect(status().isBadRequest());

        List<Interes> interesList = interesRepository.findAll();
        assertThat(interesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInteres() throws Exception {
        // Initialize the database
        interesRepository.saveAndFlush(interes);

        // Get all the interesList
        restInteresMockMvc.perform(get("/api/interes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(interes.getId().intValue())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].descripcionLarga").value(hasItem(DEFAULT_DESCRIPCION_LARGA)));
    }
    
    @Test
    @Transactional
    public void getInteres() throws Exception {
        // Initialize the database
        interesRepository.saveAndFlush(interes);

        // Get the interes
        restInteresMockMvc.perform(get("/api/interes/{id}", interes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(interes.getId().intValue()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION))
            .andExpect(jsonPath("$.descripcionLarga").value(DEFAULT_DESCRIPCION_LARGA));
    }

    @Test
    @Transactional
    public void getNonExistingInteres() throws Exception {
        // Get the interes
        restInteresMockMvc.perform(get("/api/interes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInteres() throws Exception {
        // Initialize the database
        interesRepository.saveAndFlush(interes);

        int databaseSizeBeforeUpdate = interesRepository.findAll().size();

        // Update the interes
        Interes updatedInteres = interesRepository.findById(interes.getId()).get();
        // Disconnect from session so that the updates on updatedInteres are not directly saved in db
        em.detach(updatedInteres);
        updatedInteres
            .descripcion(UPDATED_DESCRIPCION)
            .descripcionLarga(UPDATED_DESCRIPCION_LARGA);
        InteresDTO interesDTO = interesMapper.toDto(updatedInteres);

        restInteresMockMvc.perform(put("/api/interes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(interesDTO)))
            .andExpect(status().isOk());

        // Validate the Interes in the database
        List<Interes> interesList = interesRepository.findAll();
        assertThat(interesList).hasSize(databaseSizeBeforeUpdate);
        Interes testInteres = interesList.get(interesList.size() - 1);
        assertThat(testInteres.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testInteres.getDescripcionLarga()).isEqualTo(UPDATED_DESCRIPCION_LARGA);
    }

    @Test
    @Transactional
    public void updateNonExistingInteres() throws Exception {
        int databaseSizeBeforeUpdate = interesRepository.findAll().size();

        // Create the Interes
        InteresDTO interesDTO = interesMapper.toDto(interes);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInteresMockMvc.perform(put("/api/interes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(interesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Interes in the database
        List<Interes> interesList = interesRepository.findAll();
        assertThat(interesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInteres() throws Exception {
        // Initialize the database
        interesRepository.saveAndFlush(interes);

        int databaseSizeBeforeDelete = interesRepository.findAll().size();

        // Delete the interes
        restInteresMockMvc.perform(delete("/api/interes/{id}", interes.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Interes> interesList = interesRepository.findAll();
        assertThat(interesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
