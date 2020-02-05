package com.experis.formacion.alexa.poc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.experis.formacion.alexa.poc.web.rest.TestUtil;

public class TipoInteresDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoInteresDTO.class);
        TipoInteresDTO tipoInteresDTO1 = new TipoInteresDTO();
        tipoInteresDTO1.setId(1L);
        TipoInteresDTO tipoInteresDTO2 = new TipoInteresDTO();
        assertThat(tipoInteresDTO1).isNotEqualTo(tipoInteresDTO2);
        tipoInteresDTO2.setId(tipoInteresDTO1.getId());
        assertThat(tipoInteresDTO1).isEqualTo(tipoInteresDTO2);
        tipoInteresDTO2.setId(2L);
        assertThat(tipoInteresDTO1).isNotEqualTo(tipoInteresDTO2);
        tipoInteresDTO1.setId(null);
        assertThat(tipoInteresDTO1).isNotEqualTo(tipoInteresDTO2);
    }
}
