package com.experis.formacion.alexa.poc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class NivelIdiomaMapperTest {

    private NivelIdiomaMapper nivelIdiomaMapper;

    @BeforeEach
    public void setUp() {
        nivelIdiomaMapper = new NivelIdiomaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(nivelIdiomaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(nivelIdiomaMapper.fromId(null)).isNull();
    }
}
