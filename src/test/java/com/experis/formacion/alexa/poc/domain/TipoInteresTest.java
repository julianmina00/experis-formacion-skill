package com.experis.formacion.alexa.poc.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.experis.formacion.alexa.poc.web.rest.TestUtil;

public class TipoInteresTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoInteres.class);
        TipoInteres tipoInteres1 = new TipoInteres();
        tipoInteres1.setId(1L);
        TipoInteres tipoInteres2 = new TipoInteres();
        tipoInteres2.setId(tipoInteres1.getId());
        assertThat(tipoInteres1).isEqualTo(tipoInteres2);
        tipoInteres2.setId(2L);
        assertThat(tipoInteres1).isNotEqualTo(tipoInteres2);
        tipoInteres1.setId(null);
        assertThat(tipoInteres1).isNotEqualTo(tipoInteres2);
    }
}
