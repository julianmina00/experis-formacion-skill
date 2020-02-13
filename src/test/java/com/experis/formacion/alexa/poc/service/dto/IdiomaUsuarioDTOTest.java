package com.experis.formacion.alexa.poc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.experis.formacion.alexa.poc.web.rest.TestUtil;

public class IdiomaUsuarioDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(IdiomaUsuarioDTO.class);
        IdiomaUsuarioDTO idiomaUsuarioDTO1 = new IdiomaUsuarioDTO();
        idiomaUsuarioDTO1.setId(1L);
        IdiomaUsuarioDTO idiomaUsuarioDTO2 = new IdiomaUsuarioDTO();
        assertThat(idiomaUsuarioDTO1).isNotEqualTo(idiomaUsuarioDTO2);
        idiomaUsuarioDTO2.setId(idiomaUsuarioDTO1.getId());
        assertThat(idiomaUsuarioDTO1).isEqualTo(idiomaUsuarioDTO2);
        idiomaUsuarioDTO2.setId(2L);
        assertThat(idiomaUsuarioDTO1).isNotEqualTo(idiomaUsuarioDTO2);
        idiomaUsuarioDTO1.setId(null);
        assertThat(idiomaUsuarioDTO1).isNotEqualTo(idiomaUsuarioDTO2);
    }
}
