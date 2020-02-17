package com.experis.formacion.alexa.poc.service;

import com.experis.formacion.alexa.poc.domain.Interes;
import com.experis.formacion.alexa.poc.domain.InteresUsuario;
import com.experis.formacion.alexa.poc.domain.RelacionTipoInteres;
import com.experis.formacion.alexa.poc.domain.TipoInteres;
import com.experis.formacion.alexa.poc.domain.Usuario;
import com.experis.formacion.alexa.poc.repository.InteresRepository;
import com.experis.formacion.alexa.poc.repository.InteresUsuarioRepository;
import com.experis.formacion.alexa.poc.repository.RelacionTipoInteresRepository;
import com.experis.formacion.alexa.poc.repository.TipoInteresRepository;
import com.experis.formacion.alexa.poc.repository.UsuarioRepository;
import com.experis.formacion.alexa.poc.service.dto.InteresUsuarioDTO;
import com.experis.formacion.alexa.poc.service.dto.RegistroInteresDTO;
import com.experis.formacion.alexa.poc.service.mapper.InteresUsuarioMapper;
import java.util.ArrayList;
import java.util.Collections;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link InteresUsuario}.
 */
@Service
@Transactional
public class InteresUsuarioService {

    private final Logger log = LoggerFactory.getLogger(InteresUsuarioService.class);

    private final InteresUsuarioRepository interesUsuarioRepository;
    private final InteresUsuarioMapper interesUsuarioMapper;
    private final TipoInteresRepository tipoInteresRepository;
    private final InteresRepository interesRepository;
    private final UsuarioRepository usuarioRepository;
    private final RelacionTipoInteresRepository relacionTipoInteresRepository;

    public InteresUsuarioService(InteresUsuarioRepository interesUsuarioRepository, InteresUsuarioMapper interesUsuarioMapper,
        TipoInteresRepository tipoInteresRepository, InteresRepository interesRepository,
        UsuarioRepository usuarioRepository, RelacionTipoInteresRepository relacionTipoInteresRepository) {
        this.interesUsuarioRepository = interesUsuarioRepository;
        this.interesUsuarioMapper = interesUsuarioMapper;
        this.tipoInteresRepository = tipoInteresRepository;
        this.interesRepository = interesRepository;
        this.usuarioRepository = usuarioRepository;
        this.relacionTipoInteresRepository = relacionTipoInteresRepository;
    }

    private List<TipoInteres> tipoInteresesPadreEnArbol(TipoInteres tipoInteres, int profundidad) {
        List<TipoInteres> padres = new ArrayList<>();
        padres.add(tipoInteres);
        List<RelacionTipoInteres> relacionesPadre = relacionTipoInteresRepository.findByHijaIdAndProfundidad(tipoInteres.getId(), profundidad);
        relacionesPadre.forEach(relacionTipoInteresPadre -> padres.addAll(tipoInteresesPadreEnArbol(relacionTipoInteresPadre.getPadre(), profundidad-1)));
        return padres;
    }

    private List<TipoInteres> tipoInteresesHijasEnArbol(TipoInteres tipoInteres, int profundidad){
        List<TipoInteres> tipoIntereses = new ArrayList<>();
        tipoIntereses.add(tipoInteres);
        List<RelacionTipoInteres> relacionesHijas = relacionTipoInteresRepository.findByPadreIdAndProfundidad(tipoInteres.getId(), profundidad);
        relacionesHijas.forEach(relacionTipoInteres -> tipoIntereses.addAll(tipoInteresesHijasEnArbol(relacionTipoInteres.getHija(), profundidad+1)));
        return tipoIntereses;
    }

