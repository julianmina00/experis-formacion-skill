package com.experis.formacion.alexa.poc.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.experis.formacion.alexa.poc.web.rest.TestUtil;

public class NivelIdiomaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NivelIdioma.class);
        NivelIdioma nivelIdioma1 = new NivelIdioma();
        nivelIdioma1.setId(1L);
        NivelIdioma nivelIdioma2 = new NivelIdioma();
        nivelIdioma2.setId(nivelIdioma1.getId());
        assertThat(nivelIdioma1).isEqualTo(nivelIdioma2);
        nivelIdioma2.setId(2L);
        assertThat(nivelIdioma1).isNotEqualTo(nivelIdioma2);
        nivelIdioma1.setId(null);
        assertThat(nivelIdioma1).isNotEqualTo(nivelIdioma2);
    }
}
