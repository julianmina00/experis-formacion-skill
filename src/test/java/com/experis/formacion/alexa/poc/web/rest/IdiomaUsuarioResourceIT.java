package com.experis.formacion.alexa.poc.web.rest;

import com.experis.formacion.alexa.poc.ExperisFormacionApp;
import com.experis.formacion.alexa.poc.domain.IdiomaUsuario;
import com.experis.formacion.alexa.poc.repository.IdiomaUsuarioRepository;
import com.experis.formacion.alexa.poc.service.IdiomaUsuarioService;
import com.experis.formacion.alexa.poc.service.dto.IdiomaUsuarioDTO;
import com.experis.formacion.alexa.poc.service.mapper.IdiomaUsuarioMapper;
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
 * Integration tests for the {@link IdiomaUsuarioResource} REST controller.
 */
@SpringBootTest(classes = ExperisFormacionApp.class)
public class IdiomaUsuarioResourceIT {

    private static final String DEFAULT_IDIOMA = "AAAAAAAAAA";
    private static final String UPDATED_IDIOMA = "BBBBBBBBBB";

    @Autowired
    private IdiomaUsuarioRepository idiomaUsuarioRepository;

    @Autowired
    private IdiomaUsuarioMapper idiomaUsuarioMapper;

    @Autowired
    private IdiomaUsuarioService idiomaUsuarioService;

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

    private MockMvc restIdiomaUsuarioMockMvc;

