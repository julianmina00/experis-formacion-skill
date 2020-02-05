package com.experis.formacion.alexa.poc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.experis.formacion.alexa.poc.web.rest.TestUtil;

public class PerfilPlanFormativoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PerfilPlanFormativoDTO.class);
        PerfilPlanFormativoDTO perfilPlanFormativoDTO1 = new PerfilPlanFormativoDTO();
        perfilPlanFormativoDTO1.setId(1L);
        PerfilPlanFormativoDTO perfilPlanFormativoDTO2 = new PerfilPlanFormativoDTO();
        assertThat(perfilPlanFormativoDTO1).isNotEqualTo(perfilPlanFormativoDTO2);
        perfilPlanFormativoDTO2.setId(perfilPlanFormativoDTO1.getId());
        assertThat(perfilPlanFormativoDTO1).isEqualTo(perfilPlanFormativoDTO2);
        perfilPlanFormativoDTO2.setId(2L);
        assertThat(perfilPlanFormativoDTO1).isNotEqualTo(perfilPlanFormativoDTO2);
        perfilPlanFormativoDTO1.setId(null);
        assertThat(perfilPlanFormativoDTO1).isNotEqualTo(perfilPlanFormativoDTO2);
    }
}
