package com.experis.formacion.alexa.poc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class PlanFormativoUsuarioMapperTest {

    private PlanFormativoUsuarioMapper planFormativoUsuarioMapper;

    @BeforeEach
    public void setUp() {
        planFormativoUsuarioMapper = new PlanFormativoUsuarioMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(planFormativoUsuarioMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(planFormativoUsuarioMapper.fromId(null)).isNull();
    }
}
