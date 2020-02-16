package com.experis.formacion.alexa.poc.service;

import com.experis.formacion.alexa.poc.domain.Habilidad;
import com.experis.formacion.alexa.poc.domain.HabilidadUsuario;
import com.experis.formacion.alexa.poc.domain.RelacionTipoHabilidad;
import com.experis.formacion.alexa.poc.domain.TipoHabilidad;
import com.experis.formacion.alexa.poc.domain.Usuario;
import com.experis.formacion.alexa.poc.repository.HabilidadRepository;
import com.experis.formacion.alexa.poc.repository.HabilidadUsuarioRepository;
import com.experis.formacion.alexa.poc.repository.RelacionTipoHabilidadRepository;
import com.experis.formacion.alexa.poc.repository.TipoHabilidadRepository;
import com.experis.formacion.alexa.poc.repository.UsuarioRepository;
import com.experis.formacion.alexa.poc.service.dto.HabilidadUsuarioDTO;
import com.experis.formacion.alexa.poc.service.dto.RegistroHabilidadDTO;
import com.experis.formacion.alexa.poc.service.mapper.HabilidadUsuarioMapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link HabilidadUsuario}.
 */
@Service
@Transactional
public class HabilidadUsuarioService {

    private final Logger log = LoggerFactory.getLogger(HabilidadUsuarioService.class);

    private final HabilidadUsuarioRepository habilidadUsuarioRepository;
    private final HabilidadUsuarioMapper habilidadUsuarioMapper;
    private final TipoHabilidadRepository tipoHabilidadRepository;
    private final HabilidadRepository habilidadRepository;
    private final UsuarioRepository usuarioRepository;
    private final RelacionTipoHabilidadRepository relacionTipoHabilidadRepository;

    public HabilidadUsuarioService(HabilidadUsuarioRepository habilidadUsuarioRepository, HabilidadUsuarioMapper habilidadUsuarioMapper,
        TipoHabilidadRepository tipoHabilidadRepository, RelacionTipoHabilidadRepository relacionTipoHabilidadRepository,
        HabilidadRepository habilidadRepository, UsuarioRepository usuarioRepository) {
        this.habilidadUsuarioRepository = habilidadUsuarioRepository;
        this.habilidadUsuarioMapper = habilidadUsuarioMapper;
        this.tipoHabilidadRepository = tipoHabilidadRepository;
        this.relacionTipoHabilidadRepository = relacionTipoHabilidadRepository;
        this.habilidadRepository = habilidadRepository;
        this.usuarioRepository = usuarioRepository;
    }

    private List<TipoHabilidad> tipoHabilidadesPadreEnArbol(TipoHabilidad tipoHabilidad, int profundidad) {
        List<TipoHabilidad> padres = new ArrayList<>();
        padres.add(tipoHabilidad);
        List<RelacionTipoHabilidad> relacionesPadre = relacionTipoHabilidadRepository.findByHijaIdAndProfundidad(tipoHabilidad.getId(), profundidad);
        relacionesPadre.forEach(relacionTipoHabilidadPadre -> padres.addAll(tipoHabilidadesPadreEnArbol(relacionTipoHabilidadPadre.getPadre(), profundidad-1)));
        return padres;
    }

    private List<TipoHabilidad> tipoHabilidadesHijasEnArbol(TipoHabilidad tipoHabilidad, int profundidad){
        List<TipoHabilidad> tipoHabilidades = new ArrayList<>();
        tipoHabilidades.add(tipoHabilidad);
        List<RelacionTipoHabilidad> relacionesHijas = relacionTipoHabilidadRepository.findByPadreIdAndProfundidad(tipoHabilidad.getId(), profundidad);
        relacionesHijas.forEach(relacionTipoHabilidad -> tipoHabilidades.addAll(tipoHabilidadesHijasEnArbol(relacionTipoHabilidad.getHija(), profundidad+1)));
        return tipoHabilidades;
    }

