package com.experis.formacion.alexa.poc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.experis.formacion.alexa.poc.web.rest.TestUtil;

public class PlanFormativoUsuarioDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlanFormativoUsuarioDTO.class);
        PlanFormativoUsuarioDTO planFormativoUsuarioDTO1 = new PlanFormativoUsuarioDTO();
        planFormativoUsuarioDTO1.setId(1L);
        PlanFormativoUsuarioDTO planFormativoUsuarioDTO2 = new PlanFormativoUsuarioDTO();
        assertThat(planFormativoUsuarioDTO1).isNotEqualTo(planFormativoUsuarioDTO2);
        planFormativoUsuarioDTO2.setId(planFormativoUsuarioDTO1.getId());
        assertThat(planFormativoUsuarioDTO1).isEqualTo(planFormativoUsuarioDTO2);
        planFormativoUsuarioDTO2.setId(2L);
        assertThat(planFormativoUsuarioDTO1).isNotEqualTo(planFormativoUsuarioDTO2);
        planFormativoUsuarioDTO1.setId(null);
        assertThat(planFormativoUsuarioDTO1).isNotEqualTo(planFormativoUsuarioDTO2);
    }
}
