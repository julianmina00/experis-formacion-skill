package com.experis.formacion.alexa.poc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.experis.formacion.alexa.poc.web.rest.TestUtil;

public class UsuarioDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UsuarioDTO.class);
        UsuarioDTO usuarioDTO1 = new UsuarioDTO();
        usuarioDTO1.setId(1L);
        UsuarioDTO usuarioDTO2 = new UsuarioDTO();
        assertThat(usuarioDTO1).isNotEqualTo(usuarioDTO2);
        usuarioDTO2.setId(usuarioDTO1.getId());
        assertThat(usuarioDTO1).isEqualTo(usuarioDTO2);
        usuarioDTO2.setId(2L);
        assertThat(usuarioDTO1).isNotEqualTo(usuarioDTO2);
        usuarioDTO1.setId(null);
        assertThat(usuarioDTO1).isNotEqualTo(usuarioDTO2);
    }
}
