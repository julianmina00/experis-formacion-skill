package com.experis.formacion.alexa.poc.web.rest;

import com.experis.formacion.alexa.poc.ExperisFormacionApp;
import com.experis.formacion.alexa.poc.domain.InteresUsuario;
import com.experis.formacion.alexa.poc.repository.InteresUsuarioRepository;
import com.experis.formacion.alexa.poc.service.InteresUsuarioService;
import com.experis.formacion.alexa.poc.service.dto.InteresUsuarioDTO;
import com.experis.formacion.alexa.poc.service.mapper.InteresUsuarioMapper;
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
 * Integration tests for the {@link InteresUsuarioResource} REST controller.
 */
@SpringBootTest(classes = ExperisFormacionApp.class)
public class InteresUsuarioResourceIT {

    @Autowired
    private InteresUsuarioRepository interesUsuarioRepository;

    @Autowired
    private InteresUsuarioMapper interesUsuarioMapper;

    @Autowired
    private InteresUsuarioService interesUsuarioService;

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

    private MockMvc restInteresUsuarioMockMvc;

    private InteresUsuario interesUsuario;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final InteresUsuarioResource interesUsuarioResource = new InteresUsuarioResource(interesUsuarioService);
        this.restInteresUsuarioMockMvc = MockMvcBuilders.standaloneSetup(interesUsuarioResource)
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
    public static InteresUsuario createEntity(EntityManager em) {
        InteresUsuario interesUsuario = new InteresUsuario();
        return interesUsuario;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InteresUsuario createUpdatedEntity(EntityManager em) {
        InteresUsuario interesUsuario = new InteresUsuario();
        return interesUsuario;
    }

    @BeforeEach
    public void initTest() {
        interesUsuario = createEntity(em);
    }

    @Test
    @Transactional
    public void createInteresUsuario() throws Exception {
        int databaseSizeBeforeCreate = interesUsuarioRepository.findAll().size();

        // Create the InteresUsuario
        InteresUsuarioDTO interesUsuarioDTO = interesUsuarioMapper.toDto(interesUsuario);
        restInteresUsuarioMockMvc.perform(post("/api/interes-usuarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(interesUsuarioDTO)))
            .andExpect(status().isCreated());

        // Validate the InteresUsuario in the database
        List<InteresUsuario> interesUsuarioList = interesUsuarioRepository.findAll();
        assertThat(interesUsuarioList).hasSize(databaseSizeBeforeCreate + 1);
        InteresUsuario testInteresUsuario = interesUsuarioList.get(interesUsuarioList.size() - 1);
    }

    @Test
    @Transactional
    public void createInteresUsuarioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = interesUsuarioRepository.findAll().size();

        // Create the InteresUsuario with an existing ID
        interesUsuario.setId(1L);
        InteresUsuarioDTO interesUsuarioDTO = interesUsuarioMapper.toDto(interesUsuario);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInteresUsuarioMockMvc.perform(post("/api/interes-usuarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(interesUsuarioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InteresUsuario in the database
        List<InteresUsuario> interesUsuarioList = interesUsuarioRepository.findAll();
        assertThat(interesUsuarioList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllInteresUsuarios() throws Exception {
        // Initialize the database
        interesUsuarioRepository.saveAndFlush(interesUsuario);

        // Get all the interesUsuarioList
        restInteresUsuarioMockMvc.perform(get("/api/interes-usuarios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(interesUsuario.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getInteresUsuario() throws Exception {
        // Initialize the database
        interesUsuarioRepository.saveAndFlush(interesUsuario);

        // Get the interesUsuario
        restInteresUsuarioMockMvc.perform(get("/api/interes-usuarios/{id}", interesUsuario.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(interesUsuario.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingInteresUsuario() throws Exception {
        // Get the interesUsuario
        restInteresUsuarioMockMvc.perform(get("/api/interes-usuarios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInteresUsuario() throws Exception {
        // Initialize the database
        interesUsuarioRepository.saveAndFlush(interesUsuario);

        int databaseSizeBeforeUpdate = interesUsuarioRepository.findAll().size();

        // Update the interesUsuario
        InteresUsuario updatedInteresUsuario = interesUsuarioRepository.findById(interesUsuario.getId()).get();
        // Disconnect from session so that the updates on updatedInteresUsuario are not directly saved in db
        em.detach(updatedInteresUsuario);
        InteresUsuarioDTO interesUsuarioDTO = interesUsuarioMapper.toDto(updatedInteresUsuario);

        restInteresUsuarioMockMvc.perform(put("/api/interes-usuarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(interesUsuarioDTO)))
            .andExpect(status().isOk());

        // Validate the InteresUsuario in the database
        List<InteresUsuario> interesUsuarioList = interesUsuarioRepository.findAll();
        assertThat(interesUsuarioList).hasSize(databaseSizeBeforeUpdate);
        InteresUsuario testInteresUsuario = interesUsuarioList.get(interesUsuarioList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingInteresUsuario() throws Exception {
        int databaseSizeBeforeUpdate = interesUsuarioRepository.findAll().size();

        // Create the InteresUsuario
        InteresUsuarioDTO interesUsuarioDTO = interesUsuarioMapper.toDto(interesUsuario);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInteresUsuarioMockMvc.perform(put("/api/interes-usuarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(interesUsuarioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InteresUsuario in the database
        List<InteresUsuario> interesUsuarioList = interesUsuarioRepository.findAll();
        assertThat(interesUsuarioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInteresUsuario() throws Exception {
        // Initialize the database
        interesUsuarioRepository.saveAndFlush(interesUsuario);

        int databaseSizeBeforeDelete = interesUsuarioRepository.findAll().size();

        // Delete the interesUsuario
        restInteresUsuarioMockMvc.perform(delete("/api/interes-usuarios/{id}", interesUsuario.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InteresUsuario> interesUsuarioList = interesUsuarioRepository.findAll();
        assertThat(interesUsuarioList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
