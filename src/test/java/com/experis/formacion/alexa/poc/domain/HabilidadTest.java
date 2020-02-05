package com.experis.formacion.alexa.poc.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.experis.formacion.alexa.poc.web.rest.TestUtil;

public class HabilidadTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Habilidad.class);
        Habilidad habilidad1 = new Habilidad();
        habilidad1.setId(1L);
        Habilidad habilidad2 = new Habilidad();
        habilidad2.setId(habilidad1.getId());
        assertThat(habilidad1).isEqualTo(habilidad2);
        habilidad2.setId(2L);
        assertThat(habilidad1).isNotEqualTo(habilidad2);
        habilidad1.setId(null);
        assertThat(habilidad1).isNotEqualTo(habilidad2);
    }
}
