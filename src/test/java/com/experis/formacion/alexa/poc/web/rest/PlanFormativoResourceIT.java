package com.experis.formacion.alexa.poc.web.rest;

import com.experis.formacion.alexa.poc.ExperisFormacionApp;
import com.experis.formacion.alexa.poc.domain.PlanFormativo;
import com.experis.formacion.alexa.poc.repository.PlanFormativoRepository;
import com.experis.formacion.alexa.poc.service.PlanFormativoService;
import com.experis.formacion.alexa.poc.service.dto.PlanFormativoDTO;
import com.experis.formacion.alexa.poc.service.mapper.PlanFormativoMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.experis.formacion.alexa.poc.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PlanFormativoResource} REST controller.
 */
@SpringBootTest(classes = ExperisFormacionApp.class)
public class PlanFormativoResourceIT {

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION_LARGA = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION_LARGA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA_INICIO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_INICIO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_FECHA_FIN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_FIN = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private PlanFormativoRepository planFormativoRepository;

    @Autowired
    private PlanFormativoMapper planFormativoMapper;

    @Autowired
    private PlanFormativoService planFormativoService;

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

    private MockMvc restPlanFormativoMockMvc;

    private PlanFormativo planFormativo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PlanFormativoResource planFormativoResource = new PlanFormativoResource(planFormativoService);
        this.restPlanFormativoMockMvc = MockMvcBuilders.standaloneSetup(planFormativoResource)
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
    public static PlanFormativo createEntity(EntityManager em) {
        PlanFormativo planFormativo = new PlanFormativo()
            .descripcion(DEFAULT_DESCRIPCION)
            .descripcionLarga(DEFAULT_DESCRIPCION_LARGA)
            .fechaInicio(DEFAULT_FECHA_INICIO)
            .fechaFin(DEFAULT_FECHA_FIN);
        return planFormativo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PlanFormativo createUpdatedEntity(EntityManager em) {
        PlanFormativo planFormativo = new PlanFormativo()
            .descripcion(UPDATED_DESCRIPCION)
            .descripcionLarga(UPDATED_DESCRIPCION_LARGA)
            .fechaInicio(UPDATED_FECHA_INICIO)
            .fechaFin(UPDATED_FECHA_FIN);
        return planFormativo;
    }

    @BeforeEach
    public void initTest() {
        planFormativo = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlanFormativo() throws Exception {
        int databaseSizeBeforeCreate = planFormativoRepository.findAll().size();

        // Create the PlanFormativo
        PlanFormativoDTO planFormativoDTO = planFormativoMapper.toDto(planFormativo);
        restPlanFormativoMockMvc.perform(post("/api/plan-formativos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planFormativoDTO)))
            .andExpect(status().isCreated());

        // Validate the PlanFormativo in the database
        List<PlanFormativo> planFormativoList = planFormativoRepository.findAll();
        assertThat(planFormativoList).hasSize(databaseSizeBeforeCreate + 1);
        PlanFormativo testPlanFormativo = planFormativoList.get(planFormativoList.size() - 1);
        assertThat(testPlanFormativo.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testPlanFormativo.getDescripcionLarga()).isEqualTo(DEFAULT_DESCRIPCION_LARGA);
        assertThat(testPlanFormativo.getFechaInicio()).isEqualTo(DEFAULT_FECHA_INICIO);
        assertThat(testPlanFormativo.getFechaFin()).isEqualTo(DEFAULT_FECHA_FIN);
    }

    @Test
    @Transactional
    public void createPlanFormativoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = planFormativoRepository.findAll().size();

        // Create the PlanFormativo with an existing ID
        planFormativo.setId(1L);
        PlanFormativoDTO planFormativoDTO = planFormativoMapper.toDto(planFormativo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlanFormativoMockMvc.perform(post("/api/plan-formativos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planFormativoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlanFormativo in the database
        List<PlanFormativo> planFormativoList = planFormativoRepository.findAll();
        assertThat(planFormativoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDescripcionIsRequired() throws Exception {
        int databaseSizeBeforeTest = planFormativoRepository.findAll().size();
        // set the field null
        planFormativo.setDescripcion(null);

        // Create the PlanFormativo, which fails.
        PlanFormativoDTO planFormativoDTO = planFormativoMapper.toDto(planFormativo);

        restPlanFormativoMockMvc.perform(post("/api/plan-formativos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planFormativoDTO)))
            .andExpect(status().isBadRequest());

        List<PlanFormativo> planFormativoList = planFormativoRepository.findAll();
        assertThat(planFormativoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFechaInicioIsRequired() throws Exception {
        int databaseSizeBeforeTest = planFormativoRepository.findAll().size();
        // set the field null
        planFormativo.setFechaInicio(null);

        // Create the PlanFormativo, which fails.
        PlanFormativoDTO planFormativoDTO = planFormativoMapper.toDto(planFormativo);

        restPlanFormativoMockMvc.perform(post("/api/plan-formativos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planFormativoDTO)))
            .andExpect(status().isBadRequest());

        List<PlanFormativo> planFormativoList = planFormativoRepository.findAll();
        assertThat(planFormativoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFechaFinIsRequired() throws Exception {
        int databaseSizeBeforeTest = planFormativoRepository.findAll().size();
        // set the field null
        planFormativo.setFechaFin(null);

        // Create the PlanFormativo, which fails.
        PlanFormativoDTO planFormativoDTO = planFormativoMapper.toDto(planFormativo);

        restPlanFormativoMockMvc.perform(post("/api/plan-formativos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planFormativoDTO)))
            .andExpect(status().isBadRequest());

        List<PlanFormativo> planFormativoList = planFormativoRepository.findAll();
        assertThat(planFormativoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPlanFormativos() throws Exception {
        // Initialize the database
        planFormativoRepository.saveAndFlush(planFormativo);

        // Get all the planFormativoList
        restPlanFormativoMockMvc.perform(get("/api/plan-formativos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planFormativo.getId().intValue())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].descripcionLarga").value(hasItem(DEFAULT_DESCRIPCION_LARGA)))
            .andExpect(jsonPath("$.[*].fechaInicio").value(hasItem(DEFAULT_FECHA_INICIO.toString())))
            .andExpect(jsonPath("$.[*].fechaFin").value(hasItem(DEFAULT_FECHA_FIN.toString())));
    }
    
    @Test
    @Transactional
    public void getPlanFormativo() throws Exception {
        // Initialize the database
        planFormativoRepository.saveAndFlush(planFormativo);

        // Get the planFormativo
        restPlanFormativoMockMvc.perform(get("/api/plan-formativos/{id}", planFormativo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(planFormativo.getId().intValue()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION))
            .andExpect(jsonPath("$.descripcionLarga").value(DEFAULT_DESCRIPCION_LARGA))
            .andExpect(jsonPath("$.fechaInicio").value(DEFAULT_FECHA_INICIO.toString()))
            .andExpect(jsonPath("$.fechaFin").value(DEFAULT_FECHA_FIN.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPlanFormativo() throws Exception {
        // Get the planFormativo
        restPlanFormativoMockMvc.perform(get("/api/plan-formativos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlanFormativo() throws Exception {
        // Initialize the database
        planFormativoRepository.saveAndFlush(planFormativo);

        int databaseSizeBeforeUpdate = planFormativoRepository.findAll().size();

        // Update the planFormativo
        PlanFormativo updatedPlanFormativo = planFormativoRepository.findById(planFormativo.getId()).get();
        // Disconnect from session so that the updates on updatedPlanFormativo are not directly saved in db
        em.detach(updatedPlanFormativo);
        updatedPlanFormativo
            .descripcion(UPDATED_DESCRIPCION)
            .descripcionLarga(UPDATED_DESCRIPCION_LARGA)
            .fechaInicio(UPDATED_FECHA_INICIO)
            .fechaFin(UPDATED_FECHA_FIN);
        PlanFormativoDTO planFormativoDTO = planFormativoMapper.toDto(updatedPlanFormativo);

        restPlanFormativoMockMvc.perform(put("/api/plan-formativos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planFormativoDTO)))
            .andExpect(status().isOk());

        // Validate the PlanFormativo in the database
        List<PlanFormativo> planFormativoList = planFormativoRepository.findAll();
        assertThat(planFormativoList).hasSize(databaseSizeBeforeUpdate);
        PlanFormativo testPlanFormativo = planFormativoList.get(planFormativoList.size() - 1);
        assertThat(testPlanFormativo.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testPlanFormativo.getDescripcionLarga()).isEqualTo(UPDATED_DESCRIPCION_LARGA);
        assertThat(testPlanFormativo.getFechaInicio()).isEqualTo(UPDATED_FECHA_INICIO);
        assertThat(testPlanFormativo.getFechaFin()).isEqualTo(UPDATED_FECHA_FIN);
    }

    @Test
    @Transactional
    public void updateNonExistingPlanFormativo() throws Exception {
        int databaseSizeBeforeUpdate = planFormativoRepository.findAll().size();

        // Create the PlanFormativo
        PlanFormativoDTO planFormativoDTO = planFormativoMapper.toDto(planFormativo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanFormativoMockMvc.perform(put("/api/plan-formativos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planFormativoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlanFormativo in the database
        List<PlanFormativo> planFormativoList = planFormativoRepository.findAll();
        assertThat(planFormativoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePlanFormativo() throws Exception {
        // Initialize the database
        planFormativoRepository.saveAndFlush(planFormativo);

        int databaseSizeBeforeDelete = planFormativoRepository.findAll().size();

        // Delete the planFormativo
        restPlanFormativoMockMvc.perform(delete("/api/plan-formativos/{id}", planFormativo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PlanFormativo> planFormativoList = planFormativoRepository.findAll();
        assertThat(planFormativoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
