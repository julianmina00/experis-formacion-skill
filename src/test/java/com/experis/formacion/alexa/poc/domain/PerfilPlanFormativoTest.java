package com.experis.formacion.alexa.poc.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.experis.formacion.alexa.poc.web.rest.TestUtil;

public class PerfilPlanFormativoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PerfilPlanFormativo.class);
        PerfilPlanFormativo perfilPlanFormativo1 = new PerfilPlanFormativo();
        perfilPlanFormativo1.setId(1L);
        PerfilPlanFormativo perfilPlanFormativo2 = new PerfilPlanFormativo();
        perfilPlanFormativo2.setId(perfilPlanFormativo1.getId());
        assertThat(perfilPlanFormativo1).isEqualTo(perfilPlanFormativo2);
        perfilPlanFormativo2.setId(2L);
        assertThat(perfilPlanFormativo1).isNotEqualTo(perfilPlanFormativo2);
        perfilPlanFormativo1.setId(null);
        assertThat(perfilPlanFormativo1).isNotEqualTo(perfilPlanFormativo2);
    }
}
