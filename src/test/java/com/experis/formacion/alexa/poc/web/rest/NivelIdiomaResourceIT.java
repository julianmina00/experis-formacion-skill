package com.experis.formacion.alexa.poc.web.rest;

import com.experis.formacion.alexa.poc.ExperisFormacionApp;
import com.experis.formacion.alexa.poc.domain.NivelIdioma;
import com.experis.formacion.alexa.poc.repository.NivelIdiomaRepository;
import com.experis.formacion.alexa.poc.service.NivelIdiomaService;
import com.experis.formacion.alexa.poc.service.dto.NivelIdiomaDTO;
import com.experis.formacion.alexa.poc.service.mapper.NivelIdiomaMapper;
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
 * Integration tests for the {@link NivelIdiomaResource} REST controller.
 */
@SpringBootTest(classes = ExperisFormacionApp.class)
public class NivelIdiomaResourceIT {

    private static final String DEFAULT_NIVEL = "AAAAAAAAAA";
    private static final String UPDATED_NIVEL = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    @Autowired
    private NivelIdiomaRepository nivelIdiomaRepository;

    @Autowired
    private NivelIdiomaMapper nivelIdiomaMapper;

    @Autowired
    private NivelIdiomaService nivelIdiomaService;

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

    private MockMvc restNivelIdiomaMockMvc;

