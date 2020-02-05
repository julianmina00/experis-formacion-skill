package com.experis.formacion.alexa.poc.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.experis.formacion.alexa.poc.web.rest.TestUtil;

public class PlanFormativoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlanFormativo.class);
        PlanFormativo planFormativo1 = new PlanFormativo();
        planFormativo1.setId(1L);
        PlanFormativo planFormativo2 = new PlanFormativo();
        planFormativo2.setId(planFormativo1.getId());
        assertThat(planFormativo1).isEqualTo(planFormativo2);
        planFormativo2.setId(2L);
        assertThat(planFormativo1).isNotEqualTo(planFormativo2);
        planFormativo1.setId(null);
        assertThat(planFormativo1).isNotEqualTo(planFormativo2);
    }
}
