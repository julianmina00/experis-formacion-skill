package com.experis.formacion.alexa.poc.web.rest;

import com.experis.formacion.alexa.poc.ExperisFormacionApp;
import com.experis.formacion.alexa.poc.domain.Habilidad;
import com.experis.formacion.alexa.poc.repository.HabilidadRepository;
import com.experis.formacion.alexa.poc.service.HabilidadService;
import com.experis.formacion.alexa.poc.service.dto.HabilidadDTO;
import com.experis.formacion.alexa.poc.service.mapper.HabilidadMapper;
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
 * Integration tests for the {@link HabilidadResource} REST controller.
 */
@SpringBootTest(classes = ExperisFormacionApp.class)
public class HabilidadResourceIT {

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION_LARGA = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION_LARGA = "BBBBBBBBBB";

    @Autowired
    private HabilidadRepository habilidadRepository;

    @Autowired
    private HabilidadMapper habilidadMapper;

    @Autowired
    private HabilidadService habilidadService;

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

    private MockMvc restHabilidadMockMvc;

    private Habilidad habilidad;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HabilidadResource habilidadResource = new HabilidadResource(habilidadService);
        this.restHabilidadMockMvc = MockMvcBuilders.standaloneSetup(habilidadResource)
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
    public static Habilidad createEntity(EntityManager em) {
        Habilidad habilidad = new Habilidad()
            .descripcion(DEFAULT_DESCRIPCION)
            .descripcionLarga(DEFAULT_DESCRIPCION_LARGA);
        return habilidad;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Habilidad createUpdatedEntity(EntityManager em) {
        Habilidad habilidad = new Habilidad()
            .descripcion(UPDATED_DESCRIPCION)
            .descripcionLarga(UPDATED_DESCRIPCION_LARGA);
        return habilidad;
    }

    @BeforeEach
    public void initTest() {
        habilidad = createEntity(em);
    }

    @Test
    @Transactional
    public void createHabilidad() throws Exception {
        int databaseSizeBeforeCreate = habilidadRepository.findAll().size();

        // Create the Habilidad
        HabilidadDTO habilidadDTO = habilidadMapper.toDto(habilidad);
        restHabilidadMockMvc.perform(post("/api/habilidads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(habilidadDTO)))
            .andExpect(status().isCreated());

        // Validate the Habilidad in the database
        List<Habilidad> habilidadList = habilidadRepository.findAll();
        assertThat(habilidadList).hasSize(databaseSizeBeforeCreate + 1);
        Habilidad testHabilidad = habilidadList.get(habilidadList.size() - 1);
        assertThat(testHabilidad.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testHabilidad.getDescripcionLarga()).isEqualTo(DEFAULT_DESCRIPCION_LARGA);
    }

    @Test
    @Transactional
    public void createHabilidadWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = habilidadRepository.findAll().size();

        // Create the Habilidad with an existing ID
        habilidad.setId(1L);
        HabilidadDTO habilidadDTO = habilidadMapper.toDto(habilidad);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHabilidadMockMvc.perform(post("/api/habilidads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(habilidadDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Habilidad in the database
        List<Habilidad> habilidadList = habilidadRepository.findAll();
        assertThat(habilidadList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDescripcionIsRequired() throws Exception {
        int databaseSizeBeforeTest = habilidadRepository.findAll().size();
        // set the field null
        habilidad.setDescripcion(null);

        // Create the Habilidad, which fails.
        HabilidadDTO habilidadDTO = habilidadMapper.toDto(habilidad);

        restHabilidadMockMvc.perform(post("/api/habilidads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(habilidadDTO)))
            .andExpect(status().isBadRequest());

        List<Habilidad> habilidadList = habilidadRepository.findAll();
        assertThat(habilidadList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllHabilidads() throws Exception {
        // Initialize the database
        habilidadRepository.saveAndFlush(habilidad);

        // Get all the habilidadList
        restHabilidadMockMvc.perform(get("/api/habilidads?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(habilidad.getId().intValue())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].descripcionLarga").value(hasItem(DEFAULT_DESCRIPCION_LARGA)));
    }
    
    @Test
    @Transactional
    public void getHabilidad() throws Exception {
        // Initialize the database
        habilidadRepository.saveAndFlush(habilidad);

        // Get the habilidad
        restHabilidadMockMvc.perform(get("/api/habilidads/{id}", habilidad.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(habilidad.getId().intValue()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION))
            .andExpect(jsonPath("$.descripcionLarga").value(DEFAULT_DESCRIPCION_LARGA));
    }

    @Test
    @Transactional
    public void getNonExistingHabilidad() throws Exception {
        // Get the habilidad
        restHabilidadMockMvc.perform(get("/api/habilidads/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHabilidad() throws Exception {
        // Initialize the database
        habilidadRepository.saveAndFlush(habilidad);

        int databaseSizeBeforeUpdate = habilidadRepository.findAll().size();

        // Update the habilidad
        Habilidad updatedHabilidad = habilidadRepository.findById(habilidad.getId()).get();
        // Disconnect from session so that the updates on updatedHabilidad are not directly saved in db
        em.detach(updatedHabilidad);
        updatedHabilidad
            .descripcion(UPDATED_DESCRIPCION)
            .descripcionLarga(UPDATED_DESCRIPCION_LARGA);
        HabilidadDTO habilidadDTO = habilidadMapper.toDto(updatedHabilidad);

        restHabilidadMockMvc.perform(put("/api/habilidads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(habilidadDTO)))
            .andExpect(status().isOk());

        // Validate the Habilidad in the database
        List<Habilidad> habilidadList = habilidadRepository.findAll();
        assertThat(habilidadList).hasSize(databaseSizeBeforeUpdate);
        Habilidad testHabilidad = habilidadList.get(habilidadList.size() - 1);
        assertThat(testHabilidad.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testHabilidad.getDescripcionLarga()).isEqualTo(UPDATED_DESCRIPCION_LARGA);
    }

    @Test
    @Transactional
    public void updateNonExistingHabilidad() throws Exception {
        int databaseSizeBeforeUpdate = habilidadRepository.findAll().size();

        // Create the Habilidad
        HabilidadDTO habilidadDTO = habilidadMapper.toDto(habilidad);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHabilidadMockMvc.perform(put("/api/habilidads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(habilidadDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Habilidad in the database
        List<Habilidad> habilidadList = habilidadRepository.findAll();
        assertThat(habilidadList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteHabilidad() throws Exception {
        // Initialize the database
        habilidadRepository.saveAndFlush(habilidad);

        int databaseSizeBeforeDelete = habilidadRepository.findAll().size();

        // Delete the habilidad
        restHabilidadMockMvc.perform(delete("/api/habilidads/{id}", habilidad.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Habilidad> habilidadList = habilidadRepository.findAll();
        assertThat(habilidadList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
