package com.experis.formacion.alexa.poc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.experis.formacion.alexa.poc.web.rest.TestUtil;

public class RelacionTipoInteresDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RelacionTipoInteresDTO.class);
        RelacionTipoInteresDTO relacionTipoInteresDTO1 = new RelacionTipoInteresDTO();
        relacionTipoInteresDTO1.setId(1L);
        RelacionTipoInteresDTO relacionTipoInteresDTO2 = new RelacionTipoInteresDTO();
        assertThat(relacionTipoInteresDTO1).isNotEqualTo(relacionTipoInteresDTO2);
        relacionTipoInteresDTO2.setId(relacionTipoInteresDTO1.getId());
        assertThat(relacionTipoInteresDTO1).isEqualTo(relacionTipoInteresDTO2);
        relacionTipoInteresDTO2.setId(2L);
        assertThat(relacionTipoInteresDTO1).isNotEqualTo(relacionTipoInteresDTO2);
        relacionTipoInteresDTO1.setId(null);
        assertThat(relacionTipoInteresDTO1).isNotEqualTo(relacionTipoInteresDTO2);
    }
}
