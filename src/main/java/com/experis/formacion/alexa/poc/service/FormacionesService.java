package com.experis.formacion.alexa.poc.service;

import com.experis.formacion.alexa.poc.service.dto.FormacionesDTO;
import com.experis.formacion.alexa.poc.service.dto.FormacionesSugeridasDTO;
import com.experis.formacion.alexa.poc.service.dto.RegistroFormacionDTO;
import java.time.LocalDate;
import java.util.List;

public interface FormacionesService {

    List<FormacionesSugeridasDTO> getFormacionesSugeridas(long usuarioId, String tipoFormacion, int pagina, int registrosPorPagina);

    RegistroFormacionDTO registerFormacionUsuario(RegistroFormacionDTO dto);

    List<FormacionesDTO> getFormacionesPorFecha(long usuarioId, LocalDate fechaIni, LocalDate fechaFin);

}
