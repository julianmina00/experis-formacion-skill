package com.experis.formacion.alexa.poc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.experis.formacion.alexa.poc.web.rest.TestUtil;

public class HabilidadUsuarioDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HabilidadUsuarioDTO.class);
        HabilidadUsuarioDTO habilidadUsuarioDTO1 = new HabilidadUsuarioDTO();
        habilidadUsuarioDTO1.setId(1L);
        HabilidadUsuarioDTO habilidadUsuarioDTO2 = new HabilidadUsuarioDTO();
        assertThat(habilidadUsuarioDTO1).isNotEqualTo(habilidadUsuarioDTO2);
        habilidadUsuarioDTO2.setId(habilidadUsuarioDTO1.getId());
        assertThat(habilidadUsuarioDTO1).isEqualTo(habilidadUsuarioDTO2);
        habilidadUsuarioDTO2.setId(2L);
        assertThat(habilidadUsuarioDTO1).isNotEqualTo(habilidadUsuarioDTO2);
        habilidadUsuarioDTO1.setId(null);
        assertThat(habilidadUsuarioDTO1).isNotEqualTo(habilidadUsuarioDTO2);
    }
}