    public List<HabilidadUsuarioDTO> register(RegistroHabilidadDTO dto){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(dto.getUsuarioId());
        Usuario usuario = usuarioOptional.orElseThrow(() -> new RuntimeException("User not found"));
        Optional<Habilidad> habilidadOptional = habilidadRepository.findOneByDescripcionIgnoreCase(dto.getHabilidad());

        List<TipoHabilidad> tipoHabilidadesRelacionadas = habilidadOptional.map(habilidad -> {
            Optional<Integer> optionalMax = relacionTipoHabilidadRepository.findMaxProfundidadByHijaId(habilidad.getTipoHabilidad().getId());
            Integer maxProfundidad = optionalMax.orElse(0);
            return tipoHabilidadesPadreEnArbol(habilidad.getTipoHabilidad(), maxProfundidad);
        }).orElse(Collections.emptyList());

        if(habilidadOptional.isPresent() && !tipoHabilidadesRelacionadas.isEmpty()){
            Habilidad habilidad = habilidadOptional.get();
            boolean containsGrupoGeneral = tipoHabilidadesRelacionadas.stream().anyMatch(
                tipoHabilidad -> tipoHabilidad.getDescripcion().equalsIgnoreCase(dto.getGrupoGeneral()));
            boolean containsGrupoSecundario = tipoHabilidadesRelacionadas.stream().anyMatch(
                tipoHabilidad -> tipoHabilidad.getDescripcion().equalsIgnoreCase(dto.getGrupoSecundario()));

            if(containsGrupoGeneral && containsGrupoSecundario){
                if(habilidadUsuarioRepository.existsByUsuarioAndHabilidadDescripcionIgnoreCase(usuario, habilidad.getDescripcion())){
                    return Collections.emptyList();
                }
                return Collections.singletonList(habilidadUsuarioMapper.toDto(createHabilidadUsuario(usuario, habilidad)));
            }
            else if(containsGrupoGeneral){
                throw new RuntimeException("El tipo de habilidad "+dto.getGrupoSecundario()+" no está relacionado con la habilidad: "+habilidad.getDescripcion());
            }
            else {
                throw new RuntimeException("El tipo de habilidad "+dto.getGrupoGeneral()+"  no está relacionado con la habilidad: "+habilidad.getDescripcion());
            }
        }
        else{
            Optional<TipoHabilidad> optional = tipoHabilidadRepository.findOneByDescripcionIgnoreCase(dto.getGrupoGeneral());
            return optional.map(padre -> {
                List<TipoHabilidad> tipoHabilidades = tipoHabilidadesHijasEnArbol(padre, 1);
                List<TipoHabilidad> tipoHabilidadesFiltradas = tipoHabilidades.stream()
                    .filter(tipoHabilidad -> tipoHabilidad.getDescripcion().equalsIgnoreCase(dto.getGrupoSecundario()))
                    .collect(Collectors.toList());
                List<HabilidadUsuario> nuevasHabilidadUsuario = new ArrayList<>();
                if(!tipoHabilidadesFiltradas.isEmpty()){
                    tipoHabilidadesFiltradas.forEach(tipoHabilidad -> {
                        Habilidad nuevaHabilidad = createHabilidad(dto.getHabilidad(), tipoHabilidad);
                        nuevasHabilidadUsuario.add(createHabilidadUsuario(usuario,  nuevaHabilidad));
                    });
                    List<HabilidadUsuario> habilidadesUsuario = habilidadUsuarioRepository.saveAll(nuevasHabilidadUsuario);
                    habilidadUsuarioRepository.flush();
                    return habilidadesUsuario;
                }
                else{
                    TipoHabilidad nuevoTipoHabilidad = creteTipoHabilidad(dto.getGrupoSecundario());
                    createRelacionTipoHabilidad(padre, nuevoTipoHabilidad, 1);
                    Habilidad nuevaHabilidad = createHabilidad(dto.getHabilidad(), nuevoTipoHabilidad);
                    HabilidadUsuario habilidadUsuario = createHabilidadUsuario(usuario, nuevaHabilidad);
                    return Collections.singletonList(habilidadUsuario);
                }
            }).map(habilidadesUsuario -> {
                List<HabilidadUsuarioDTO> response = new ArrayList<>();
                habilidadesUsuario.forEach(habilidadUsuario -> response.add(habilidadUsuarioMapper.toDto(habilidadUsuario)));
                return response;
            }).orElseThrow(() -> new RuntimeException("Invalid request"));
        }
    }

