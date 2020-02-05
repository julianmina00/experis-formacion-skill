package com.experis.formacion.alexa.poc.web.rest;

import com.experis.formacion.alexa.poc.ExperisFormacionApp;
import com.experis.formacion.alexa.poc.domain.HabilidadUsuario;
import com.experis.formacion.alexa.poc.repository.HabilidadUsuarioRepository;
import com.experis.formacion.alexa.poc.service.HabilidadUsuarioService;
import com.experis.formacion.alexa.poc.service.dto.HabilidadUsuarioDTO;
import com.experis.formacion.alexa.poc.service.mapper.HabilidadUsuarioMapper;
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
 * Integration tests for the {@link HabilidadUsuarioResource} REST controller.
 */
@SpringBootTest(classes = ExperisFormacionApp.class)
public class HabilidadUsuarioResourceIT {

    @Autowired
    private HabilidadUsuarioRepository habilidadUsuarioRepository;

    @Autowired
    private HabilidadUsuarioMapper habilidadUsuarioMapper;

    @Autowired
    private HabilidadUsuarioService habilidadUsuarioService;

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

    private MockMvc restHabilidadUsuarioMockMvc;

    private HabilidadUsuario habilidadUsuario;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HabilidadUsuarioResource habilidadUsuarioResource = new HabilidadUsuarioResource(habilidadUsuarioService);
        this.restHabilidadUsuarioMockMvc = MockMvcBuilders.standaloneSetup(habilidadUsuarioResource)
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
    public static HabilidadUsuario createEntity(EntityManager em) {
        HabilidadUsuario habilidadUsuario = new HabilidadUsuario();
        return habilidadUsuario;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HabilidadUsuario createUpdatedEntity(EntityManager em) {
        HabilidadUsuario habilidadUsuario = new HabilidadUsuario();
        return habilidadUsuario;
    }

    @BeforeEach
    public void initTest() {
        habilidadUsuario = createEntity(em);
    }

    @Test
    @Transactional
    public void createHabilidadUsuario() throws Exception {
        int databaseSizeBeforeCreate = habilidadUsuarioRepository.findAll().size();

        // Create the HabilidadUsuario
        HabilidadUsuarioDTO habilidadUsuarioDTO = habilidadUsuarioMapper.toDto(habilidadUsuario);
        restHabilidadUsuarioMockMvc.perform(post("/api/habilidad-usuarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(habilidadUsuarioDTO)))
            .andExpect(status().isCreated());

        // Validate the HabilidadUsuario in the database
        List<HabilidadUsuario> habilidadUsuarioList = habilidadUsuarioRepository.findAll();
        assertThat(habilidadUsuarioList).hasSize(databaseSizeBeforeCreate + 1);
        HabilidadUsuario testHabilidadUsuario = habilidadUsuarioList.get(habilidadUsuarioList.size() - 1);
    }

    @Test
    @Transactional
    public void createHabilidadUsuarioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = habilidadUsuarioRepository.findAll().size();

        // Create the HabilidadUsuario with an existing ID
        habilidadUsuario.setId(1L);
        HabilidadUsuarioDTO habilidadUsuarioDTO = habilidadUsuarioMapper.toDto(habilidadUsuario);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHabilidadUsuarioMockMvc.perform(post("/api/habilidad-usuarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(habilidadUsuarioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the HabilidadUsuario in the database
        List<HabilidadUsuario> habilidadUsuarioList = habilidadUsuarioRepository.findAll();
        assertThat(habilidadUsuarioList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllHabilidadUsuarios() throws Exception {
        // Initialize the database
        habilidadUsuarioRepository.saveAndFlush(habilidadUsuario);

        // Get all the habilidadUsuarioList
        restHabilidadUsuarioMockMvc.perform(get("/api/habilidad-usuarios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(habilidadUsuario.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getHabilidadUsuario() throws Exception {
        // Initialize the database
        habilidadUsuarioRepository.saveAndFlush(habilidadUsuario);

        // Get the habilidadUsuario
        restHabilidadUsuarioMockMvc.perform(get("/api/habilidad-usuarios/{id}", habilidadUsuario.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(habilidadUsuario.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingHabilidadUsuario() throws Exception {
        // Get the habilidadUsuario
        restHabilidadUsuarioMockMvc.perform(get("/api/habilidad-usuarios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHabilidadUsuario() throws Exception {
        // Initialize the database
        habilidadUsuarioRepository.saveAndFlush(habilidadUsuario);

        int databaseSizeBeforeUpdate = habilidadUsuarioRepository.findAll().size();

        // Update the habilidadUsuario
        HabilidadUsuario updatedHabilidadUsuario = habilidadUsuarioRepository.findById(habilidadUsuario.getId()).get();
        // Disconnect from session so that the updates on updatedHabilidadUsuario are not directly saved in db
        em.detach(updatedHabilidadUsuario);
        HabilidadUsuarioDTO habilidadUsuarioDTO = habilidadUsuarioMapper.toDto(updatedHabilidadUsuario);

        restHabilidadUsuarioMockMvc.perform(put("/api/habilidad-usuarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(habilidadUsuarioDTO)))
            .andExpect(status().isOk());

        // Validate the HabilidadUsuario in the database
        List<HabilidadUsuario> habilidadUsuarioList = habilidadUsuarioRepository.findAll();
        assertThat(habilidadUsuarioList).hasSize(databaseSizeBeforeUpdate);
        HabilidadUsuario testHabilidadUsuario = habilidadUsuarioList.get(habilidadUsuarioList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingHabilidadUsuario() throws Exception {
        int databaseSizeBeforeUpdate = habilidadUsuarioRepository.findAll().size();

        // Create the HabilidadUsuario
        HabilidadUsuarioDTO habilidadUsuarioDTO = habilidadUsuarioMapper.toDto(habilidadUsuario);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHabilidadUsuarioMockMvc.perform(put("/api/habilidad-usuarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(habilidadUsuarioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the HabilidadUsuario in the database
        List<HabilidadUsuario> habilidadUsuarioList = habilidadUsuarioRepository.findAll();
        assertThat(habilidadUsuarioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteHabilidadUsuario() throws Exception {
        // Initialize the database
        habilidadUsuarioRepository.saveAndFlush(habilidadUsuario);

        int databaseSizeBeforeDelete = habilidadUsuarioRepository.findAll().size();

        // Delete the habilidadUsuario
        restHabilidadUsuarioMockMvc.perform(delete("/api/habilidad-usuarios/{id}", habilidadUsuario.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<HabilidadUsuario> habilidadUsuarioList = habilidadUsuarioRepository.findAll();
        assertThat(habilidadUsuarioList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
