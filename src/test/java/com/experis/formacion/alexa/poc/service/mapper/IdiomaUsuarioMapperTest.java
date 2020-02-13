package com.experis.formacion.alexa.poc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class IdiomaUsuarioMapperTest {

    private IdiomaUsuarioMapper idiomaUsuarioMapper;

    @BeforeEach
    public void setUp() {
        idiomaUsuarioMapper = new IdiomaUsuarioMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(idiomaUsuarioMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(idiomaUsuarioMapper.fromId(null)).isNull();
    }
}
