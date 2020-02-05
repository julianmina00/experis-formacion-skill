package com.experis.formacion.alexa.poc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class CursoPlanFormativoMapperTest {

    private CursoPlanFormativoMapper cursoPlanFormativoMapper;

    @BeforeEach
    public void setUp() {
        cursoPlanFormativoMapper = new CursoPlanFormativoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(cursoPlanFormativoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(cursoPlanFormativoMapper.fromId(null)).isNull();
    }
}
