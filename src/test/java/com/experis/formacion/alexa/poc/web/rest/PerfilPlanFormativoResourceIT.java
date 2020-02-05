package com.experis.formacion.alexa.poc.web.rest;

import com.experis.formacion.alexa.poc.ExperisFormacionApp;
import com.experis.formacion.alexa.poc.domain.PerfilPlanFormativo;
import com.experis.formacion.alexa.poc.repository.PerfilPlanFormativoRepository;
import com.experis.formacion.alexa.poc.service.PerfilPlanFormativoService;
import com.experis.formacion.alexa.poc.service.dto.PerfilPlanFormativoDTO;
import com.experis.formacion.alexa.poc.service.mapper.PerfilPlanFormativoMapper;
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
 * Integration tests for the {@link PerfilPlanFormativoResource} REST controller.
 */
@SpringBootTest(classes = ExperisFormacionApp.class)
public class PerfilPlanFormativoResourceIT {

    private static final String DEFAULT_INTERES_HABILIDAD = "H";
    private static final String UPDATED_INTERES_HABILIDAD = "I";

    @Autowired
    private PerfilPlanFormativoRepository perfilPlanFormativoRepository;

    @Autowired
    private PerfilPlanFormativoMapper perfilPlanFormativoMapper;

    @Autowired
    private PerfilPlanFormativoService perfilPlanFormativoService;

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

    private MockMvc restPerfilPlanFormativoMockMvc;

