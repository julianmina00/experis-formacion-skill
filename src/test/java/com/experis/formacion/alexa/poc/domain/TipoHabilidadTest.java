package com.experis.formacion.alexa.poc.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.experis.formacion.alexa.poc.web.rest.TestUtil;

public class TipoHabilidadTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoHabilidad.class);
        TipoHabilidad tipoHabilidad1 = new TipoHabilidad();
        tipoHabilidad1.setId(1L);
        TipoHabilidad tipoHabilidad2 = new TipoHabilidad();
        tipoHabilidad2.setId(tipoHabilidad1.getId());
        assertThat(tipoHabilidad1).isEqualTo(tipoHabilidad2);
        tipoHabilidad2.setId(2L);
        assertThat(tipoHabilidad1).isNotEqualTo(tipoHabilidad2);
        tipoHabilidad1.setId(null);
        assertThat(tipoHabilidad1).isNotEqualTo(tipoHabilidad2);
    }
}
