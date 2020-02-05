package com.experis.formacion.alexa.poc.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.experis.formacion.alexa.poc.web.rest.TestUtil;

public class PlanFormativoUsuarioTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlanFormativoUsuario.class);
        PlanFormativoUsuario planFormativoUsuario1 = new PlanFormativoUsuario();
        planFormativoUsuario1.setId(1L);
        PlanFormativoUsuario planFormativoUsuario2 = new PlanFormativoUsuario();
        planFormativoUsuario2.setId(planFormativoUsuario1.getId());
        assertThat(planFormativoUsuario1).isEqualTo(planFormativoUsuario2);
        planFormativoUsuario2.setId(2L);
        assertThat(planFormativoUsuario1).isNotEqualTo(planFormativoUsuario2);
        planFormativoUsuario1.setId(null);
        assertThat(planFormativoUsuario1).isNotEqualTo(planFormativoUsuario2);
    }
}
