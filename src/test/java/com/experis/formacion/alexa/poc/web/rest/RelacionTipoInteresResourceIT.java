package com.experis.formacion.alexa.poc.web.rest;

import com.experis.formacion.alexa.poc.ExperisFormacionApp;
import com.experis.formacion.alexa.poc.domain.RelacionTipoInteres;
import com.experis.formacion.alexa.poc.repository.RelacionTipoInteresRepository;
import com.experis.formacion.alexa.poc.service.RelacionTipoInteresService;
import com.experis.formacion.alexa.poc.service.dto.RelacionTipoInteresDTO;
import com.experis.formacion.alexa.poc.service.mapper.RelacionTipoInteresMapper;
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
 * Integration tests for the {@link RelacionTipoInteresResource} REST controller.
 */
@SpringBootTest(classes = ExperisFormacionApp.class)
public class RelacionTipoInteresResourceIT {

    private static final Integer DEFAULT_PROFUNDIDAD = 10;
    private static final Integer UPDATED_PROFUNDIDAD = 9;

    @Autowired
    private RelacionTipoInteresRepository relacionTipoInteresRepository;

    @Autowired
    private RelacionTipoInteresMapper relacionTipoInteresMapper;

    @Autowired
    private RelacionTipoInteresService relacionTipoInteresService;

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

    private MockMvc restRelacionTipoInteresMockMvc;

