package com.experis.formacion.alexa.poc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.experis.formacion.alexa.poc.web.rest.TestUtil;

public class NivelIdiomaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NivelIdiomaDTO.class);
        NivelIdiomaDTO nivelIdiomaDTO1 = new NivelIdiomaDTO();
        nivelIdiomaDTO1.setId(1L);
        NivelIdiomaDTO nivelIdiomaDTO2 = new NivelIdiomaDTO();
        assertThat(nivelIdiomaDTO1).isNotEqualTo(nivelIdiomaDTO2);
        nivelIdiomaDTO2.setId(nivelIdiomaDTO1.getId());
        assertThat(nivelIdiomaDTO1).isEqualTo(nivelIdiomaDTO2);
        nivelIdiomaDTO2.setId(2L);
        assertThat(nivelIdiomaDTO1).isNotEqualTo(nivelIdiomaDTO2);
        nivelIdiomaDTO1.setId(null);
        assertThat(nivelIdiomaDTO1).isNotEqualTo(nivelIdiomaDTO2);
    }
}
