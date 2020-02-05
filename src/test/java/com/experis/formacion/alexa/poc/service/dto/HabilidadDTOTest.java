package com.experis.formacion.alexa.poc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.experis.formacion.alexa.poc.web.rest.TestUtil;

public class HabilidadDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HabilidadDTO.class);
        HabilidadDTO habilidadDTO1 = new HabilidadDTO();
        habilidadDTO1.setId(1L);
        HabilidadDTO habilidadDTO2 = new HabilidadDTO();
        assertThat(habilidadDTO1).isNotEqualTo(habilidadDTO2);
        habilidadDTO2.setId(habilidadDTO1.getId());
        assertThat(habilidadDTO1).isEqualTo(habilidadDTO2);
        habilidadDTO2.setId(2L);
        assertThat(habilidadDTO1).isNotEqualTo(habilidadDTO2);
        habilidadDTO1.setId(null);
        assertThat(habilidadDTO1).isNotEqualTo(habilidadDTO2);
    }
}
