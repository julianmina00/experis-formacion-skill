package com.experis.formacion.alexa.poc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.experis.formacion.alexa.poc.web.rest.TestUtil;

public class InteresDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InteresDTO.class);
        InteresDTO interesDTO1 = new InteresDTO();
        interesDTO1.setId(1L);
        InteresDTO interesDTO2 = new InteresDTO();
        assertThat(interesDTO1).isNotEqualTo(interesDTO2);
        interesDTO2.setId(interesDTO1.getId());
        assertThat(interesDTO1).isEqualTo(interesDTO2);
        interesDTO2.setId(2L);
        assertThat(interesDTO1).isNotEqualTo(interesDTO2);
        interesDTO1.setId(null);
        assertThat(interesDTO1).isNotEqualTo(interesDTO2);
    }
}