    private TipoHabilidad creteTipoHabilidad(String descripcion) {
        TipoHabilidad nuevoTipoHabilidad = new TipoHabilidad();
        nuevoTipoHabilidad.setDescripcion(descripcion);
        nuevoTipoHabilidad = tipoHabilidadRepository.save(nuevoTipoHabilidad);
        tipoHabilidadRepository.flush();
        return nuevoTipoHabilidad;
    }

    private RelacionTipoHabilidad createRelacionTipoHabilidad(TipoHabilidad padre, TipoHabilidad hija, int profundidad) {
        RelacionTipoHabilidad nuevaRelacionTipoHabilidad = new RelacionTipoHabilidad();
        nuevaRelacionTipoHabilidad.setPadre(padre);
        nuevaRelacionTipoHabilidad.setHija(hija);
        nuevaRelacionTipoHabilidad.setProfundidad(profundidad);
        nuevaRelacionTipoHabilidad = relacionTipoHabilidadRepository.save(nuevaRelacionTipoHabilidad);
        relacionTipoHabilidadRepository.flush();
        return nuevaRelacionTipoHabilidad;
    }

    private Habilidad createHabilidad(String habilidad, TipoHabilidad tipoHabilidad) {
        Habilidad nuevaHabilidad = new Habilidad();
        nuevaHabilidad.setDescripcion(habilidad);
        nuevaHabilidad.setTipoHabilidad(tipoHabilidad);
        nuevaHabilidad = habilidadRepository.save(nuevaHabilidad);
        habilidadRepository.flush();
        return nuevaHabilidad;
    }

    private HabilidadUsuario createHabilidadUsuario(Usuario usuario, @Valid Habilidad habilidad) {
        HabilidadUsuario habilidadUsuario = new HabilidadUsuario();
        habilidadUsuario.setUsuario(usuario);
        habilidadUsuario.setHabilidad(habilidad);
        habilidadUsuario = habilidadUsuarioRepository.save(habilidadUsuario);
        habilidadUsuarioRepository.flush();
        return habilidadUsuario;
    }

    /**
     * Save a habilidadUsuario.
     *
     * @param habilidadUsuarioDTO the entity to save.
     * @return the persisted entity.
     */
    public HabilidadUsuarioDTO save(HabilidadUsuarioDTO habilidadUsuarioDTO) {
        log.debug("Request to save HabilidadUsuario : {}", habilidadUsuarioDTO);
        HabilidadUsuario habilidadUsuario = habilidadUsuarioMapper.toEntity(habilidadUsuarioDTO);
        habilidadUsuario = habilidadUsuarioRepository.save(habilidadUsuario);
        return habilidadUsuarioMapper.toDto(habilidadUsuario);
    }

    /**
     * Get all the habilidadUsuarios.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<HabilidadUsuarioDTO> findAll() {
        log.debug("Request to get all HabilidadUsuarios");
        return habilidadUsuarioRepository.findAll().stream()
            .map(habilidadUsuarioMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one habilidadUsuario by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<HabilidadUsuarioDTO> findOne(Long id) {
        log.debug("Request to get HabilidadUsuario : {}", id);
        return habilidadUsuarioRepository.findById(id)
            .map(habilidadUsuarioMapper::toDto);
    }

    /**
     * Delete the habilidadUsuario by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete HabilidadUsuario : {}", id);
        habilidadUsuarioRepository.deleteById(id);
    }
}
