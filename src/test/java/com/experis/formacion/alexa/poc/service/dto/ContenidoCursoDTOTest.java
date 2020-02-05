package com.experis.formacion.alexa.poc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.experis.formacion.alexa.poc.web.rest.TestUtil;

public class ContenidoCursoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContenidoCursoDTO.class);
        ContenidoCursoDTO contenidoCursoDTO1 = new ContenidoCursoDTO();
        contenidoCursoDTO1.setId(1L);
        ContenidoCursoDTO contenidoCursoDTO2 = new ContenidoCursoDTO();
        assertThat(contenidoCursoDTO1).isNotEqualTo(contenidoCursoDTO2);
        contenidoCursoDTO2.setId(contenidoCursoDTO1.getId());
        assertThat(contenidoCursoDTO1).isEqualTo(contenidoCursoDTO2);
        contenidoCursoDTO2.setId(2L);
        assertThat(contenidoCursoDTO1).isNotEqualTo(contenidoCursoDTO2);
        contenidoCursoDTO1.setId(null);
        assertThat(contenidoCursoDTO1).isNotEqualTo(contenidoCursoDTO2);
    }
}
