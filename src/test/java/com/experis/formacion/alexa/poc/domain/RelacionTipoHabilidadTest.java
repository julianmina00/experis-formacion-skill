package com.experis.formacion.alexa.poc.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.experis.formacion.alexa.poc.web.rest.TestUtil;

public class RelacionTipoHabilidadTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RelacionTipoHabilidad.class);
        RelacionTipoHabilidad relacionTipoHabilidad1 = new RelacionTipoHabilidad();
        relacionTipoHabilidad1.setId(1L);
        RelacionTipoHabilidad relacionTipoHabilidad2 = new RelacionTipoHabilidad();
        relacionTipoHabilidad2.setId(relacionTipoHabilidad1.getId());
        assertThat(relacionTipoHabilidad1).isEqualTo(relacionTipoHabilidad2);
        relacionTipoHabilidad2.setId(2L);
        assertThat(relacionTipoHabilidad1).isNotEqualTo(relacionTipoHabilidad2);
        relacionTipoHabilidad1.setId(null);
        assertThat(relacionTipoHabilidad1).isNotEqualTo(relacionTipoHabilidad2);
    }
}
