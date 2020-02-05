package com.experis.formacion.alexa.poc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class InteresMapperTest {

    private InteresMapper interesMapper;

    @BeforeEach
    public void setUp() {
        interesMapper = new InteresMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(interesMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(interesMapper.fromId(null)).isNull();
    }
}
