package com.experis.formacion.alexa.poc.web.rest;

import com.experis.formacion.alexa.poc.ExperisFormacionApp;
import com.experis.formacion.alexa.poc.domain.Curso;
import com.experis.formacion.alexa.poc.repository.CursoRepository;
import com.experis.formacion.alexa.poc.service.CursoService;
import com.experis.formacion.alexa.poc.service.dto.CursoDTO;
import com.experis.formacion.alexa.poc.service.mapper.CursoMapper;
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
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static com.experis.formacion.alexa.poc.web.rest.TestUtil.sameInstant;
import static com.experis.formacion.alexa.poc.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CursoResource} REST controller.
 */
@SpringBootTest(classes = ExperisFormacionApp.class)
public class CursoResourceIT {

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION_LARGA = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION_LARGA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA_INICIO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_INICIO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_FECHA_FIN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_FIN = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_TELEMATICO_PRESENCIAL = "T";
    private static final String UPDATED_TELEMATICO_PRESENCIAL = "TB";

    private static final ZonedDateTime DEFAULT_HORA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_HORA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_UBICACION = "AAAAAAAAAA";
    private static final String UPDATED_UBICACION = "BBBBBBBBBB";

    private static final Long DEFAULT_NUMERO_HORAS = 1L;
    private static final Long UPDATED_NUMERO_HORAS = 2L;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CursoMapper cursoMapper;

    @Autowired
    private CursoService cursoService;

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

    private MockMvc restCursoMockMvc;

