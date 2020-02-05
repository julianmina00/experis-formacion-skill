package com.experis.formacion.alexa.poc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.experis.formacion.alexa.poc.web.rest.TestUtil;

public class TipoHabilidadDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoHabilidadDTO.class);
        TipoHabilidadDTO tipoHabilidadDTO1 = new TipoHabilidadDTO();
        tipoHabilidadDTO1.setId(1L);
        TipoHabilidadDTO tipoHabilidadDTO2 = new TipoHabilidadDTO();
        assertThat(tipoHabilidadDTO1).isNotEqualTo(tipoHabilidadDTO2);
        tipoHabilidadDTO2.setId(tipoHabilidadDTO1.getId());
        assertThat(tipoHabilidadDTO1).isEqualTo(tipoHabilidadDTO2);
        tipoHabilidadDTO2.setId(2L);
        assertThat(tipoHabilidadDTO1).isNotEqualTo(tipoHabilidadDTO2);
        tipoHabilidadDTO1.setId(null);
        assertThat(tipoHabilidadDTO1).isNotEqualTo(tipoHabilidadDTO2);
    }
}
