package com.experis.formacion.alexa.poc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class PlanFormativoMapperTest {

    private PlanFormativoMapper planFormativoMapper;

    @BeforeEach
    public void setUp() {
        planFormativoMapper = new PlanFormativoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(planFormativoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(planFormativoMapper.fromId(null)).isNull();
    }
}
