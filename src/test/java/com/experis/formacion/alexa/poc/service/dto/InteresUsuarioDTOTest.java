package com.experis.formacion.alexa.poc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.experis.formacion.alexa.poc.web.rest.TestUtil;

public class InteresUsuarioDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InteresUsuarioDTO.class);
        InteresUsuarioDTO interesUsuarioDTO1 = new InteresUsuarioDTO();
        interesUsuarioDTO1.setId(1L);
        InteresUsuarioDTO interesUsuarioDTO2 = new InteresUsuarioDTO();
        assertThat(interesUsuarioDTO1).isNotEqualTo(interesUsuarioDTO2);
        interesUsuarioDTO2.setId(interesUsuarioDTO1.getId());
        assertThat(interesUsuarioDTO1).isEqualTo(interesUsuarioDTO2);
        interesUsuarioDTO2.setId(2L);
        assertThat(interesUsuarioDTO1).isNotEqualTo(interesUsuarioDTO2);
        interesUsuarioDTO1.setId(null);
        assertThat(interesUsuarioDTO1).isNotEqualTo(interesUsuarioDTO2);
    }
}
