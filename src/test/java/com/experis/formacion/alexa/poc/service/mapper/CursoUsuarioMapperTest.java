package com.experis.formacion.alexa.poc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class CursoUsuarioMapperTest {

    private CursoUsuarioMapper cursoUsuarioMapper;

    @BeforeEach
    public void setUp() {
        cursoUsuarioMapper = new CursoUsuarioMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(cursoUsuarioMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(cursoUsuarioMapper.fromId(null)).isNull();
    }
}
