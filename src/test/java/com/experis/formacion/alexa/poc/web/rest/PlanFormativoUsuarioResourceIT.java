package com.experis.formacion.alexa.poc.web.rest;

import com.experis.formacion.alexa.poc.ExperisFormacionApp;
import com.experis.formacion.alexa.poc.domain.PlanFormativoUsuario;
import com.experis.formacion.alexa.poc.repository.PlanFormativoUsuarioRepository;
import com.experis.formacion.alexa.poc.service.PlanFormativoUsuarioService;
import com.experis.formacion.alexa.poc.service.dto.PlanFormativoUsuarioDTO;
import com.experis.formacion.alexa.poc.service.mapper.PlanFormativoUsuarioMapper;
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
 * Integration tests for the {@link PlanFormativoUsuarioResource} REST controller.
 */
@SpringBootTest(classes = ExperisFormacionApp.class)
public class PlanFormativoUsuarioResourceIT {

    @Autowired
    private PlanFormativoUsuarioRepository planFormativoUsuarioRepository;

    @Autowired
    private PlanFormativoUsuarioMapper planFormativoUsuarioMapper;

    @Autowired
    private PlanFormativoUsuarioService planFormativoUsuarioService;

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

    private MockMvc restPlanFormativoUsuarioMockMvc;

    private PlanFormativoUsuario planFormativoUsuario;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PlanFormativoUsuarioResource planFormativoUsuarioResource = new PlanFormativoUsuarioResource(planFormativoUsuarioService);
        this.restPlanFormativoUsuarioMockMvc = MockMvcBuilders.standaloneSetup(planFormativoUsuarioResource)
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
    public static PlanFormativoUsuario createEntity(EntityManager em) {
        PlanFormativoUsuario planFormativoUsuario = new PlanFormativoUsuario();
        return planFormativoUsuario;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PlanFormativoUsuario createUpdatedEntity(EntityManager em) {
        PlanFormativoUsuario planFormativoUsuario = new PlanFormativoUsuario();
        return planFormativoUsuario;
    }

    @BeforeEach
    public void initTest() {
        planFormativoUsuario = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlanFormativoUsuario() throws Exception {
        int databaseSizeBeforeCreate = planFormativoUsuarioRepository.findAll().size();

        // Create the PlanFormativoUsuario
        PlanFormativoUsuarioDTO planFormativoUsuarioDTO = planFormativoUsuarioMapper.toDto(planFormativoUsuario);
        restPlanFormativoUsuarioMockMvc.perform(post("/api/plan-formativo-usuarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planFormativoUsuarioDTO)))
            .andExpect(status().isCreated());

        // Validate the PlanFormativoUsuario in the database
        List<PlanFormativoUsuario> planFormativoUsuarioList = planFormativoUsuarioRepository.findAll();
        assertThat(planFormativoUsuarioList).hasSize(databaseSizeBeforeCreate + 1);
        PlanFormativoUsuario testPlanFormativoUsuario = planFormativoUsuarioList.get(planFormativoUsuarioList.size() - 1);
    }

    @Test
    @Transactional
    public void createPlanFormativoUsuarioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = planFormativoUsuarioRepository.findAll().size();

        // Create the PlanFormativoUsuario with an existing ID
        planFormativoUsuario.setId(1L);
        PlanFormativoUsuarioDTO planFormativoUsuarioDTO = planFormativoUsuarioMapper.toDto(planFormativoUsuario);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlanFormativoUsuarioMockMvc.perform(post("/api/plan-formativo-usuarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planFormativoUsuarioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlanFormativoUsuario in the database
        List<PlanFormativoUsuario> planFormativoUsuarioList = planFormativoUsuarioRepository.findAll();
        assertThat(planFormativoUsuarioList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPlanFormativoUsuarios() throws Exception {
        // Initialize the database
        planFormativoUsuarioRepository.saveAndFlush(planFormativoUsuario);

        // Get all the planFormativoUsuarioList
        restPlanFormativoUsuarioMockMvc.perform(get("/api/plan-formativo-usuarios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planFormativoUsuario.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getPlanFormativoUsuario() throws Exception {
        // Initialize the database
        planFormativoUsuarioRepository.saveAndFlush(planFormativoUsuario);

        // Get the planFormativoUsuario
        restPlanFormativoUsuarioMockMvc.perform(get("/api/plan-formativo-usuarios/{id}", planFormativoUsuario.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(planFormativoUsuario.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPlanFormativoUsuario() throws Exception {
        // Get the planFormativoUsuario
        restPlanFormativoUsuarioMockMvc.perform(get("/api/plan-formativo-usuarios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlanFormativoUsuario() throws Exception {
        // Initialize the database
        planFormativoUsuarioRepository.saveAndFlush(planFormativoUsuario);

        int databaseSizeBeforeUpdate = planFormativoUsuarioRepository.findAll().size();

        // Update the planFormativoUsuario
        PlanFormativoUsuario updatedPlanFormativoUsuario = planFormativoUsuarioRepository.findById(planFormativoUsuario.getId()).get();
        // Disconnect from session so that the updates on updatedPlanFormativoUsuario are not directly saved in db
        em.detach(updatedPlanFormativoUsuario);
        PlanFormativoUsuarioDTO planFormativoUsuarioDTO = planFormativoUsuarioMapper.toDto(updatedPlanFormativoUsuario);

        restPlanFormativoUsuarioMockMvc.perform(put("/api/plan-formativo-usuarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planFormativoUsuarioDTO)))
            .andExpect(status().isOk());

        // Validate the PlanFormativoUsuario in the database
        List<PlanFormativoUsuario> planFormativoUsuarioList = planFormativoUsuarioRepository.findAll();
        assertThat(planFormativoUsuarioList).hasSize(databaseSizeBeforeUpdate);
        PlanFormativoUsuario testPlanFormativoUsuario = planFormativoUsuarioList.get(planFormativoUsuarioList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingPlanFormativoUsuario() throws Exception {
        int databaseSizeBeforeUpdate = planFormativoUsuarioRepository.findAll().size();

        // Create the PlanFormativoUsuario
        PlanFormativoUsuarioDTO planFormativoUsuarioDTO = planFormativoUsuarioMapper.toDto(planFormativoUsuario);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanFormativoUsuarioMockMvc.perform(put("/api/plan-formativo-usuarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planFormativoUsuarioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlanFormativoUsuario in the database
        List<PlanFormativoUsuario> planFormativoUsuarioList = planFormativoUsuarioRepository.findAll();
        assertThat(planFormativoUsuarioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePlanFormativoUsuario() throws Exception {
        // Initialize the database
        planFormativoUsuarioRepository.saveAndFlush(planFormativoUsuario);

        int databaseSizeBeforeDelete = planFormativoUsuarioRepository.findAll().size();

        // Delete the planFormativoUsuario
        restPlanFormativoUsuarioMockMvc.perform(delete("/api/plan-formativo-usuarios/{id}", planFormativoUsuario.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PlanFormativoUsuario> planFormativoUsuarioList = planFormativoUsuarioRepository.findAll();
        assertThat(planFormativoUsuarioList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
