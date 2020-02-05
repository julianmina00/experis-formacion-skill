package com.experis.formacion.alexa.poc.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.experis.formacion.alexa.poc.web.rest.TestUtil;

public class InteresTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Interes.class);
        Interes interes1 = new Interes();
        interes1.setId(1L);
        Interes interes2 = new Interes();
        interes2.setId(interes1.getId());
        assertThat(interes1).isEqualTo(interes2);
        interes2.setId(2L);
        assertThat(interes1).isNotEqualTo(interes2);
        interes1.setId(null);
        assertThat(interes1).isNotEqualTo(interes2);
    }
}
