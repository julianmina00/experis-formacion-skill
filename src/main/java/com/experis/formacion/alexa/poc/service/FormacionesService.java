package com.experis.formacion.alexa.poc.service;

import com.experis.formacion.alexa.poc.service.dto.FormacionesSugeridasDTO;
import java.util.List;

public interface FormacionesService {

    List<FormacionesSugeridasDTO> getFormacionesSugeridas(long usuarioId, String tipoFormacion, int pagina, int registrosPorPagina);

}
