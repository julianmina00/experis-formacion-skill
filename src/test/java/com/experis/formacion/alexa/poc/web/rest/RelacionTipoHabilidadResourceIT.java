package com.experis.formacion.alexa.poc.web.rest;

import com.experis.formacion.alexa.poc.ExperisFormacionApp;
import com.experis.formacion.alexa.poc.domain.RelacionTipoHabilidad;
import com.experis.formacion.alexa.poc.repository.RelacionTipoHabilidadRepository;
import com.experis.formacion.alexa.poc.service.RelacionTipoHabilidadService;
import com.experis.formacion.alexa.poc.service.dto.RelacionTipoHabilidadDTO;
import com.experis.formacion.alexa.poc.service.mapper.RelacionTipoHabilidadMapper;
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
 * Integration tests for the {@link RelacionTipoHabilidadResource} REST controller.
 */
@SpringBootTest(classes = ExperisFormacionApp.class)
public class RelacionTipoHabilidadResourceIT {

    private static final Integer DEFAULT_PROFUNDIDAD = 10;
    private static final Integer UPDATED_PROFUNDIDAD = 9;

    @Autowired
    private RelacionTipoHabilidadRepository relacionTipoHabilidadRepository;

    @Autowired
    private RelacionTipoHabilidadMapper relacionTipoHabilidadMapper;

    @Autowired
    private RelacionTipoHabilidadService relacionTipoHabilidadService;

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

    private MockMvc restRelacionTipoHabilidadMockMvc;

