package com.experis.formacion.alexa.poc.service.impl;

import com.experis.formacion.alexa.poc.domain.Curso;
import com.experis.formacion.alexa.poc.domain.Habilidad;
import com.experis.formacion.alexa.poc.domain.Interes;
import com.experis.formacion.alexa.poc.domain.PlanFormativo;
import com.experis.formacion.alexa.poc.domain.Usuario;
import com.experis.formacion.alexa.poc.repository.ContenidoCursoRepository;
import com.experis.formacion.alexa.poc.repository.CursoRepository;
import com.experis.formacion.alexa.poc.repository.HabilidadRepository;
import com.experis.formacion.alexa.poc.repository.HabilidadUsuarioRepository;
import com.experis.formacion.alexa.poc.repository.InteresRepository;
import com.experis.formacion.alexa.poc.repository.InteresUsuarioRepository;
import com.experis.formacion.alexa.poc.repository.PerfilPlanFormativoRepository;
import com.experis.formacion.alexa.poc.repository.PlanFormativoRepository;
import com.experis.formacion.alexa.poc.repository.UsuarioRepository;
import com.experis.formacion.alexa.poc.service.FormacionesService;
import com.experis.formacion.alexa.poc.service.dto.FormacionesSugeridasDTO;
import com.experis.formacion.alexa.poc.service.mapper.FormacionesCursoMapper;
import com.experis.formacion.alexa.poc.service.mapper.FormacionesPlanMapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FormacionesServiceImpl implements FormacionesService {

    private static final String TIPO_FORMACION_CURSO = "C";
    private static final String TIPO_FORMACION_PLANES = "P";
    private CursoRepository cursoRepository;
    private PlanFormativoRepository planFormativoRepository;
    private UsuarioRepository usuarioRepository;
    private FormacionesCursoMapper formacionesCursoMapper;
    private FormacionesPlanMapper formacionesPlanMapper;

    public FormacionesServiceImpl(
        CursoRepository cursoRepository,
        PlanFormativoRepository planFormativoRepository,
        UsuarioRepository usuarioRepository,
        FormacionesCursoMapper formacionesCursoMapper,
        FormacionesPlanMapper formacionesPlanMapper) {
        this.cursoRepository = cursoRepository;
        this.planFormativoRepository = planFormativoRepository;
        this.usuarioRepository = usuarioRepository;
        this.formacionesCursoMapper = formacionesCursoMapper;
        this.formacionesPlanMapper = formacionesPlanMapper;
    }

    @Override
    public List<FormacionesSugeridasDTO> getFormacionesSugeridas(long usuarioId, String tipoFormacion, int pagina, int registrosPorPagina) {

        Optional<Usuario> optionalUsuario = usuarioRepository.findById(usuarioId);
        return optionalUsuario.map(usuario -> {
            List<Long> idHabilidades = new ArrayList<>();
            List<Long> idIntereses = new ArrayList<>();
            List<String> habilidades = new ArrayList<>();
            List<String> intereses = new ArrayList<>();
            usuario.getHabilidadUsuarios().forEach(habilidadUsuario -> {
                Habilidad habilidad = habilidadUsuario.getHabilidad();
                habilidades.add(habilidad.getDescripcion());
                idHabilidades.add(habilidad.getId());
            });
            usuario.getInteresUsuarios().forEach(interesUsuario -> {
                Interes interes = interesUsuario.getInteres();
                intereses.add(interes.getDescripcion());
                idIntereses.add(interes.getId());
            });
            if(tipoFormacion.equalsIgnoreCase(TIPO_FORMACION_CURSO)){
                return getCursosSugeridos(idHabilidades, idIntereses, habilidades, intereses, pagina, registrosPorPagina);
            }
            if(tipoFormacion.equalsIgnoreCase(TIPO_FORMACION_PLANES)){
                return getPlanesFormativosSugeridos(idHabilidades, idIntereses, habilidades, intereses, pagina, registrosPorPagina);
            }
            return Collections.<FormacionesSugeridasDTO>emptyList();
        }).orElseThrow(() -> new RuntimeException("El usuario con id "+usuarioId+" no es válido"));
    }

    private List<FormacionesSugeridasDTO> getCursosSugeridos(List<Long> idHabilidades, List<Long> idIntereses, List<String> habilidades, List<String> intereses, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        List<Curso> cursos = cursoRepository.findMoreRelevantAvailableByHabilidadesAndIntereses(idHabilidades, idIntereses, pageable);
        List<FormacionesSugeridasDTO> formacionesSugeridas = new ArrayList<>();
        cursos.forEach(curso -> {
            FormacionesSugeridasDTO dto = formacionesCursoMapper.toDto(curso);
            dto.setTipoFormacion(TIPO_FORMACION_CURSO);
            dto.setHabilidadesRelacionadas(habilidades);
            dto.setInteresesRelacionados(intereses);
            formacionesSugeridas.add(dto);
        });
        return formacionesSugeridas;
    }

    private List<FormacionesSugeridasDTO> getPlanesFormativosSugeridos(List<Long> idHabilidades, List<Long> idIntereses, List<String> habilidades, List<String> intereses, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        List<PlanFormativo> planesFormativos = planFormativoRepository.findMoreRelevantAvailableByHabilidadesAndIntereses(idHabilidades, idIntereses, pageable);
        List<FormacionesSugeridasDTO> formacionesSugeridas = new ArrayList<>();
        planesFormativos.forEach(planFormativo -> {
            FormacionesSugeridasDTO dto = formacionesPlanMapper.toDto(planFormativo);
            dto.setTipoFormacion(TIPO_FORMACION_PLANES);
            dto.setHabilidadesRelacionadas(habilidades);
            dto.setInteresesRelacionados(intereses);
            List<String> cursos = new ArrayList<>();
            planFormativo.getCursoPlanFormativos().forEach(cursoPlanFormativo -> cursos.add(cursoPlanFormativo.getCurso().getDescripcion()));
            dto.setCursos(cursos);
            formacionesSugeridas.add(dto);
        });
        return formacionesSugeridas;
    }

}
