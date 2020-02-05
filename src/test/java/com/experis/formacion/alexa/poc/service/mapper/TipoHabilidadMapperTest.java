package com.experis.formacion.alexa.poc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class TipoHabilidadMapperTest {

    private TipoHabilidadMapper tipoHabilidadMapper;

    @BeforeEach
    public void setUp() {
        tipoHabilidadMapper = new TipoHabilidadMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(tipoHabilidadMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(tipoHabilidadMapper.fromId(null)).isNull();
    }
}