    private RelacionTipoHabilidad relacionTipoHabilidad;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RelacionTipoHabilidadResource relacionTipoHabilidadResource = new RelacionTipoHabilidadResource(relacionTipoHabilidadService);
        this.restRelacionTipoHabilidadMockMvc = MockMvcBuilders.standaloneSetup(relacionTipoHabilidadResource)
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
    public static RelacionTipoHabilidad createEntity(EntityManager em) {
        RelacionTipoHabilidad relacionTipoHabilidad = new RelacionTipoHabilidad()
            .profundidad(DEFAULT_PROFUNDIDAD);
        return relacionTipoHabilidad;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RelacionTipoHabilidad createUpdatedEntity(EntityManager em) {
        RelacionTipoHabilidad relacionTipoHabilidad = new RelacionTipoHabilidad()
            .profundidad(UPDATED_PROFUNDIDAD);
        return relacionTipoHabilidad;
    }

    @BeforeEach
    public void initTest() {
        relacionTipoHabilidad = createEntity(em);
    }

    @Test
    @Transactional
    public void createRelacionTipoHabilidad() throws Exception {
        int databaseSizeBeforeCreate = relacionTipoHabilidadRepository.findAll().size();

        // Create the RelacionTipoHabilidad
        RelacionTipoHabilidadDTO relacionTipoHabilidadDTO = relacionTipoHabilidadMapper.toDto(relacionTipoHabilidad);
        restRelacionTipoHabilidadMockMvc.perform(post("/api/relacion-tipo-habilidads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relacionTipoHabilidadDTO)))
            .andExpect(status().isCreated());

        // Validate the RelacionTipoHabilidad in the database
        List<RelacionTipoHabilidad> relacionTipoHabilidadList = relacionTipoHabilidadRepository.findAll();
        assertThat(relacionTipoHabilidadList).hasSize(databaseSizeBeforeCreate + 1);
        RelacionTipoHabilidad testRelacionTipoHabilidad = relacionTipoHabilidadList.get(relacionTipoHabilidadList.size() - 1);
        assertThat(testRelacionTipoHabilidad.getProfundidad()).isEqualTo(DEFAULT_PROFUNDIDAD);
    }

    @Test
    @Transactional
    public void createRelacionTipoHabilidadWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = relacionTipoHabilidadRepository.findAll().size();

        // Create the RelacionTipoHabilidad with an existing ID
        relacionTipoHabilidad.setId(1L);
        RelacionTipoHabilidadDTO relacionTipoHabilidadDTO = relacionTipoHabilidadMapper.toDto(relacionTipoHabilidad);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRelacionTipoHabilidadMockMvc.perform(post("/api/relacion-tipo-habilidads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relacionTipoHabilidadDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RelacionTipoHabilidad in the database
        List<RelacionTipoHabilidad> relacionTipoHabilidadList = relacionTipoHabilidadRepository.findAll();
        assertThat(relacionTipoHabilidadList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkProfundidadIsRequired() throws Exception {
        int databaseSizeBeforeTest = relacionTipoHabilidadRepository.findAll().size();
        // set the field null
        relacionTipoHabilidad.setProfundidad(null);

        // Create the RelacionTipoHabilidad, which fails.
        RelacionTipoHabilidadDTO relacionTipoHabilidadDTO = relacionTipoHabilidadMapper.toDto(relacionTipoHabilidad);

        restRelacionTipoHabilidadMockMvc.perform(post("/api/relacion-tipo-habilidads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relacionTipoHabilidadDTO)))
            .andExpect(status().isBadRequest());

        List<RelacionTipoHabilidad> relacionTipoHabilidadList = relacionTipoHabilidadRepository.findAll();
        assertThat(relacionTipoHabilidadList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRelacionTipoHabilidads() throws Exception {
        // Initialize the database
        relacionTipoHabilidadRepository.saveAndFlush(relacionTipoHabilidad);

        // Get all the relacionTipoHabilidadList
        restRelacionTipoHabilidadMockMvc.perform(get("/api/relacion-tipo-habilidads?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(relacionTipoHabilidad.getId().intValue())))
            .andExpect(jsonPath("$.[*].profundidad").value(hasItem(DEFAULT_PROFUNDIDAD)));
    }
    
    @Test
    @Transactional
    public void getRelacionTipoHabilidad() throws Exception {
        // Initialize the database
        relacionTipoHabilidadRepository.saveAndFlush(relacionTipoHabilidad);

        // Get the relacionTipoHabilidad
        restRelacionTipoHabilidadMockMvc.perform(get("/api/relacion-tipo-habilidads/{id}", relacionTipoHabilidad.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(relacionTipoHabilidad.getId().intValue()))
            .andExpect(jsonPath("$.profundidad").value(DEFAULT_PROFUNDIDAD));
    }

    @Test
    @Transactional
    public void getNonExistingRelacionTipoHabilidad() throws Exception {
        // Get the relacionTipoHabilidad
        restRelacionTipoHabilidadMockMvc.perform(get("/api/relacion-tipo-habilidads/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRelacionTipoHabilidad() throws Exception {
        // Initialize the database
        relacionTipoHabilidadRepository.saveAndFlush(relacionTipoHabilidad);

        int databaseSizeBeforeUpdate = relacionTipoHabilidadRepository.findAll().size();

        // Update the relacionTipoHabilidad
        RelacionTipoHabilidad updatedRelacionTipoHabilidad = relacionTipoHabilidadRepository.findById(relacionTipoHabilidad.getId()).get();
        // Disconnect from session so that the updates on updatedRelacionTipoHabilidad are not directly saved in db
        em.detach(updatedRelacionTipoHabilidad);
        updatedRelacionTipoHabilidad
            .profundidad(UPDATED_PROFUNDIDAD);
        RelacionTipoHabilidadDTO relacionTipoHabilidadDTO = relacionTipoHabilidadMapper.toDto(updatedRelacionTipoHabilidad);

        restRelacionTipoHabilidadMockMvc.perform(put("/api/relacion-tipo-habilidads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relacionTipoHabilidadDTO)))
            .andExpect(status().isOk());

        // Validate the RelacionTipoHabilidad in the database
        List<RelacionTipoHabilidad> relacionTipoHabilidadList = relacionTipoHabilidadRepository.findAll();
        assertThat(relacionTipoHabilidadList).hasSize(databaseSizeBeforeUpdate);
        RelacionTipoHabilidad testRelacionTipoHabilidad = relacionTipoHabilidadList.get(relacionTipoHabilidadList.size() - 1);
        assertThat(testRelacionTipoHabilidad.getProfundidad()).isEqualTo(UPDATED_PROFUNDIDAD);
    }

    @Test
    @Transactional
    public void updateNonExistingRelacionTipoHabilidad() throws Exception {
        int databaseSizeBeforeUpdate = relacionTipoHabilidadRepository.findAll().size();

        // Create the RelacionTipoHabilidad
        RelacionTipoHabilidadDTO relacionTipoHabilidadDTO = relacionTipoHabilidadMapper.toDto(relacionTipoHabilidad);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRelacionTipoHabilidadMockMvc.perform(put("/api/relacion-tipo-habilidads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relacionTipoHabilidadDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RelacionTipoHabilidad in the database
        List<RelacionTipoHabilidad> relacionTipoHabilidadList = relacionTipoHabilidadRepository.findAll();
        assertThat(relacionTipoHabilidadList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRelacionTipoHabilidad() throws Exception {
        // Initialize the database
        relacionTipoHabilidadRepository.saveAndFlush(relacionTipoHabilidad);

        int databaseSizeBeforeDelete = relacionTipoHabilidadRepository.findAll().size();

        // Delete the relacionTipoHabilidad
        restRelacionTipoHabilidadMockMvc.perform(delete("/api/relacion-tipo-habilidads/{id}", relacionTipoHabilidad.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RelacionTipoHabilidad> relacionTipoHabilidadList = relacionTipoHabilidadRepository.findAll();
        assertThat(relacionTipoHabilidadList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