    private Curso curso;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CursoResource cursoResource = new CursoResource(cursoService);
        this.restCursoMockMvc = MockMvcBuilders.standaloneSetup(cursoResource)
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
    public static Curso createEntity(EntityManager em) {
        Curso curso = new Curso()
            .descripcion(DEFAULT_DESCRIPCION)
            .descripcionLarga(DEFAULT_DESCRIPCION_LARGA)
            .fechaInicio(DEFAULT_FECHA_INICIO)
            .fechaFin(DEFAULT_FECHA_FIN)
            .telematicoPresencial(DEFAULT_TELEMATICO_PRESENCIAL)
            .hora(DEFAULT_HORA)
            .ubicacion(DEFAULT_UBICACION)
            .numeroHoras(DEFAULT_NUMERO_HORAS);
        return curso;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Curso createUpdatedEntity(EntityManager em) {
        Curso curso = new Curso()
            .descripcion(UPDATED_DESCRIPCION)
            .descripcionLarga(UPDATED_DESCRIPCION_LARGA)
            .fechaInicio(UPDATED_FECHA_INICIO)
            .fechaFin(UPDATED_FECHA_FIN)
            .telematicoPresencial(UPDATED_TELEMATICO_PRESENCIAL)
            .hora(UPDATED_HORA)
            .ubicacion(UPDATED_UBICACION)
            .numeroHoras(UPDATED_NUMERO_HORAS);
        return curso;
    }

    @BeforeEach
    public void initTest() {
        curso = createEntity(em);
    }

    @Test
    @Transactional
    public void createCurso() throws Exception {
        int databaseSizeBeforeCreate = cursoRepository.findAll().size();

        // Create the Curso
        CursoDTO cursoDTO = cursoMapper.toDto(curso);
        restCursoMockMvc.perform(post("/api/cursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cursoDTO)))
            .andExpect(status().isCreated());

        // Validate the Curso in the database
        List<Curso> cursoList = cursoRepository.findAll();
        assertThat(cursoList).hasSize(databaseSizeBeforeCreate + 1);
        Curso testCurso = cursoList.get(cursoList.size() - 1);
        assertThat(testCurso.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testCurso.getDescripcionLarga()).isEqualTo(DEFAULT_DESCRIPCION_LARGA);
        assertThat(testCurso.getFechaInicio()).isEqualTo(DEFAULT_FECHA_INICIO);
        assertThat(testCurso.getFechaFin()).isEqualTo(DEFAULT_FECHA_FIN);
        assertThat(testCurso.getTelematicoPresencial()).isEqualTo(DEFAULT_TELEMATICO_PRESENCIAL);
        assertThat(testCurso.getHora()).isEqualTo(DEFAULT_HORA);
        assertThat(testCurso.getUbicacion()).isEqualTo(DEFAULT_UBICACION);
        assertThat(testCurso.getNumeroHoras()).isEqualTo(DEFAULT_NUMERO_HORAS);
    }

    @Test
    @Transactional
    public void createCursoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cursoRepository.findAll().size();

        // Create the Curso with an existing ID
        curso.setId(1L);
        CursoDTO cursoDTO = cursoMapper.toDto(curso);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCursoMockMvc.perform(post("/api/cursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cursoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Curso in the database
        List<Curso> cursoList = cursoRepository.findAll();
        assertThat(cursoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDescripcionIsRequired() throws Exception {
        int databaseSizeBeforeTest = cursoRepository.findAll().size();
        // set the field null
        curso.setDescripcion(null);

        // Create the Curso, which fails.
        CursoDTO cursoDTO = cursoMapper.toDto(curso);

        restCursoMockMvc.perform(post("/api/cursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cursoDTO)))
            .andExpect(status().isBadRequest());

        List<Curso> cursoList = cursoRepository.findAll();
        assertThat(cursoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFechaInicioIsRequired() throws Exception {
        int databaseSizeBeforeTest = cursoRepository.findAll().size();
        // set the field null
        curso.setFechaInicio(null);

        // Create the Curso, which fails.
        CursoDTO cursoDTO = cursoMapper.toDto(curso);

        restCursoMockMvc.perform(post("/api/cursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cursoDTO)))
            .andExpect(status().isBadRequest());

        List<Curso> cursoList = cursoRepository.findAll();
        assertThat(cursoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFechaFinIsRequired() throws Exception {
        int databaseSizeBeforeTest = cursoRepository.findAll().size();
        // set the field null
        curso.setFechaFin(null);

        // Create the Curso, which fails.
        CursoDTO cursoDTO = cursoMapper.toDto(curso);

        restCursoMockMvc.perform(post("/api/cursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cursoDTO)))
            .andExpect(status().isBadRequest());

        List<Curso> cursoList = cursoRepository.findAll();
        assertThat(cursoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTelematicoPresencialIsRequired() throws Exception {
        int databaseSizeBeforeTest = cursoRepository.findAll().size();
        // set the field null
        curso.setTelematicoPresencial(null);

        // Create the Curso, which fails.
        CursoDTO cursoDTO = cursoMapper.toDto(curso);

        restCursoMockMvc.perform(post("/api/cursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cursoDTO)))
            .andExpect(status().isBadRequest());

        List<Curso> cursoList = cursoRepository.findAll();
        assertThat(cursoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCursos() throws Exception {
        // Initialize the database
        cursoRepository.saveAndFlush(curso);

        // Get all the cursoList
        restCursoMockMvc.perform(get("/api/cursos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(curso.getId().intValue())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].descripcionLarga").value(hasItem(DEFAULT_DESCRIPCION_LARGA)))
            .andExpect(jsonPath("$.[*].fechaInicio").value(hasItem(DEFAULT_FECHA_INICIO.toString())))
            .andExpect(jsonPath("$.[*].fechaFin").value(hasItem(DEFAULT_FECHA_FIN.toString())))
            .andExpect(jsonPath("$.[*].telematicoPresencial").value(hasItem(DEFAULT_TELEMATICO_PRESENCIAL)))
            .andExpect(jsonPath("$.[*].hora").value(hasItem(sameInstant(DEFAULT_HORA))))
            .andExpect(jsonPath("$.[*].ubicacion").value(hasItem(DEFAULT_UBICACION)))
            .andExpect(jsonPath("$.[*].numeroHoras").value(hasItem(DEFAULT_NUMERO_HORAS.intValue())));
    }
    
    @Test
    @Transactional
    public void getCurso() throws Exception {
        // Initialize the database
        cursoRepository.saveAndFlush(curso);

        // Get the curso
        restCursoMockMvc.perform(get("/api/cursos/{id}", curso.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(curso.getId().intValue()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION))
            .andExpect(jsonPath("$.descripcionLarga").value(DEFAULT_DESCRIPCION_LARGA))
            .andExpect(jsonPath("$.fechaInicio").value(DEFAULT_FECHA_INICIO.toString()))
            .andExpect(jsonPath("$.fechaFin").value(DEFAULT_FECHA_FIN.toString()))
            .andExpect(jsonPath("$.telematicoPresencial").value(DEFAULT_TELEMATICO_PRESENCIAL))
            .andExpect(jsonPath("$.hora").value(sameInstant(DEFAULT_HORA)))
            .andExpect(jsonPath("$.ubicacion").value(DEFAULT_UBICACION))
            .andExpect(jsonPath("$.numeroHoras").value(DEFAULT_NUMERO_HORAS.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCurso() throws Exception {
        // Get the curso
        restCursoMockMvc.perform(get("/api/cursos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCurso() throws Exception {
        // Initialize the database
        cursoRepository.saveAndFlush(curso);

        int databaseSizeBeforeUpdate = cursoRepository.findAll().size();

        // Update the curso
        Curso updatedCurso = cursoRepository.findById(curso.getId()).get();
        // Disconnect from session so that the updates on updatedCurso are not directly saved in db
        em.detach(updatedCurso);
        updatedCurso
            .descripcion(UPDATED_DESCRIPCION)
            .descripcionLarga(UPDATED_DESCRIPCION_LARGA)
            .fechaInicio(UPDATED_FECHA_INICIO)
            .fechaFin(UPDATED_FECHA_FIN)
            .telematicoPresencial(UPDATED_TELEMATICO_PRESENCIAL)
            .hora(UPDATED_HORA)
            .ubicacion(UPDATED_UBICACION)
            .numeroHoras(UPDATED_NUMERO_HORAS);
        CursoDTO cursoDTO = cursoMapper.toDto(updatedCurso);

        restCursoMockMvc.perform(put("/api/cursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cursoDTO)))
            .andExpect(status().isOk());

        // Validate the Curso in the database
        List<Curso> cursoList = cursoRepository.findAll();
        assertThat(cursoList).hasSize(databaseSizeBeforeUpdate);
        Curso testCurso = cursoList.get(cursoList.size() - 1);
        assertThat(testCurso.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testCurso.getDescripcionLarga()).isEqualTo(UPDATED_DESCRIPCION_LARGA);
        assertThat(testCurso.getFechaInicio()).isEqualTo(UPDATED_FECHA_INICIO);
        assertThat(testCurso.getFechaFin()).isEqualTo(UPDATED_FECHA_FIN);
        assertThat(testCurso.getTelematicoPresencial()).isEqualTo(UPDATED_TELEMATICO_PRESENCIAL);
        assertThat(testCurso.getHora()).isEqualTo(UPDATED_HORA);
        assertThat(testCurso.getUbicacion()).isEqualTo(UPDATED_UBICACION);
        assertThat(testCurso.getNumeroHoras()).isEqualTo(UPDATED_NUMERO_HORAS);
    }

    @Test
    @Transactional
    public void updateNonExistingCurso() throws Exception {
        int databaseSizeBeforeUpdate = cursoRepository.findAll().size();

        // Create the Curso
        CursoDTO cursoDTO = cursoMapper.toDto(curso);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCursoMockMvc.perform(put("/api/cursos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cursoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Curso in the database
        List<Curso> cursoList = cursoRepository.findAll();
        assertThat(cursoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCurso() throws Exception {
        // Initialize the database
        cursoRepository.saveAndFlush(curso);

        int databaseSizeBeforeDelete = cursoRepository.findAll().size();

        // Delete the curso
        restCursoMockMvc.perform(delete("/api/cursos/{id}", curso.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Curso> cursoList = cursoRepository.findAll();
        assertThat(cursoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
