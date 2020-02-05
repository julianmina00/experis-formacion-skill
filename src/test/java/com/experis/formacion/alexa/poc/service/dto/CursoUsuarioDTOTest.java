package com.experis.formacion.alexa.poc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.experis.formacion.alexa.poc.web.rest.TestUtil;

public class CursoUsuarioDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CursoUsuarioDTO.class);
        CursoUsuarioDTO cursoUsuarioDTO1 = new CursoUsuarioDTO();
        cursoUsuarioDTO1.setId(1L);
        CursoUsuarioDTO cursoUsuarioDTO2 = new CursoUsuarioDTO();
        assertThat(cursoUsuarioDTO1).isNotEqualTo(cursoUsuarioDTO2);
        cursoUsuarioDTO2.setId(cursoUsuarioDTO1.getId());
        assertThat(cursoUsuarioDTO1).isEqualTo(cursoUsuarioDTO2);
        cursoUsuarioDTO2.setId(2L);
        assertThat(cursoUsuarioDTO1).isNotEqualTo(cursoUsuarioDTO2);
        cursoUsuarioDTO1.setId(null);
        assertThat(cursoUsuarioDTO1).isNotEqualTo(cursoUsuarioDTO2);
    }
}
