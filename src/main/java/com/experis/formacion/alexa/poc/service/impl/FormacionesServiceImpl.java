package com.experis.formacion.alexa.poc.service.impl;

import com.experis.formacion.alexa.poc.domain.Curso;
import com.experis.formacion.alexa.poc.domain.CursoPlanFormativo;
import com.experis.formacion.alexa.poc.domain.CursoUsuario;
import com.experis.formacion.alexa.poc.domain.Habilidad;
import com.experis.formacion.alexa.poc.domain.HabilidadUsuario;
import com.experis.formacion.alexa.poc.domain.Interes;
import com.experis.formacion.alexa.poc.domain.InteresUsuario;
import com.experis.formacion.alexa.poc.domain.PlanFormativo;
import com.experis.formacion.alexa.poc.domain.PlanFormativoUsuario;
import com.experis.formacion.alexa.poc.domain.Usuario;
import com.experis.formacion.alexa.poc.repository.CursoPlanFormativoRepository;
import com.experis.formacion.alexa.poc.repository.CursoRepository;
import com.experis.formacion.alexa.poc.repository.CursoUsuarioRepository;
import com.experis.formacion.alexa.poc.repository.HabilidadUsuarioRepository;
import com.experis.formacion.alexa.poc.repository.InteresUsuarioRepository;
import com.experis.formacion.alexa.poc.repository.PlanFormativoRepository;
import com.experis.formacion.alexa.poc.repository.PlanFormativoUsuarioRepository;
import com.experis.formacion.alexa.poc.repository.UsuarioRepository;
import com.experis.formacion.alexa.poc.service.CursoUsuarioService;
import com.experis.formacion.alexa.poc.service.FormacionesService;
import com.experis.formacion.alexa.poc.service.PlanFormativoUsuarioService;
import com.experis.formacion.alexa.poc.service.dto.CursoUsuarioDTO;
import com.experis.formacion.alexa.poc.service.dto.FormacionesDTO;
import com.experis.formacion.alexa.poc.service.dto.FormacionesSugeridasDTO;
import com.experis.formacion.alexa.poc.service.dto.PlanFormativoUsuarioDTO;
import com.experis.formacion.alexa.poc.service.dto.RegistroFormacionDTO;
import com.experis.formacion.alexa.poc.service.mapper.FormacionesCursoMapper;
import com.experis.formacion.alexa.poc.service.mapper.FormacionesPlanMapper;
import com.experis.formacion.alexa.poc.service.mapper.FormacionesSugeridasCursoMapper;
import com.experis.formacion.alexa.poc.service.mapper.FormacionesSugeridasPlanMapper;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
    private FormacionesSugeridasCursoMapper formacionesSugeridasCursoMapper;
    private FormacionesSugeridasPlanMapper formacionesSugeridasPlanMapper;
    private FormacionesCursoMapper formacionesCursoMapper;
    private FormacionesPlanMapper formacionesPlanMapper;
    private CursoUsuarioService cursoUsuarioService;
    private PlanFormativoUsuarioService planFormativoUsuarioService;
    private HabilidadUsuarioRepository habilidadUsuarioRepository;
    private InteresUsuarioRepository interesUsuarioRepository;
    private CursoPlanFormativoRepository cursoPlanFormativoRepository;
    private CursoUsuarioRepository cursoUsuarioRepository;
    private PlanFormativoUsuarioRepository planFormativoUsuarioRepository;

    public FormacionesServiceImpl(
        CursoRepository cursoRepository,
        PlanFormativoRepository planFormativoRepository,
        UsuarioRepository usuarioRepository,
        FormacionesSugeridasCursoMapper formacionesSugeridasCursoMapper,
        FormacionesSugeridasPlanMapper formacionesSugeridasPlanMapper,
        FormacionesCursoMapper formacionesCursoMapper,
        FormacionesPlanMapper formacionesPlanMapper,
        CursoUsuarioService cursoUsuarioService,
        PlanFormativoUsuarioService planFormativoUsuarioService,
        HabilidadUsuarioRepository habilidadUsuarioRepository,
        InteresUsuarioRepository interesUsuarioRepository,
        CursoPlanFormativoRepository cursoPlanFormativoRepository,
        CursoUsuarioRepository cursoUsuarioRepository,
        PlanFormativoUsuarioRepository planFormativoUsuarioRepository) {
        this.cursoRepository = cursoRepository;
        this.planFormativoRepository = planFormativoRepository;
        this.usuarioRepository = usuarioRepository;
        this.formacionesSugeridasCursoMapper = formacionesSugeridasCursoMapper;
        this.formacionesSugeridasPlanMapper = formacionesSugeridasPlanMapper;
        this.formacionesCursoMapper = formacionesCursoMapper;
        this.formacionesPlanMapper = formacionesPlanMapper;
        this.cursoUsuarioService = cursoUsuarioService;
        this.planFormativoUsuarioService = planFormativoUsuarioService;
        this.habilidadUsuarioRepository = habilidadUsuarioRepository;
        this.interesUsuarioRepository = interesUsuarioRepository;
        this.cursoPlanFormativoRepository = cursoPlanFormativoRepository;
        this.cursoUsuarioRepository = cursoUsuarioRepository;
        this.planFormativoUsuarioRepository = planFormativoUsuarioRepository;
    }

    @Override
    public List<FormacionesDTO> getFormacionesPorFecha(long usuarioId, LocalDate fechaIni, LocalDate fechaFin) {
        List<FormacionesDTO> formaciones = new ArrayList<>();
        List<CursoUsuario> cursosUsuario = cursoUsuarioRepository.findAvailableByUsuarioIdAndRangeOfDates(usuarioId, fechaIni, fechaFin);
        List<PlanFormativoUsuario> planesUsuario = planFormativoUsuarioRepository.findAvailableByUsuarioIdAndRangeOfDates(usuarioId, fechaIni, fechaFin);
        formaciones.addAll(cursosUsuario.stream().map(cursoUsuario -> formacionesCursoMapper.toDto(cursoUsuario.getCurso())).collect(
            Collectors.toList()));
        List<FormacionesDTO> planesFormativos = planesUsuario.stream()
            .map(planUsuario -> formacionesPlanMapper.toDto(planUsuario.getPlanFormativo()))
            .collect(Collectors.toList());
        planesFormativos.forEach(planFormativo ->  {
            List<String> cursos = new ArrayList<>();
            List<CursoPlanFormativo> cursoPlanFormativo = cursoPlanFormativoRepository.findByPlanFormativoId(planFormativo.getId());
            cursoPlanFormativo.forEach(cursoPlan -> cursos.add(cursoPlan.getCurso().getDescripcion()));
            planFormativo.setCursos(cursos);
        });
        formaciones.addAll(planesFormativos);
        return formaciones;
    }

    @Override
    public RegistroFormacionDTO registerFormacionUsuario(RegistroFormacionDTO dto) {
        if(dto.getTipoFormacion().equalsIgnoreCase(TIPO_FORMACION_CURSO)){
            CursoUsuarioDTO cursoUsuarioDTO = new CursoUsuarioDTO();
            cursoUsuarioDTO.setUsuarioId(dto.getUsuarioId());
            cursoUsuarioDTO.setCursoId(dto.getFormacionId());
            Optional<CursoUsuario> cursoUsuarioOptional = cursoUsuarioRepository.findOneByUsuarioIdAndCursoId(dto.getUsuarioId(), dto.getFormacionId());
            if (cursoUsuarioOptional.isPresent()) {
                throw new RuntimeException("el usuario ya se había dado de alta en este curso");
            }
            cursoUsuarioDTO = cursoUsuarioService.save(cursoUsuarioDTO);
            dto.setId(cursoUsuarioDTO.getId());
            return dto;
        }
        else if(dto.getTipoFormacion().equalsIgnoreCase(TIPO_FORMACION_PLANES)){
            PlanFormativoUsuarioDTO planFormativoUsuarioDTO = new PlanFormativoUsuarioDTO();
            planFormativoUsuarioDTO.setUsuarioId(dto.getUsuarioId());
            planFormativoUsuarioDTO.setPlanFormativoId(dto.getFormacionId());
            Optional<PlanFormativoUsuario> planFormativoUsuarioOptional = planFormativoUsuarioRepository.findOneByUsuarioIdAndPlanFormativoId(dto.getUsuarioId(), dto.getFormacionId());
            if (planFormativoUsuarioOptional.isPresent()) {
                throw new RuntimeException("el usuario ya se había dado de alta en este plan formativo");
            }
            planFormativoUsuarioDTO = planFormativoUsuarioService.save(planFormativoUsuarioDTO);
            dto.setId(planFormativoUsuarioDTO.getId());
            return dto;
        }
        throw new RuntimeException("El tipo de formación "+dto.getTipoFormacion()+" no es válido");
    }

    @Override
    public List<FormacionesSugeridasDTO> getFormacionesSugeridas(long usuarioId, String tipoFormacion, int pagina, int registrosPorPagina) {

        Optional<Usuario> optionalUsuario = usuarioRepository.findById(usuarioId);
        return optionalUsuario.map(usuario -> {
            List<Long> idHabilidades = new ArrayList<>();
            List<Long> idIntereses = new ArrayList<>();
            usuario.getHabilidadUsuarios().forEach(habilidadUsuario -> {
                Habilidad habilidad = habilidadUsuario.getHabilidad();
                idHabilidades.add(habilidad.getId());
            });
            usuario.getInteresUsuarios().forEach(interesUsuario -> {
                Interes interes = interesUsuario.getInteres();
                idIntereses.add(interes.getId());
            });
            if(idHabilidades.isEmpty()){
                idHabilidades.add(0L);
            }
            if(idIntereses.isEmpty()){
                idIntereses.add(0L);
            }
            if(tipoFormacion.equalsIgnoreCase(TIPO_FORMACION_CURSO)){
                return getCursosSugeridos(usuarioId, idHabilidades, idIntereses, pagina, registrosPorPagina);
            }
            if(tipoFormacion.equalsIgnoreCase(TIPO_FORMACION_PLANES)){
                return getPlanesFormativosSugeridos(usuarioId, idHabilidades, idIntereses, pagina, registrosPorPagina);
            }
            return Collections.<FormacionesSugeridasDTO>emptyList();
        }).orElseThrow(() -> new RuntimeException("El usuario con id "+usuarioId+" no es válido"));
    }

    private List<FormacionesSugeridasDTO> getCursosSugeridos(Long usuarioId, List<Long> idHabilidades, List<Long> idIntereses, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        List<Curso> cursos = cursoRepository.findMoreRelevantAvailableByHabilidadesAndIntereses(idHabilidades, idIntereses, pageable);
        List<FormacionesSugeridasDTO> formacionesSugeridas = new ArrayList<>();
        cursos.forEach(curso -> {
            FormacionesSugeridasDTO dto = formacionesSugeridasCursoMapper.toDto(curso);
            dto.setTipoFormacion(TIPO_FORMACION_CURSO);
            List<HabilidadUsuario> matchHabilidades = habilidadUsuarioRepository.findMatchByUsuarioAndCurso(usuarioId, curso.getId());
            List<InteresUsuario> matchIntereses = interesUsuarioRepository.findMatchByUsuarioAndCurso(usuarioId, curso.getId());
            dto.setHabilidadesRelacionadas(
                matchHabilidades.stream().map(habilidadUsuario -> habilidadUsuario.getHabilidad().getDescripcion()).collect(Collectors.toList()));
            dto.setInteresesRelacionados(
                matchIntereses.stream().map(interesUsuario -> interesUsuario.getInteres().getDescripcion()).collect(Collectors.toList()));
            formacionesSugeridas.add(dto);
        });
        return formacionesSugeridas;
    }

    private List<FormacionesSugeridasDTO> getPlanesFormativosSugeridos(Long usuarioId, List<Long> idHabilidades, List<Long> idIntereses, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        List<PlanFormativo> planesFormativos = planFormativoRepository.findMoreRelevantAvailableByHabilidadesAndIntereses(idHabilidades, idIntereses, pageable);
        List<FormacionesSugeridasDTO> formacionesSugeridas = new ArrayList<>();
        planesFormativos.forEach(planFormativo -> {
            FormacionesSugeridasDTO dto = formacionesSugeridasPlanMapper.toDto(planFormativo);
            dto.setTipoFormacion(TIPO_FORMACION_PLANES);
            List<HabilidadUsuario> matchHabilidades = habilidadUsuarioRepository.findMatchByUsuarioAndPlanFormativo(usuarioId, planFormativo.getId());
            List<InteresUsuario> matchIntereses = interesUsuarioRepository.findMatchByUsuarioAndPlanFormativo(usuarioId, planFormativo.getId());
            dto.setHabilidadesRelacionadas(
                matchHabilidades.stream().map(habilidadUsuario -> habilidadUsuario.getHabilidad().getDescripcion()).collect(Collectors.toList()));
            dto.setInteresesRelacionados(
                matchIntereses.stream().map(interesUsuario -> interesUsuario.getInteres().getDescripcion()).collect(Collectors.toList()));
            List<String> cursos = new ArrayList<>();
            planFormativo.getCursoPlanFormativos().forEach(cursoPlanFormativo -> cursos.add(cursoPlanFormativo.getCurso().getDescripcion()));
            List<CursoPlanFormativo> cursosPlanFormativo =  cursoPlanFormativoRepository.findByPlanFormativo(planFormativo);
            dto.setCursos(
                cursosPlanFormativo.stream().map(cursoPlanFormativo -> cursoPlanFormativo.getCurso().getDescripcion()).collect(Collectors.toList()));
            formacionesSugeridas.add(dto);
        });
        return formacionesSugeridas;
    }

}
