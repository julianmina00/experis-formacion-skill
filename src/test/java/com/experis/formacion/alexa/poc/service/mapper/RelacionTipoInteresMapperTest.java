package com.experis.formacion.alexa.poc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class RelacionTipoInteresMapperTest {

    private RelacionTipoInteresMapper relacionTipoInteresMapper;

    @BeforeEach
    public void setUp() {
        relacionTipoInteresMapper = new RelacionTipoInteresMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(relacionTipoInteresMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(relacionTipoInteresMapper.fromId(null)).isNull();
    }
}
