package com.experis.formacion.alexa.poc.web.rest;

import com.experis.formacion.alexa.poc.ExperisFormacionApp;
import com.experis.formacion.alexa.poc.domain.CursoPlanFormativo;
import com.experis.formacion.alexa.poc.repository.CursoPlanFormativoRepository;
import com.experis.formacion.alexa.poc.service.CursoPlanFormativoService;
import com.experis.formacion.alexa.poc.service.dto.CursoPlanFormativoDTO;
import com.experis.formacion.alexa.poc.service.mapper.CursoPlanFormativoMapper;
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
 * Integration tests for the {@link CursoPlanFormativoResource} REST controller.
 */
@SpringBootTest(classes = ExperisFormacionApp.class)
public class CursoPlanFormativoResourceIT {

    @Autowired
    private CursoPlanFormativoRepository cursoPlanFormativoRepository;

    @Autowired
    private CursoPlanFormativoMapper cursoPlanFormativoMapper;

    @Autowired
    private CursoPlanFormativoService cursoPlanFormativoService;

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

    private MockMvc restCursoPlanFormativoMockMvc;

    private CursoPlanFormativo cursoPlanFormativo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CursoPlanFormativoResource cursoPlanFormativoResource = new CursoPlanFormativoResource(cursoPlanFormativoService);
        this.restCursoPlanFormativoMockMvc = MockMvcBuilders.standaloneSetup(cursoPlanFormativoResource)
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
    public static CursoPlanFormativo createEntity(EntityManager em) {
        CursoPlanFormativo cursoPlanFormativo = new CursoPlanFormativo();
        return cursoPlanFormativo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CursoPlanFormativo createUpdatedEntity(EntityManager em) {
        CursoPlanFormativo cursoPlanFormativo = new CursoPlanFormativo();
        return cursoPlanFormativo;
    }

    @BeforeEach
    public void initTest() {
        cursoPlanFormativo = createEntity(em);
    }

    @Test
    @Transactional
    public void createCursoPlanFormativo() throws Exception {
        int databaseSizeBeforeCreate = cursoPlanFormativoRepository.findAll().size();

        // Create the CursoPlanFormativo
        CursoPlanFormativoDTO cursoPlanFormativoDTO = cursoPlanFormativoMapper.toDto(cursoPlanFormativo);
        restCursoPlanFormativoMockMvc.perform(post("/api/curso-plan-formativos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cursoPlanFormativoDTO)))
            .andExpect(status().isCreated());

        // Validate the CursoPlanFormativo in the database
        List<CursoPlanFormativo> cursoPlanFormativoList = cursoPlanFormativoRepository.findAll();
        assertThat(cursoPlanFormativoList).hasSize(databaseSizeBeforeCreate + 1);
        CursoPlanFormativo testCursoPlanFormativo = cursoPlanFormativoList.get(cursoPlanFormativoList.size() - 1);
    }

    @Test
    @Transactional
    public void createCursoPlanFormativoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cursoPlanFormativoRepository.findAll().size();

        // Create the CursoPlanFormativo with an existing ID
        cursoPlanFormativo.setId(1L);
        CursoPlanFormativoDTO cursoPlanFormativoDTO = cursoPlanFormativoMapper.toDto(cursoPlanFormativo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCursoPlanFormativoMockMvc.perform(post("/api/curso-plan-formativos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cursoPlanFormativoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CursoPlanFormativo in the database
        List<CursoPlanFormativo> cursoPlanFormativoList = cursoPlanFormativoRepository.findAll();
        assertThat(cursoPlanFormativoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCursoPlanFormativos() throws Exception {
        // Initialize the database
        cursoPlanFormativoRepository.saveAndFlush(cursoPlanFormativo);

        // Get all the cursoPlanFormativoList
        restCursoPlanFormativoMockMvc.perform(get("/api/curso-plan-formativos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cursoPlanFormativo.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getCursoPlanFormativo() throws Exception {
        // Initialize the database
        cursoPlanFormativoRepository.saveAndFlush(cursoPlanFormativo);

        // Get the cursoPlanFormativo
        restCursoPlanFormativoMockMvc.perform(get("/api/curso-plan-formativos/{id}", cursoPlanFormativo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cursoPlanFormativo.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCursoPlanFormativo() throws Exception {
        // Get the cursoPlanFormativo
        restCursoPlanFormativoMockMvc.perform(get("/api/curso-plan-formativos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCursoPlanFormativo() throws Exception {
        // Initialize the database
        cursoPlanFormativoRepository.saveAndFlush(cursoPlanFormativo);

        int databaseSizeBeforeUpdate = cursoPlanFormativoRepository.findAll().size();

        // Update the cursoPlanFormativo
        CursoPlanFormativo updatedCursoPlanFormativo = cursoPlanFormativoRepository.findById(cursoPlanFormativo.getId()).get();
        // Disconnect from session so that the updates on updatedCursoPlanFormativo are not directly saved in db
        em.detach(updatedCursoPlanFormativo);
        CursoPlanFormativoDTO cursoPlanFormativoDTO = cursoPlanFormativoMapper.toDto(updatedCursoPlanFormativo);

        restCursoPlanFormativoMockMvc.perform(put("/api/curso-plan-formativos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cursoPlanFormativoDTO)))
            .andExpect(status().isOk());

        // Validate the CursoPlanFormativo in the database
        List<CursoPlanFormativo> cursoPlanFormativoList = cursoPlanFormativoRepository.findAll();
        assertThat(cursoPlanFormativoList).hasSize(databaseSizeBeforeUpdate);
        CursoPlanFormativo testCursoPlanFormativo = cursoPlanFormativoList.get(cursoPlanFormativoList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingCursoPlanFormativo() throws Exception {
        int databaseSizeBeforeUpdate = cursoPlanFormativoRepository.findAll().size();

        // Create the CursoPlanFormativo
        CursoPlanFormativoDTO cursoPlanFormativoDTO = cursoPlanFormativoMapper.toDto(cursoPlanFormativo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCursoPlanFormativoMockMvc.perform(put("/api/curso-plan-formativos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cursoPlanFormativoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CursoPlanFormativo in the database
        List<CursoPlanFormativo> cursoPlanFormativoList = cursoPlanFormativoRepository.findAll();
        assertThat(cursoPlanFormativoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCursoPlanFormativo() throws Exception {
        // Initialize the database
        cursoPlanFormativoRepository.saveAndFlush(cursoPlanFormativo);

        int databaseSizeBeforeDelete = cursoPlanFormativoRepository.findAll().size();

        // Delete the cursoPlanFormativo
        restCursoPlanFormativoMockMvc.perform(delete("/api/curso-plan-formativos/{id}", cursoPlanFormativo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CursoPlanFormativo> cursoPlanFormativoList = cursoPlanFormativoRepository.findAll();
        assertThat(cursoPlanFormativoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
