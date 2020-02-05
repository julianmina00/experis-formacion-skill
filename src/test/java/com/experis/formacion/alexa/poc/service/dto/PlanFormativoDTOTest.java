package com.experis.formacion.alexa.poc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.experis.formacion.alexa.poc.web.rest.TestUtil;

public class PlanFormativoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlanFormativoDTO.class);
        PlanFormativoDTO planFormativoDTO1 = new PlanFormativoDTO();
        planFormativoDTO1.setId(1L);
        PlanFormativoDTO planFormativoDTO2 = new PlanFormativoDTO();
        assertThat(planFormativoDTO1).isNotEqualTo(planFormativoDTO2);
        planFormativoDTO2.setId(planFormativoDTO1.getId());
        assertThat(planFormativoDTO1).isEqualTo(planFormativoDTO2);
        planFormativoDTO2.setId(2L);
        assertThat(planFormativoDTO1).isNotEqualTo(planFormativoDTO2);
        planFormativoDTO1.setId(null);
        assertThat(planFormativoDTO1).isNotEqualTo(planFormativoDTO2);
    }
}
