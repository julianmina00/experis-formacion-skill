package com.experis.formacion.alexa.poc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class CursoMapperTest {

    private CursoMapper cursoMapper;

    @BeforeEach
    public void setUp() {
        cursoMapper = new CursoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(cursoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(cursoMapper.fromId(null)).isNull();
    }
}
