package com.experis.formacion.alexa.poc.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.experis.formacion.alexa.poc.web.rest.TestUtil;

public class RelacionTipoInteresTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RelacionTipoInteres.class);
        RelacionTipoInteres relacionTipoInteres1 = new RelacionTipoInteres();
        relacionTipoInteres1.setId(1L);
        RelacionTipoInteres relacionTipoInteres2 = new RelacionTipoInteres();
        relacionTipoInteres2.setId(relacionTipoInteres1.getId());
        assertThat(relacionTipoInteres1).isEqualTo(relacionTipoInteres2);
        relacionTipoInteres2.setId(2L);
        assertThat(relacionTipoInteres1).isNotEqualTo(relacionTipoInteres2);
        relacionTipoInteres1.setId(null);
        assertThat(relacionTipoInteres1).isNotEqualTo(relacionTipoInteres2);
    }
}
