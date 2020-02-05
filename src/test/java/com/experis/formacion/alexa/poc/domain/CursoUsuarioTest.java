package com.experis.formacion.alexa.poc.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.experis.formacion.alexa.poc.web.rest.TestUtil;

public class CursoUsuarioTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CursoUsuario.class);
        CursoUsuario cursoUsuario1 = new CursoUsuario();
        cursoUsuario1.setId(1L);
        CursoUsuario cursoUsuario2 = new CursoUsuario();
        cursoUsuario2.setId(cursoUsuario1.getId());
        assertThat(cursoUsuario1).isEqualTo(cursoUsuario2);
        cursoUsuario2.setId(2L);
        assertThat(cursoUsuario1).isNotEqualTo(cursoUsuario2);
        cursoUsuario1.setId(null);
        assertThat(cursoUsuario1).isNotEqualTo(cursoUsuario2);
    }
}
