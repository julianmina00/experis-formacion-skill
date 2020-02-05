package com.experis.formacion.alexa.poc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ContenidoCursoMapperTest {

    private ContenidoCursoMapper contenidoCursoMapper;

    @BeforeEach
    public void setUp() {
        contenidoCursoMapper = new ContenidoCursoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(contenidoCursoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(contenidoCursoMapper.fromId(null)).isNull();
    }
}