    private PerfilPlanFormativo perfilPlanFormativo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PerfilPlanFormativoResource perfilPlanFormativoResource = new PerfilPlanFormativoResource(perfilPlanFormativoService);
        this.restPerfilPlanFormativoMockMvc = MockMvcBuilders.standaloneSetup(perfilPlanFormativoResource)
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
    public static PerfilPlanFormativo createEntity(EntityManager em) {
        PerfilPlanFormativo perfilPlanFormativo = new PerfilPlanFormativo()
            .interesHabilidad(DEFAULT_INTERES_HABILIDAD);
        return perfilPlanFormativo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PerfilPlanFormativo createUpdatedEntity(EntityManager em) {
        PerfilPlanFormativo perfilPlanFormativo = new PerfilPlanFormativo()
            .interesHabilidad(UPDATED_INTERES_HABILIDAD);
        return perfilPlanFormativo;
    }

    @BeforeEach
    public void initTest() {
        perfilPlanFormativo = createEntity(em);
    }

    @Test
    @Transactional
    public void createPerfilPlanFormativo() throws Exception {
        int databaseSizeBeforeCreate = perfilPlanFormativoRepository.findAll().size();

        // Create the PerfilPlanFormativo
        PerfilPlanFormativoDTO perfilPlanFormativoDTO = perfilPlanFormativoMapper.toDto(perfilPlanFormativo);
        restPerfilPlanFormativoMockMvc.perform(post("/api/perfil-plan-formativos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perfilPlanFormativoDTO)))
            .andExpect(status().isCreated());

        // Validate the PerfilPlanFormativo in the database
        List<PerfilPlanFormativo> perfilPlanFormativoList = perfilPlanFormativoRepository.findAll();
        assertThat(perfilPlanFormativoList).hasSize(databaseSizeBeforeCreate + 1);
        PerfilPlanFormativo testPerfilPlanFormativo = perfilPlanFormativoList.get(perfilPlanFormativoList.size() - 1);
        assertThat(testPerfilPlanFormativo.getInteresHabilidad()).isEqualTo(DEFAULT_INTERES_HABILIDAD);
    }

    @Test
    @Transactional
    public void createPerfilPlanFormativoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = perfilPlanFormativoRepository.findAll().size();

        // Create the PerfilPlanFormativo with an existing ID
        perfilPlanFormativo.setId(1L);
        PerfilPlanFormativoDTO perfilPlanFormativoDTO = perfilPlanFormativoMapper.toDto(perfilPlanFormativo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPerfilPlanFormativoMockMvc.perform(post("/api/perfil-plan-formativos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perfilPlanFormativoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PerfilPlanFormativo in the database
        List<PerfilPlanFormativo> perfilPlanFormativoList = perfilPlanFormativoRepository.findAll();
        assertThat(perfilPlanFormativoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkInteresHabilidadIsRequired() throws Exception {
        int databaseSizeBeforeTest = perfilPlanFormativoRepository.findAll().size();
        // set the field null
        perfilPlanFormativo.setInteresHabilidad(null);

        // Create the PerfilPlanFormativo, which fails.
        PerfilPlanFormativoDTO perfilPlanFormativoDTO = perfilPlanFormativoMapper.toDto(perfilPlanFormativo);

        restPerfilPlanFormativoMockMvc.perform(post("/api/perfil-plan-formativos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perfilPlanFormativoDTO)))
            .andExpect(status().isBadRequest());

        List<PerfilPlanFormativo> perfilPlanFormativoList = perfilPlanFormativoRepository.findAll();
        assertThat(perfilPlanFormativoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPerfilPlanFormativos() throws Exception {
        // Initialize the database
        perfilPlanFormativoRepository.saveAndFlush(perfilPlanFormativo);

        // Get all the perfilPlanFormativoList
        restPerfilPlanFormativoMockMvc.perform(get("/api/perfil-plan-formativos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(perfilPlanFormativo.getId().intValue())))
            .andExpect(jsonPath("$.[*].interesHabilidad").value(hasItem(DEFAULT_INTERES_HABILIDAD)));
    }
    
    @Test
    @Transactional
    public void getPerfilPlanFormativo() throws Exception {
        // Initialize the database
        perfilPlanFormativoRepository.saveAndFlush(perfilPlanFormativo);

        // Get the perfilPlanFormativo
        restPerfilPlanFormativoMockMvc.perform(get("/api/perfil-plan-formativos/{id}", perfilPlanFormativo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(perfilPlanFormativo.getId().intValue()))
            .andExpect(jsonPath("$.interesHabilidad").value(DEFAULT_INTERES_HABILIDAD));
    }

    @Test
    @Transactional
    public void getNonExistingPerfilPlanFormativo() throws Exception {
        // Get the perfilPlanFormativo
        restPerfilPlanFormativoMockMvc.perform(get("/api/perfil-plan-formativos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePerfilPlanFormativo() throws Exception {
        // Initialize the database
        perfilPlanFormativoRepository.saveAndFlush(perfilPlanFormativo);

        int databaseSizeBeforeUpdate = perfilPlanFormativoRepository.findAll().size();

        // Update the perfilPlanFormativo
        PerfilPlanFormativo updatedPerfilPlanFormativo = perfilPlanFormativoRepository.findById(perfilPlanFormativo.getId()).get();
        // Disconnect from session so that the updates on updatedPerfilPlanFormativo are not directly saved in db
        em.detach(updatedPerfilPlanFormativo);
        updatedPerfilPlanFormativo
            .interesHabilidad(UPDATED_INTERES_HABILIDAD);
        PerfilPlanFormativoDTO perfilPlanFormativoDTO = perfilPlanFormativoMapper.toDto(updatedPerfilPlanFormativo);

        restPerfilPlanFormativoMockMvc.perform(put("/api/perfil-plan-formativos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perfilPlanFormativoDTO)))
            .andExpect(status().isOk());

        // Validate the PerfilPlanFormativo in the database
        List<PerfilPlanFormativo> perfilPlanFormativoList = perfilPlanFormativoRepository.findAll();
        assertThat(perfilPlanFormativoList).hasSize(databaseSizeBeforeUpdate);
        PerfilPlanFormativo testPerfilPlanFormativo = perfilPlanFormativoList.get(perfilPlanFormativoList.size() - 1);
        assertThat(testPerfilPlanFormativo.getInteresHabilidad()).isEqualTo(UPDATED_INTERES_HABILIDAD);
    }

    @Test
    @Transactional
    public void updateNonExistingPerfilPlanFormativo() throws Exception {
        int databaseSizeBeforeUpdate = perfilPlanFormativoRepository.findAll().size();

        // Create the PerfilPlanFormativo
        PerfilPlanFormativoDTO perfilPlanFormativoDTO = perfilPlanFormativoMapper.toDto(perfilPlanFormativo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPerfilPlanFormativoMockMvc.perform(put("/api/perfil-plan-formativos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perfilPlanFormativoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PerfilPlanFormativo in the database
        List<PerfilPlanFormativo> perfilPlanFormativoList = perfilPlanFormativoRepository.findAll();
        assertThat(perfilPlanFormativoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePerfilPlanFormativo() throws Exception {
        // Initialize the database
        perfilPlanFormativoRepository.saveAndFlush(perfilPlanFormativo);

        int databaseSizeBeforeDelete = perfilPlanFormativoRepository.findAll().size();

        // Delete the perfilPlanFormativo
        restPerfilPlanFormativoMockMvc.perform(delete("/api/perfil-plan-formativos/{id}", perfilPlanFormativo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PerfilPlanFormativo> perfilPlanFormativoList = perfilPlanFormativoRepository.findAll();
        assertThat(perfilPlanFormativoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
