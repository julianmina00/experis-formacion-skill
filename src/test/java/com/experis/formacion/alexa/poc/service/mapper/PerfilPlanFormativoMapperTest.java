package com.experis.formacion.alexa.poc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class PerfilPlanFormativoMapperTest {

    private PerfilPlanFormativoMapper perfilPlanFormativoMapper;

    @BeforeEach
    public void setUp() {
        perfilPlanFormativoMapper = new PerfilPlanFormativoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(perfilPlanFormativoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(perfilPlanFormativoMapper.fromId(null)).isNull();
    }
}
