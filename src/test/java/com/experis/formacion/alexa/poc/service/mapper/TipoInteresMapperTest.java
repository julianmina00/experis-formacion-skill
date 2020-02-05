package com.experis.formacion.alexa.poc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class TipoInteresMapperTest {

    private TipoInteresMapper tipoInteresMapper;

    @BeforeEach
    public void setUp() {
        tipoInteresMapper = new TipoInteresMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(tipoInteresMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(tipoInteresMapper.fromId(null)).isNull();
    }
}
