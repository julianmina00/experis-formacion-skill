package com.experis.formacion.alexa.poc.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.experis.formacion.alexa.poc.web.rest.TestUtil;

public class InteresUsuarioTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InteresUsuario.class);
        InteresUsuario interesUsuario1 = new InteresUsuario();
        interesUsuario1.setId(1L);
        InteresUsuario interesUsuario2 = new InteresUsuario();
        interesUsuario2.setId(interesUsuario1.getId());
        assertThat(interesUsuario1).isEqualTo(interesUsuario2);
        interesUsuario2.setId(2L);
        assertThat(interesUsuario1).isNotEqualTo(interesUsuario2);
        interesUsuario1.setId(null);
        assertThat(interesUsuario1).isNotEqualTo(interesUsuario2);
    }
}
