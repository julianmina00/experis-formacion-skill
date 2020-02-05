package com.experis.formacion.alexa.poc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.experis.formacion.alexa.poc.web.rest.TestUtil;

public class CursoPlanFormativoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CursoPlanFormativoDTO.class);
        CursoPlanFormativoDTO cursoPlanFormativoDTO1 = new CursoPlanFormativoDTO();
        cursoPlanFormativoDTO1.setId(1L);
        CursoPlanFormativoDTO cursoPlanFormativoDTO2 = new CursoPlanFormativoDTO();
        assertThat(cursoPlanFormativoDTO1).isNotEqualTo(cursoPlanFormativoDTO2);
        cursoPlanFormativoDTO2.setId(cursoPlanFormativoDTO1.getId());
        assertThat(cursoPlanFormativoDTO1).isEqualTo(cursoPlanFormativoDTO2);
        cursoPlanFormativoDTO2.setId(2L);
        assertThat(cursoPlanFormativoDTO1).isNotEqualTo(cursoPlanFormativoDTO2);
        cursoPlanFormativoDTO1.setId(null);
        assertThat(cursoPlanFormativoDTO1).isNotEqualTo(cursoPlanFormativoDTO2);
    }
}
