package com.experis.formacion.alexa.poc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class InteresUsuarioMapperTest {

    private InteresUsuarioMapper interesUsuarioMapper;

    @BeforeEach
    public void setUp() {
        interesUsuarioMapper = new InteresUsuarioMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(interesUsuarioMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(interesUsuarioMapper.fromId(null)).isNull();
    }
}
