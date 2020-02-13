package com.experis.formacion.alexa.poc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.experis.formacion.alexa.poc.web.rest.TestUtil;

public class RelacionTipoHabilidadDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RelacionTipoHabilidadDTO.class);
        RelacionTipoHabilidadDTO relacionTipoHabilidadDTO1 = new RelacionTipoHabilidadDTO();
        relacionTipoHabilidadDTO1.setId(1L);
        RelacionTipoHabilidadDTO relacionTipoHabilidadDTO2 = new RelacionTipoHabilidadDTO();
        assertThat(relacionTipoHabilidadDTO1).isNotEqualTo(relacionTipoHabilidadDTO2);
        relacionTipoHabilidadDTO2.setId(relacionTipoHabilidadDTO1.getId());
        assertThat(relacionTipoHabilidadDTO1).isEqualTo(relacionTipoHabilidadDTO2);
        relacionTipoHabilidadDTO2.setId(2L);
        assertThat(relacionTipoHabilidadDTO1).isNotEqualTo(relacionTipoHabilidadDTO2);
        relacionTipoHabilidadDTO1.setId(null);
        assertThat(relacionTipoHabilidadDTO1).isNotEqualTo(relacionTipoHabilidadDTO2);
    }
}
