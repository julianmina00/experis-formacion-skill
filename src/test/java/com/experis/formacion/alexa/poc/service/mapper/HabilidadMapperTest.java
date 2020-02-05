package com.experis.formacion.alexa.poc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class HabilidadMapperTest {

    private HabilidadMapper habilidadMapper;

    @BeforeEach
    public void setUp() {
        habilidadMapper = new HabilidadMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(habilidadMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(habilidadMapper.fromId(null)).isNull();
    }
}
