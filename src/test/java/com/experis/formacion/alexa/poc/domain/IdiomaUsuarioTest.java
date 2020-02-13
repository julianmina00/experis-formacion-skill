package com.experis.formacion.alexa.poc.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.experis.formacion.alexa.poc.web.rest.TestUtil;

public class IdiomaUsuarioTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(IdiomaUsuario.class);
        IdiomaUsuario idiomaUsuario1 = new IdiomaUsuario();
        idiomaUsuario1.setId(1L);
        IdiomaUsuario idiomaUsuario2 = new IdiomaUsuario();
        idiomaUsuario2.setId(idiomaUsuario1.getId());
        assertThat(idiomaUsuario1).isEqualTo(idiomaUsuario2);
        idiomaUsuario2.setId(2L);
        assertThat(idiomaUsuario1).isNotEqualTo(idiomaUsuario2);
        idiomaUsuario1.setId(null);
        assertThat(idiomaUsuario1).isNotEqualTo(idiomaUsuario2);
    }
}
