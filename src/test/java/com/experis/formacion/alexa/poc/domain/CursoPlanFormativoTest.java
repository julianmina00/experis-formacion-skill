package com.experis.formacion.alexa.poc.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.experis.formacion.alexa.poc.web.rest.TestUtil;

public class CursoPlanFormativoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CursoPlanFormativo.class);
        CursoPlanFormativo cursoPlanFormativo1 = new CursoPlanFormativo();
        cursoPlanFormativo1.setId(1L);
        CursoPlanFormativo cursoPlanFormativo2 = new CursoPlanFormativo();
        cursoPlanFormativo2.setId(cursoPlanFormativo1.getId());
        assertThat(cursoPlanFormativo1).isEqualTo(cursoPlanFormativo2);
        cursoPlanFormativo2.setId(2L);
        assertThat(cursoPlanFormativo1).isNotEqualTo(cursoPlanFormativo2);
        cursoPlanFormativo1.setId(null);
        assertThat(cursoPlanFormativo1).isNotEqualTo(cursoPlanFormativo2);
    }
}
