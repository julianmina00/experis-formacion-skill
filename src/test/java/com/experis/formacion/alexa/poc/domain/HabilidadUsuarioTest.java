package com.experis.formacion.alexa.poc.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.experis.formacion.alexa.poc.web.rest.TestUtil;

public class HabilidadUsuarioTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HabilidadUsuario.class);
        HabilidadUsuario habilidadUsuario1 = new HabilidadUsuario();
        habilidadUsuario1.setId(1L);
        HabilidadUsuario habilidadUsuario2 = new HabilidadUsuario();
        habilidadUsuario2.setId(habilidadUsuario1.getId());
        assertThat(habilidadUsuario1).isEqualTo(habilidadUsuario2);
        habilidadUsuario2.setId(2L);
        assertThat(habilidadUsuario1).isNotEqualTo(habilidadUsuario2);
        habilidadUsuario1.setId(null);
        assertThat(habilidadUsuario1).isNotEqualTo(habilidadUsuario2);
    }
}