    private NivelIdioma nivelIdioma;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NivelIdiomaResource nivelIdiomaResource = new NivelIdiomaResource(nivelIdiomaService);
        this.restNivelIdiomaMockMvc = MockMvcBuilders.standaloneSetup(nivelIdiomaResource)
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
    public static NivelIdioma createEntity(EntityManager em) {
        NivelIdioma nivelIdioma = new NivelIdioma()
            .nivel(DEFAULT_NIVEL)
            .descripcion(DEFAULT_DESCRIPCION);
        return nivelIdioma;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NivelIdioma createUpdatedEntity(EntityManager em) {
        NivelIdioma nivelIdioma = new NivelIdioma()
            .nivel(UPDATED_NIVEL)
            .descripcion(UPDATED_DESCRIPCION);
        return nivelIdioma;
    }

    @BeforeEach
    public void initTest() {
        nivelIdioma = createEntity(em);
    }

    @Test
    @Transactional
    public void createNivelIdioma() throws Exception {
        int databaseSizeBeforeCreate = nivelIdiomaRepository.findAll().size();

        // Create the NivelIdioma
        NivelIdiomaDTO nivelIdiomaDTO = nivelIdiomaMapper.toDto(nivelIdioma);
        restNivelIdiomaMockMvc.perform(post("/api/nivel-idiomas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nivelIdiomaDTO)))
            .andExpect(status().isCreated());

        // Validate the NivelIdioma in the database
        List<NivelIdioma> nivelIdiomaList = nivelIdiomaRepository.findAll();
        assertThat(nivelIdiomaList).hasSize(databaseSizeBeforeCreate + 1);
        NivelIdioma testNivelIdioma = nivelIdiomaList.get(nivelIdiomaList.size() - 1);
        assertThat(testNivelIdioma.getNivel()).isEqualTo(DEFAULT_NIVEL);
        assertThat(testNivelIdioma.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
    }

    @Test
    @Transactional
    public void createNivelIdiomaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nivelIdiomaRepository.findAll().size();

        // Create the NivelIdioma with an existing ID
        nivelIdioma.setId(1L);
        NivelIdiomaDTO nivelIdiomaDTO = nivelIdiomaMapper.toDto(nivelIdioma);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNivelIdiomaMockMvc.perform(post("/api/nivel-idiomas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nivelIdiomaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NivelIdioma in the database
        List<NivelIdioma> nivelIdiomaList = nivelIdiomaRepository.findAll();
        assertThat(nivelIdiomaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNivelIsRequired() throws Exception {
        int databaseSizeBeforeTest = nivelIdiomaRepository.findAll().size();
        // set the field null
        nivelIdioma.setNivel(null);

        // Create the NivelIdioma, which fails.
        NivelIdiomaDTO nivelIdiomaDTO = nivelIdiomaMapper.toDto(nivelIdioma);

        restNivelIdiomaMockMvc.perform(post("/api/nivel-idiomas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nivelIdiomaDTO)))
            .andExpect(status().isBadRequest());

        List<NivelIdioma> nivelIdiomaList = nivelIdiomaRepository.findAll();
        assertThat(nivelIdiomaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNivelIdiomas() throws Exception {
        // Initialize the database
        nivelIdiomaRepository.saveAndFlush(nivelIdioma);

        // Get all the nivelIdiomaList
        restNivelIdiomaMockMvc.perform(get("/api/nivel-idiomas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nivelIdioma.getId().intValue())))
            .andExpect(jsonPath("$.[*].nivel").value(hasItem(DEFAULT_NIVEL)))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)));
    }
    
    @Test
    @Transactional
    public void getNivelIdioma() throws Exception {
        // Initialize the database
        nivelIdiomaRepository.saveAndFlush(nivelIdioma);

        // Get the nivelIdioma
        restNivelIdiomaMockMvc.perform(get("/api/nivel-idiomas/{id}", nivelIdioma.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nivelIdioma.getId().intValue()))
            .andExpect(jsonPath("$.nivel").value(DEFAULT_NIVEL))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION));
    }

    @Test
    @Transactional
    public void getNonExistingNivelIdioma() throws Exception {
        // Get the nivelIdioma
        restNivelIdiomaMockMvc.perform(get("/api/nivel-idiomas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNivelIdioma() throws Exception {
        // Initialize the database
        nivelIdiomaRepository.saveAndFlush(nivelIdioma);

        int databaseSizeBeforeUpdate = nivelIdiomaRepository.findAll().size();

        // Update the nivelIdioma
        NivelIdioma updatedNivelIdioma = nivelIdiomaRepository.findById(nivelIdioma.getId()).get();
        // Disconnect from session so that the updates on updatedNivelIdioma are not directly saved in db
        em.detach(updatedNivelIdioma);
        updatedNivelIdioma
            .nivel(UPDATED_NIVEL)
            .descripcion(UPDATED_DESCRIPCION);
        NivelIdiomaDTO nivelIdiomaDTO = nivelIdiomaMapper.toDto(updatedNivelIdioma);

        restNivelIdiomaMockMvc.perform(put("/api/nivel-idiomas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nivelIdiomaDTO)))
            .andExpect(status().isOk());

        // Validate the NivelIdioma in the database
        List<NivelIdioma> nivelIdiomaList = nivelIdiomaRepository.findAll();
        assertThat(nivelIdiomaList).hasSize(databaseSizeBeforeUpdate);
        NivelIdioma testNivelIdioma = nivelIdiomaList.get(nivelIdiomaList.size() - 1);
        assertThat(testNivelIdioma.getNivel()).isEqualTo(UPDATED_NIVEL);
        assertThat(testNivelIdioma.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
    }

    @Test
    @Transactional
    public void updateNonExistingNivelIdioma() throws Exception {
        int databaseSizeBeforeUpdate = nivelIdiomaRepository.findAll().size();

        // Create the NivelIdioma
        NivelIdiomaDTO nivelIdiomaDTO = nivelIdiomaMapper.toDto(nivelIdioma);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNivelIdiomaMockMvc.perform(put("/api/nivel-idiomas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nivelIdiomaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NivelIdioma in the database
        List<NivelIdioma> nivelIdiomaList = nivelIdiomaRepository.findAll();
        assertThat(nivelIdiomaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNivelIdioma() throws Exception {
        // Initialize the database
        nivelIdiomaRepository.saveAndFlush(nivelIdioma);

        int databaseSizeBeforeDelete = nivelIdiomaRepository.findAll().size();

        // Delete the nivelIdioma
        restNivelIdiomaMockMvc.perform(delete("/api/nivel-idiomas/{id}", nivelIdioma.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NivelIdioma> nivelIdiomaList = nivelIdiomaRepository.findAll();
        assertThat(nivelIdiomaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
