package com.experis.formacion.alexa.poc.web.rest;

import com.experis.formacion.alexa.poc.ExperisFormacionApp;
import com.experis.formacion.alexa.poc.domain.CursoUsuario;
import com.experis.formacion.alexa.poc.repository.CursoUsuarioRepository;
import com.experis.formacion.alexa.poc.service.CursoUsuarioService;
import com.experis.formacion.alexa.poc.service.dto.CursoUsuarioDTO;
import com.experis.formacion.alexa.poc.service.mapper.CursoUsuarioMapper;
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
 * Integration tests for the {@link CursoUsuarioResource} REST controller.
 */
@SpringBootTest(classes = ExperisFormacionApp.class)
public class CursoUsuarioResourceIT {

    @Autowired
    private CursoUsuarioRepository cursoUsuarioRepository;

    @Autowired
    private CursoUsuarioMapper cursoUsuarioMapper;

    @Autowired
    private CursoUsuarioService cursoUsuarioService;

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

    private MockMvc restCursoUsuarioMockMvc;

    private CursoUsuario cursoUsuario;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CursoUsuarioResource cursoUsuarioResource = new CursoUsuarioResource(cursoUsuarioService);
        this.restCursoUsuarioMockMvc = MockMvcBuilders.standaloneSetup(cursoUsuarioResource)
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
    public static CursoUsuario createEntity(EntityManager em) {
        CursoUsuario cursoUsuario = new CursoUsuario();
        return cursoUsuario;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CursoUsuario createUpdatedEntity(EntityManager em) {
        CursoUsuario cursoUsuario = new CursoUsuario();
        return cursoUsuario;
    }

    @BeforeEach
    public void initTest() {
        cursoUsuario = createEntity(em);
    }

    @Test
    @Transactional
    public void createCursoUsuario() throws Exception {
        int databaseSizeBeforeCreate = cursoUsuarioRepository.findAll().size();

        // Create the CursoUsuario
        CursoUsuarioDTO cursoUsuarioDTO = cursoUsuarioMapper.toDto(cursoUsuario);
        restCursoUsuarioMockMvc.perform(post("/api/curso-usuarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cursoUsuarioDTO)))
            .andExpect(status().isCreated());

        // Validate the CursoUsuario in the database
        List<CursoUsuario> cursoUsuarioList = cursoUsuarioRepository.findAll();
        assertThat(cursoUsuarioList).hasSize(databaseSizeBeforeCreate + 1);
        CursoUsuario testCursoUsuario = cursoUsuarioList.get(cursoUsuarioList.size() - 1);
    }

    @Test
    @Transactional
    public void createCursoUsuarioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cursoUsuarioRepository.findAll().size();

        // Create the CursoUsuario with an existing ID
        cursoUsuario.setId(1L);
        CursoUsuarioDTO cursoUsuarioDTO = cursoUsuarioMapper.toDto(cursoUsuario);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCursoUsuarioMockMvc.perform(post("/api/curso-usuarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cursoUsuarioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CursoUsuario in the database
        List<CursoUsuario> cursoUsuarioList = cursoUsuarioRepository.findAll();
        assertThat(cursoUsuarioList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCursoUsuarios() throws Exception {
        // Initialize the database
        cursoUsuarioRepository.saveAndFlush(cursoUsuario);

        // Get all the cursoUsuarioList
        restCursoUsuarioMockMvc.perform(get("/api/curso-usuarios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cursoUsuario.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getCursoUsuario() throws Exception {
        // Initialize the database
        cursoUsuarioRepository.saveAndFlush(cursoUsuario);

        // Get the cursoUsuario
        restCursoUsuarioMockMvc.perform(get("/api/curso-usuarios/{id}", cursoUsuario.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cursoUsuario.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCursoUsuario() throws Exception {
        // Get the cursoUsuario
        restCursoUsuarioMockMvc.perform(get("/api/curso-usuarios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCursoUsuario() throws Exception {
        // Initialize the database
        cursoUsuarioRepository.saveAndFlush(cursoUsuario);

        int databaseSizeBeforeUpdate = cursoUsuarioRepository.findAll().size();

        // Update the cursoUsuario
        CursoUsuario updatedCursoUsuario = cursoUsuarioRepository.findById(cursoUsuario.getId()).get();
        // Disconnect from session so that the updates on updatedCursoUsuario are not directly saved in db
        em.detach(updatedCursoUsuario);
        CursoUsuarioDTO cursoUsuarioDTO = cursoUsuarioMapper.toDto(updatedCursoUsuario);

        restCursoUsuarioMockMvc.perform(put("/api/curso-usuarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cursoUsuarioDTO)))
            .andExpect(status().isOk());

        // Validate the CursoUsuario in the database
        List<CursoUsuario> cursoUsuarioList = cursoUsuarioRepository.findAll();
        assertThat(cursoUsuarioList).hasSize(databaseSizeBeforeUpdate);
        CursoUsuario testCursoUsuario = cursoUsuarioList.get(cursoUsuarioList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingCursoUsuario() throws Exception {
        int databaseSizeBeforeUpdate = cursoUsuarioRepository.findAll().size();

        // Create the CursoUsuario
        CursoUsuarioDTO cursoUsuarioDTO = cursoUsuarioMapper.toDto(cursoUsuario);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCursoUsuarioMockMvc.perform(put("/api/curso-usuarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cursoUsuarioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CursoUsuario in the database
        List<CursoUsuario> cursoUsuarioList = cursoUsuarioRepository.findAll();
        assertThat(cursoUsuarioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCursoUsuario() throws Exception {
        // Initialize the database
        cursoUsuarioRepository.saveAndFlush(cursoUsuario);

        int databaseSizeBeforeDelete = cursoUsuarioRepository.findAll().size();

        // Delete the cursoUsuario
        restCursoUsuarioMockMvc.perform(delete("/api/curso-usuarios/{id}", cursoUsuario.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CursoUsuario> cursoUsuarioList = cursoUsuarioRepository.findAll();
        assertThat(cursoUsuarioList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
