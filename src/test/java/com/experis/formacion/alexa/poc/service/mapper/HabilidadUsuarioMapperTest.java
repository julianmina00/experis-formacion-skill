package com.experis.formacion.alexa.poc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class HabilidadUsuarioMapperTest {

    private HabilidadUsuarioMapper habilidadUsuarioMapper;

    @BeforeEach
    public void setUp() {
        habilidadUsuarioMapper = new HabilidadUsuarioMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(habilidadUsuarioMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(habilidadUsuarioMapper.fromId(null)).isNull();
    }
}
