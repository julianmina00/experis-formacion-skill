package com.experis.formacion.alexa.poc.web.rest;

import com.experis.formacion.alexa.poc.ExperisFormacionApp;
import com.experis.formacion.alexa.poc.domain.TipoHabilidad;
import com.experis.formacion.alexa.poc.repository.TipoHabilidadRepository;
import com.experis.formacion.alexa.poc.service.TipoHabilidadService;
import com.experis.formacion.alexa.poc.service.dto.TipoHabilidadDTO;
import com.experis.formacion.alexa.poc.service.mapper.TipoHabilidadMapper;
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
 * Integration tests for the {@link TipoHabilidadResource} REST controller.
 */
@SpringBootTest(classes = ExperisFormacionApp.class)
public class TipoHabilidadResourceIT {

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION_LARGA = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION_LARGA = "BBBBBBBBBB";

    @Autowired
    private TipoHabilidadRepository tipoHabilidadRepository;

    @Autowired
    private TipoHabilidadMapper tipoHabilidadMapper;

    @Autowired
    private TipoHabilidadService tipoHabilidadService;

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

    private MockMvc restTipoHabilidadMockMvc;

    private TipoHabilidad tipoHabilidad;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TipoHabilidadResource tipoHabilidadResource = new TipoHabilidadResource(tipoHabilidadService);
        this.restTipoHabilidadMockMvc = MockMvcBuilders.standaloneSetup(tipoHabilidadResource)
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
    public static TipoHabilidad createEntity(EntityManager em) {
        TipoHabilidad tipoHabilidad = new TipoHabilidad()
            .descripcion(DEFAULT_DESCRIPCION)
            .descripcionLarga(DEFAULT_DESCRIPCION_LARGA);
        return tipoHabilidad;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TipoHabilidad createUpdatedEntity(EntityManager em) {
        TipoHabilidad tipoHabilidad = new TipoHabilidad()
            .descripcion(UPDATED_DESCRIPCION)
            .descripcionLarga(UPDATED_DESCRIPCION_LARGA);
        return tipoHabilidad;
    }

    @BeforeEach
    public void initTest() {
        tipoHabilidad = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoHabilidad() throws Exception {
        int databaseSizeBeforeCreate = tipoHabilidadRepository.findAll().size();

        // Create the TipoHabilidad
        TipoHabilidadDTO tipoHabilidadDTO = tipoHabilidadMapper.toDto(tipoHabilidad);
        restTipoHabilidadMockMvc.perform(post("/api/tipo-habilidads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoHabilidadDTO)))
            .andExpect(status().isCreated());

        // Validate the TipoHabilidad in the database
        List<TipoHabilidad> tipoHabilidadList = tipoHabilidadRepository.findAll();
        assertThat(tipoHabilidadList).hasSize(databaseSizeBeforeCreate + 1);
        TipoHabilidad testTipoHabilidad = tipoHabilidadList.get(tipoHabilidadList.size() - 1);
        assertThat(testTipoHabilidad.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testTipoHabilidad.getDescripcionLarga()).isEqualTo(DEFAULT_DESCRIPCION_LARGA);
    }

    @Test
    @Transactional
    public void createTipoHabilidadWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoHabilidadRepository.findAll().size();

        // Create the TipoHabilidad with an existing ID
        tipoHabilidad.setId(1L);
        TipoHabilidadDTO tipoHabilidadDTO = tipoHabilidadMapper.toDto(tipoHabilidad);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoHabilidadMockMvc.perform(post("/api/tipo-habilidads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoHabilidadDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TipoHabilidad in the database
        List<TipoHabilidad> tipoHabilidadList = tipoHabilidadRepository.findAll();
        assertThat(tipoHabilidadList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDescripcionIsRequired() throws Exception {
        int databaseSizeBeforeTest = tipoHabilidadRepository.findAll().size();
        // set the field null
        tipoHabilidad.setDescripcion(null);

        // Create the TipoHabilidad, which fails.
        TipoHabilidadDTO tipoHabilidadDTO = tipoHabilidadMapper.toDto(tipoHabilidad);

        restTipoHabilidadMockMvc.perform(post("/api/tipo-habilidads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoHabilidadDTO)))
            .andExpect(status().isBadRequest());

        List<TipoHabilidad> tipoHabilidadList = tipoHabilidadRepository.findAll();
        assertThat(tipoHabilidadList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTipoHabilidads() throws Exception {
        // Initialize the database
        tipoHabilidadRepository.saveAndFlush(tipoHabilidad);

        // Get all the tipoHabilidadList
        restTipoHabilidadMockMvc.perform(get("/api/tipo-habilidads?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoHabilidad.getId().intValue())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].descripcionLarga").value(hasItem(DEFAULT_DESCRIPCION_LARGA)));
    }
    
    @Test
    @Transactional
    public void getTipoHabilidad() throws Exception {
        // Initialize the database
        tipoHabilidadRepository.saveAndFlush(tipoHabilidad);

        // Get the tipoHabilidad
        restTipoHabilidadMockMvc.perform(get("/api/tipo-habilidads/{id}", tipoHabilidad.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipoHabilidad.getId().intValue()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION))
            .andExpect(jsonPath("$.descripcionLarga").value(DEFAULT_DESCRIPCION_LARGA));
    }

    @Test
    @Transactional
    public void getNonExistingTipoHabilidad() throws Exception {
        // Get the tipoHabilidad
        restTipoHabilidadMockMvc.perform(get("/api/tipo-habilidads/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoHabilidad() throws Exception {
        // Initialize the database
        tipoHabilidadRepository.saveAndFlush(tipoHabilidad);

        int databaseSizeBeforeUpdate = tipoHabilidadRepository.findAll().size();

        // Update the tipoHabilidad
        TipoHabilidad updatedTipoHabilidad = tipoHabilidadRepository.findById(tipoHabilidad.getId()).get();
        // Disconnect from session so that the updates on updatedTipoHabilidad are not directly saved in db
        em.detach(updatedTipoHabilidad);
        updatedTipoHabilidad
            .descripcion(UPDATED_DESCRIPCION)
            .descripcionLarga(UPDATED_DESCRIPCION_LARGA);
        TipoHabilidadDTO tipoHabilidadDTO = tipoHabilidadMapper.toDto(updatedTipoHabilidad);

        restTipoHabilidadMockMvc.perform(put("/api/tipo-habilidads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoHabilidadDTO)))
            .andExpect(status().isOk());

        // Validate the TipoHabilidad in the database
        List<TipoHabilidad> tipoHabilidadList = tipoHabilidadRepository.findAll();
        assertThat(tipoHabilidadList).hasSize(databaseSizeBeforeUpdate);
        TipoHabilidad testTipoHabilidad = tipoHabilidadList.get(tipoHabilidadList.size() - 1);
        assertThat(testTipoHabilidad.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testTipoHabilidad.getDescripcionLarga()).isEqualTo(UPDATED_DESCRIPCION_LARGA);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoHabilidad() throws Exception {
        int databaseSizeBeforeUpdate = tipoHabilidadRepository.findAll().size();

        // Create the TipoHabilidad
        TipoHabilidadDTO tipoHabilidadDTO = tipoHabilidadMapper.toDto(tipoHabilidad);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipoHabilidadMockMvc.perform(put("/api/tipo-habilidads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoHabilidadDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TipoHabilidad in the database
        List<TipoHabilidad> tipoHabilidadList = tipoHabilidadRepository.findAll();
        assertThat(tipoHabilidadList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTipoHabilidad() throws Exception {
        // Initialize the database
        tipoHabilidadRepository.saveAndFlush(tipoHabilidad);

        int databaseSizeBeforeDelete = tipoHabilidadRepository.findAll().size();

        // Delete the tipoHabilidad
        restTipoHabilidadMockMvc.perform(delete("/api/tipo-habilidads/{id}", tipoHabilidad.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TipoHabilidad> tipoHabilidadList = tipoHabilidadRepository.findAll();
        assertThat(tipoHabilidadList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
