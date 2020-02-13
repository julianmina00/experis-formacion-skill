package com.experis.formacion.alexa.poc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class RelacionTipoHabilidadMapperTest {

    private RelacionTipoHabilidadMapper relacionTipoHabilidadMapper;

    @BeforeEach
    public void setUp() {
        relacionTipoHabilidadMapper = new RelacionTipoHabilidadMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(relacionTipoHabilidadMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(relacionTipoHabilidadMapper.fromId(null)).isNull();
    }
}
