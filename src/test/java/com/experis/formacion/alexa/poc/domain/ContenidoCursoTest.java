package com.experis.formacion.alexa.poc.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.experis.formacion.alexa.poc.web.rest.TestUtil;

public class ContenidoCursoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContenidoCurso.class);
        ContenidoCurso contenidoCurso1 = new ContenidoCurso();
        contenidoCurso1.setId(1L);
        ContenidoCurso contenidoCurso2 = new ContenidoCurso();
        contenidoCurso2.setId(contenidoCurso1.getId());
        assertThat(contenidoCurso1).isEqualTo(contenidoCurso2);
        contenidoCurso2.setId(2L);
        assertThat(contenidoCurso1).isNotEqualTo(contenidoCurso2);
        contenidoCurso1.setId(null);
        assertThat(contenidoCurso1).isNotEqualTo(contenidoCurso2);
    }
}