    private RelacionTipoInteres relacionTipoInteres;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RelacionTipoInteresResource relacionTipoInteresResource = new RelacionTipoInteresResource(relacionTipoInteresService);
        this.restRelacionTipoInteresMockMvc = MockMvcBuilders.standaloneSetup(relacionTipoInteresResource)
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
    public static RelacionTipoInteres createEntity(EntityManager em) {
        RelacionTipoInteres relacionTipoInteres = new RelacionTipoInteres()
            .profundidad(DEFAULT_PROFUNDIDAD);
        return relacionTipoInteres;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RelacionTipoInteres createUpdatedEntity(EntityManager em) {
        RelacionTipoInteres relacionTipoInteres = new RelacionTipoInteres()
            .profundidad(UPDATED_PROFUNDIDAD);
        return relacionTipoInteres;
    }

    @BeforeEach
    public void initTest() {
        relacionTipoInteres = createEntity(em);
    }

    @Test
    @Transactional
    public void createRelacionTipoInteres() throws Exception {
        int databaseSizeBeforeCreate = relacionTipoInteresRepository.findAll().size();

        // Create the RelacionTipoInteres
        RelacionTipoInteresDTO relacionTipoInteresDTO = relacionTipoInteresMapper.toDto(relacionTipoInteres);
        restRelacionTipoInteresMockMvc.perform(post("/api/relacion-tipo-interes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relacionTipoInteresDTO)))
            .andExpect(status().isCreated());

        // Validate the RelacionTipoInteres in the database
        List<RelacionTipoInteres> relacionTipoInteresList = relacionTipoInteresRepository.findAll();
        assertThat(relacionTipoInteresList).hasSize(databaseSizeBeforeCreate + 1);
        RelacionTipoInteres testRelacionTipoInteres = relacionTipoInteresList.get(relacionTipoInteresList.size() - 1);
        assertThat(testRelacionTipoInteres.getProfundidad()).isEqualTo(DEFAULT_PROFUNDIDAD);
    }

    @Test
    @Transactional
    public void createRelacionTipoInteresWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = relacionTipoInteresRepository.findAll().size();

        // Create the RelacionTipoInteres with an existing ID
        relacionTipoInteres.setId(1L);
        RelacionTipoInteresDTO relacionTipoInteresDTO = relacionTipoInteresMapper.toDto(relacionTipoInteres);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRelacionTipoInteresMockMvc.perform(post("/api/relacion-tipo-interes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relacionTipoInteresDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RelacionTipoInteres in the database
        List<RelacionTipoInteres> relacionTipoInteresList = relacionTipoInteresRepository.findAll();
        assertThat(relacionTipoInteresList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkProfundidadIsRequired() throws Exception {
        int databaseSizeBeforeTest = relacionTipoInteresRepository.findAll().size();
        // set the field null
        relacionTipoInteres.setProfundidad(null);

        // Create the RelacionTipoInteres, which fails.
        RelacionTipoInteresDTO relacionTipoInteresDTO = relacionTipoInteresMapper.toDto(relacionTipoInteres);

        restRelacionTipoInteresMockMvc.perform(post("/api/relacion-tipo-interes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relacionTipoInteresDTO)))
            .andExpect(status().isBadRequest());

        List<RelacionTipoInteres> relacionTipoInteresList = relacionTipoInteresRepository.findAll();
        assertThat(relacionTipoInteresList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRelacionTipoInteres() throws Exception {
        // Initialize the database
        relacionTipoInteresRepository.saveAndFlush(relacionTipoInteres);

        // Get all the relacionTipoInteresList
        restRelacionTipoInteresMockMvc.perform(get("/api/relacion-tipo-interes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(relacionTipoInteres.getId().intValue())))
            .andExpect(jsonPath("$.[*].profundidad").value(hasItem(DEFAULT_PROFUNDIDAD)));
    }
    
    @Test
    @Transactional
    public void getRelacionTipoInteres() throws Exception {
        // Initialize the database
        relacionTipoInteresRepository.saveAndFlush(relacionTipoInteres);

        // Get the relacionTipoInteres
        restRelacionTipoInteresMockMvc.perform(get("/api/relacion-tipo-interes/{id}", relacionTipoInteres.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(relacionTipoInteres.getId().intValue()))
            .andExpect(jsonPath("$.profundidad").value(DEFAULT_PROFUNDIDAD));
    }

    @Test
    @Transactional
    public void getNonExistingRelacionTipoInteres() throws Exception {
        // Get the relacionTipoInteres
        restRelacionTipoInteresMockMvc.perform(get("/api/relacion-tipo-interes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRelacionTipoInteres() throws Exception {
        // Initialize the database
        relacionTipoInteresRepository.saveAndFlush(relacionTipoInteres);

        int databaseSizeBeforeUpdate = relacionTipoInteresRepository.findAll().size();

        // Update the relacionTipoInteres
        RelacionTipoInteres updatedRelacionTipoInteres = relacionTipoInteresRepository.findById(relacionTipoInteres.getId()).get();
        // Disconnect from session so that the updates on updatedRelacionTipoInteres are not directly saved in db
        em.detach(updatedRelacionTipoInteres);
        updatedRelacionTipoInteres
            .profundidad(UPDATED_PROFUNDIDAD);
        RelacionTipoInteresDTO relacionTipoInteresDTO = relacionTipoInteresMapper.toDto(updatedRelacionTipoInteres);

        restRelacionTipoInteresMockMvc.perform(put("/api/relacion-tipo-interes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relacionTipoInteresDTO)))
            .andExpect(status().isOk());

        // Validate the RelacionTipoInteres in the database
        List<RelacionTipoInteres> relacionTipoInteresList = relacionTipoInteresRepository.findAll();
        assertThat(relacionTipoInteresList).hasSize(databaseSizeBeforeUpdate);
        RelacionTipoInteres testRelacionTipoInteres = relacionTipoInteresList.get(relacionTipoInteresList.size() - 1);
        assertThat(testRelacionTipoInteres.getProfundidad()).isEqualTo(UPDATED_PROFUNDIDAD);
    }

    @Test
    @Transactional
    public void updateNonExistingRelacionTipoInteres() throws Exception {
        int databaseSizeBeforeUpdate = relacionTipoInteresRepository.findAll().size();

        // Create the RelacionTipoInteres
        RelacionTipoInteresDTO relacionTipoInteresDTO = relacionTipoInteresMapper.toDto(relacionTipoInteres);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRelacionTipoInteresMockMvc.perform(put("/api/relacion-tipo-interes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relacionTipoInteresDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RelacionTipoInteres in the database
        List<RelacionTipoInteres> relacionTipoInteresList = relacionTipoInteresRepository.findAll();
        assertThat(relacionTipoInteresList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRelacionTipoInteres() throws Exception {
        // Initialize the database
        relacionTipoInteresRepository.saveAndFlush(relacionTipoInteres);

        int databaseSizeBeforeDelete = relacionTipoInteresRepository.findAll().size();

        // Delete the relacionTipoInteres
        restRelacionTipoInteresMockMvc.perform(delete("/api/relacion-tipo-interes/{id}", relacionTipoInteres.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RelacionTipoInteres> relacionTipoInteresList = relacionTipoInteresRepository.findAll();
        assertThat(relacionTipoInteresList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