    public List<InteresUsuarioDTO> register(RegistroInteresDTO dto){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(dto.getUsuarioId());
        Usuario usuario = usuarioOptional.orElseThrow(() -> new RuntimeException("User not found"));
        Optional<Interes> interesOptional = interesRepository.findOneByDescripcionIgnoreCase(dto.getInteres());

        List<TipoInteres> tipoInteresesRelacionadas = interesOptional.map(interes -> {
            Optional<Integer> optionalMax = relacionTipoInteresRepository.findMaxProfundidadByHijaId(interes.getTipoInteres().getId());
            Integer maxProfundidad = optionalMax.orElse(0);
            return tipoInteresesPadreEnArbol(interes.getTipoInteres(), maxProfundidad);
        }).orElse(Collections.emptyList());

        if(interesOptional.isPresent() && !tipoInteresesRelacionadas.isEmpty()){
            Interes interes = interesOptional.get();
            boolean containsGrupoGeneral = tipoInteresesRelacionadas.stream().anyMatch(
                tipoInteres -> tipoInteres.getDescripcion().equalsIgnoreCase(dto.getTipoPrincipal()));
            boolean containsGrupoSecundario = tipoInteresesRelacionadas.stream().anyMatch(
                tipoInteres -> tipoInteres.getDescripcion().equalsIgnoreCase(dto.getTipoSecundario()));

            if(containsGrupoGeneral && containsGrupoSecundario){
                if(interesUsuarioRepository.existsByUsuarioAndInteresDescripcionIgnoreCase(usuario, interes.getDescripcion())){
                    return Collections.emptyList();
                }
                return Collections.singletonList(interesUsuarioMapper.toDto(createInteresUsuario(usuario, interes)));
            }
            else if(containsGrupoGeneral){
                throw new RuntimeException("El tipo de interes "+dto.getTipoSecundario()+" no está relacionado con la interes: "+interes.getDescripcion());
            }
            else {
                throw new RuntimeException("El tipo de interes "+dto.getTipoPrincipal()+"  no está relacionado con la interes: "+interes.getDescripcion());
            }
        }
        else{
            Optional<TipoInteres> optional = tipoInteresRepository.findOneByDescripcionIgnoreCase(dto.getTipoPrincipal());
            return optional.map(padre -> {
                List<TipoInteres> tipoIntereses = tipoInteresesHijasEnArbol(padre, 1);
                List<TipoInteres> tipoInteresesFiltradas = tipoIntereses.stream()
                    .filter(tipoInteres -> tipoInteres.getDescripcion().equalsIgnoreCase(dto.getTipoSecundario()))
                    .collect(Collectors.toList());
                List<InteresUsuario> nuevasInteresUsuario = new ArrayList<>();
                if(!tipoInteresesFiltradas.isEmpty()){
                    tipoInteresesFiltradas.forEach(tipoInteres -> {
                        Interes nuevaInteres = createInteres(dto.getInteres(), tipoInteres);
                        nuevasInteresUsuario.add(createInteresUsuario(usuario,  nuevaInteres));
                    });
                    List<InteresUsuario> interesesUsuario = interesUsuarioRepository.saveAll(nuevasInteresUsuario);
                    interesUsuarioRepository.flush();
                    return interesesUsuario;
                }
                else{
                    TipoInteres nuevoTipoInteres = creteTipoInteres(dto.getTipoSecundario());
                    createRelacionTipoInteres(padre, nuevoTipoInteres, 1);
                    Interes nuevaInteres = createInteres(dto.getInteres(), nuevoTipoInteres);
                    InteresUsuario interesUsuario = createInteresUsuario(usuario, nuevaInteres);
                    return Collections.singletonList(interesUsuario);
                }
            }).map(interesesUsuario -> {
                List<InteresUsuarioDTO> response = new ArrayList<>();
                interesesUsuario.forEach(interesUsuario -> response.add(interesUsuarioMapper.toDto(interesUsuario)));
                return response;
            }).orElseThrow(() -> new RuntimeException("Invalid request"));
        }
    }

    private TipoInteres creteTipoInteres(String descripcion) {
        TipoInteres nuevoTipoInteres = new TipoInteres();
        nuevoTipoInteres.setDescripcion(descripcion);
        nuevoTipoInteres = tipoInteresRepository.save(nuevoTipoInteres);
        tipoInteresRepository.flush();
        return nuevoTipoInteres;
    }

    private RelacionTipoInteres createRelacionTipoInteres(TipoInteres padre, TipoInteres hija, int profundidad) {
        RelacionTipoInteres nuevaRelacionTipoInteres = new RelacionTipoInteres();
        nuevaRelacionTipoInteres.setPadre(padre);
        nuevaRelacionTipoInteres.setHija(hija);
        nuevaRelacionTipoInteres.setProfundidad(profundidad);
        nuevaRelacionTipoInteres = relacionTipoInteresRepository.save(nuevaRelacionTipoInteres);
        relacionTipoInteresRepository.flush();
        return nuevaRelacionTipoInteres;
    }

    private Interes createInteres(String interes, TipoInteres tipoInteres) {
        Interes nuevaInteres = new Interes();
        nuevaInteres.setDescripcion(interes);
        nuevaInteres.setTipoInteres(tipoInteres);
        nuevaInteres = interesRepository.save(nuevaInteres);
        interesRepository.flush();
        return nuevaInteres;
    }

    private InteresUsuario createInteresUsuario(Usuario usuario, @Valid Interes interes) {
        InteresUsuario interesUsuario = new InteresUsuario();
        interesUsuario.setUsuario(usuario);
        interesUsuario.setInteres(interes);
        interesUsuario = interesUsuarioRepository.save(interesUsuario);
        interesUsuarioRepository.flush();
        return interesUsuario;
    }

    /**
     * Save a interesUsuario.
     *
     * @param interesUsuarioDTO the entity to save.
     * @return the persisted entity.
     */
    public InteresUsuarioDTO save(InteresUsuarioDTO interesUsuarioDTO) {
        log.debug("Request to save InteresUsuario : {}", interesUsuarioDTO);
        InteresUsuario interesUsuario = interesUsuarioMapper.toEntity(interesUsuarioDTO);
        interesUsuario = interesUsuarioRepository.save(interesUsuario);
        return interesUsuarioMapper.toDto(interesUsuario);
    }

    /**
     * Get all the interesUsuarios.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<InteresUsuarioDTO> findAll() {
        log.debug("Request to get all InteresUsuarios");
        return interesUsuarioRepository.findAll().stream()
            .map(interesUsuarioMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one interesUsuario by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InteresUsuarioDTO> findOne(Long id) {
        log.debug("Request to get InteresUsuario : {}", id);
        return interesUsuarioRepository.findById(id)
            .map(interesUsuarioMapper::toDto);
    }

    /**
     * Delete the interesUsuario by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InteresUsuario : {}", id);
        interesUsuarioRepository.deleteById(id);
    }
}