    private IdiomaUsuario idiomaUsuario;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final IdiomaUsuarioResource idiomaUsuarioResource = new IdiomaUsuarioResource(idiomaUsuarioService);
        this.restIdiomaUsuarioMockMvc = MockMvcBuilders.standaloneSetup(idiomaUsuarioResource)
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
    public static IdiomaUsuario createEntity(EntityManager em) {
        IdiomaUsuario idiomaUsuario = new IdiomaUsuario()
            .idioma(DEFAULT_IDIOMA);
        return idiomaUsuario;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static IdiomaUsuario createUpdatedEntity(EntityManager em) {
        IdiomaUsuario idiomaUsuario = new IdiomaUsuario()
            .idioma(UPDATED_IDIOMA);
        return idiomaUsuario;
    }

    @BeforeEach
    public void initTest() {
        idiomaUsuario = createEntity(em);
    }

    @Test
    @Transactional
    public void createIdiomaUsuario() throws Exception {
        int databaseSizeBeforeCreate = idiomaUsuarioRepository.findAll().size();

        // Create the IdiomaUsuario
        IdiomaUsuarioDTO idiomaUsuarioDTO = idiomaUsuarioMapper.toDto(idiomaUsuario);
        restIdiomaUsuarioMockMvc.perform(post("/api/idioma-usuarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(idiomaUsuarioDTO)))
            .andExpect(status().isCreated());

        // Validate the IdiomaUsuario in the database
        List<IdiomaUsuario> idiomaUsuarioList = idiomaUsuarioRepository.findAll();
        assertThat(idiomaUsuarioList).hasSize(databaseSizeBeforeCreate + 1);
        IdiomaUsuario testIdiomaUsuario = idiomaUsuarioList.get(idiomaUsuarioList.size() - 1);
        assertThat(testIdiomaUsuario.getIdioma()).isEqualTo(DEFAULT_IDIOMA);
    }

    @Test
    @Transactional
    public void createIdiomaUsuarioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = idiomaUsuarioRepository.findAll().size();

        // Create the IdiomaUsuario with an existing ID
        idiomaUsuario.setId(1L);
        IdiomaUsuarioDTO idiomaUsuarioDTO = idiomaUsuarioMapper.toDto(idiomaUsuario);

        // An entity with an existing ID cannot be created, so this API call must fail
        restIdiomaUsuarioMockMvc.perform(post("/api/idioma-usuarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(idiomaUsuarioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the IdiomaUsuario in the database
        List<IdiomaUsuario> idiomaUsuarioList = idiomaUsuarioRepository.findAll();
        assertThat(idiomaUsuarioList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdiomaIsRequired() throws Exception {
        int databaseSizeBeforeTest = idiomaUsuarioRepository.findAll().size();
        // set the field null
        idiomaUsuario.setIdioma(null);

        // Create the IdiomaUsuario, which fails.
        IdiomaUsuarioDTO idiomaUsuarioDTO = idiomaUsuarioMapper.toDto(idiomaUsuario);

        restIdiomaUsuarioMockMvc.perform(post("/api/idioma-usuarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(idiomaUsuarioDTO)))
            .andExpect(status().isBadRequest());

        List<IdiomaUsuario> idiomaUsuarioList = idiomaUsuarioRepository.findAll();
        assertThat(idiomaUsuarioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllIdiomaUsuarios() throws Exception {
        // Initialize the database
        idiomaUsuarioRepository.saveAndFlush(idiomaUsuario);

        // Get all the idiomaUsuarioList
        restIdiomaUsuarioMockMvc.perform(get("/api/idioma-usuarios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(idiomaUsuario.getId().intValue())))
            .andExpect(jsonPath("$.[*].idioma").value(hasItem(DEFAULT_IDIOMA)));
    }
    
    @Test
    @Transactional
    public void getIdiomaUsuario() throws Exception {
        // Initialize the database
        idiomaUsuarioRepository.saveAndFlush(idiomaUsuario);

        // Get the idiomaUsuario
        restIdiomaUsuarioMockMvc.perform(get("/api/idioma-usuarios/{id}", idiomaUsuario.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(idiomaUsuario.getId().intValue()))
            .andExpect(jsonPath("$.idioma").value(DEFAULT_IDIOMA));
    }

    @Test
    @Transactional
    public void getNonExistingIdiomaUsuario() throws Exception {
        // Get the idiomaUsuario
        restIdiomaUsuarioMockMvc.perform(get("/api/idioma-usuarios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateIdiomaUsuario() throws Exception {
        // Initialize the database
        idiomaUsuarioRepository.saveAndFlush(idiomaUsuario);

        int databaseSizeBeforeUpdate = idiomaUsuarioRepository.findAll().size();

        // Update the idiomaUsuario
        IdiomaUsuario updatedIdiomaUsuario = idiomaUsuarioRepository.findById(idiomaUsuario.getId()).get();
        // Disconnect from session so that the updates on updatedIdiomaUsuario are not directly saved in db
        em.detach(updatedIdiomaUsuario);
        updatedIdiomaUsuario
            .idioma(UPDATED_IDIOMA);
        IdiomaUsuarioDTO idiomaUsuarioDTO = idiomaUsuarioMapper.toDto(updatedIdiomaUsuario);

        restIdiomaUsuarioMockMvc.perform(put("/api/idioma-usuarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(idiomaUsuarioDTO)))
            .andExpect(status().isOk());

        // Validate the IdiomaUsuario in the database
        List<IdiomaUsuario> idiomaUsuarioList = idiomaUsuarioRepository.findAll();
        assertThat(idiomaUsuarioList).hasSize(databaseSizeBeforeUpdate);
        IdiomaUsuario testIdiomaUsuario = idiomaUsuarioList.get(idiomaUsuarioList.size() - 1);
        assertThat(testIdiomaUsuario.getIdioma()).isEqualTo(UPDATED_IDIOMA);
    }

    @Test
    @Transactional
    public void updateNonExistingIdiomaUsuario() throws Exception {
        int databaseSizeBeforeUpdate = idiomaUsuarioRepository.findAll().size();

        // Create the IdiomaUsuario
        IdiomaUsuarioDTO idiomaUsuarioDTO = idiomaUsuarioMapper.toDto(idiomaUsuario);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIdiomaUsuarioMockMvc.perform(put("/api/idioma-usuarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(idiomaUsuarioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the IdiomaUsuario in the database
        List<IdiomaUsuario> idiomaUsuarioList = idiomaUsuarioRepository.findAll();
        assertThat(idiomaUsuarioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteIdiomaUsuario() throws Exception {
        // Initialize the database
        idiomaUsuarioRepository.saveAndFlush(idiomaUsuario);

        int databaseSizeBeforeDelete = idiomaUsuarioRepository.findAll().size();

        // Delete the idiomaUsuario
        restIdiomaUsuarioMockMvc.perform(delete("/api/idioma-usuarios/{id}", idiomaUsuario.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<IdiomaUsuario> idiomaUsuarioList = idiomaUsuarioRepository.findAll();
        assertThat(idiomaUsuarioList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
