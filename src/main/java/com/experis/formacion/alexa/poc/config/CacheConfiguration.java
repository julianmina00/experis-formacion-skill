package com.experis.formacion.alexa.poc.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.experis.formacion.alexa.poc.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.experis.formacion.alexa.poc.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.experis.formacion.alexa.poc.domain.User.class.getName());
            createCache(cm, com.experis.formacion.alexa.poc.domain.Authority.class.getName());
            createCache(cm, com.experis.formacion.alexa.poc.domain.User.class.getName() + ".authorities");
            createCache(cm, com.experis.formacion.alexa.poc.domain.Curso.class.getName());
            createCache(cm, com.experis.formacion.alexa.poc.domain.Curso.class.getName() + ".cursoPlanFormativos");
            createCache(cm, com.experis.formacion.alexa.poc.domain.Curso.class.getName() + ".cursoUsuarios");
            createCache(cm, com.experis.formacion.alexa.poc.domain.Curso.class.getName() + ".contenidoCursos");
            createCache(cm, com.experis.formacion.alexa.poc.domain.PlanFormativo.class.getName());
            createCache(cm, com.experis.formacion.alexa.poc.domain.PlanFormativo.class.getName() + ".cursoPlanFormativos");
            createCache(cm, com.experis.formacion.alexa.poc.domain.PlanFormativo.class.getName() + ".perfilPlanFormativos");
            createCache(cm, com.experis.formacion.alexa.poc.domain.PlanFormativo.class.getName() + ".planFormativoUsuarios");
            createCache(cm, com.experis.formacion.alexa.poc.domain.TipoHabilidad.class.getName());
            createCache(cm, com.experis.formacion.alexa.poc.domain.TipoHabilidad.class.getName() + ".habilidads");
            createCache(cm, com.experis.formacion.alexa.poc.domain.TipoInteres.class.getName());
            createCache(cm, com.experis.formacion.alexa.poc.domain.TipoInteres.class.getName() + ".interes");
            createCache(cm, com.experis.formacion.alexa.poc.domain.Usuario.class.getName());
            createCache(cm, com.experis.formacion.alexa.poc.domain.Usuario.class.getName() + ".cursoUsuarios");
            createCache(cm, com.experis.formacion.alexa.poc.domain.Usuario.class.getName() + ".habilidadUsuarios");
            createCache(cm, com.experis.formacion.alexa.poc.domain.Usuario.class.getName() + ".interesUsuarios");
            createCache(cm, com.experis.formacion.alexa.poc.domain.Usuario.class.getName() + ".planFormativoUsuarios");
            createCache(cm, com.experis.formacion.alexa.poc.domain.Habilidad.class.getName());
            createCache(cm, com.experis.formacion.alexa.poc.domain.Habilidad.class.getName() + ".perfilPlanFormativos");
            createCache(cm, com.experis.formacion.alexa.poc.domain.Habilidad.class.getName() + ".habilidadUsuarios");
            createCache(cm, com.experis.formacion.alexa.poc.domain.Habilidad.class.getName() + ".contenidoCursos");
            createCache(cm, com.experis.formacion.alexa.poc.domain.Interes.class.getName());
            createCache(cm, com.experis.formacion.alexa.poc.domain.Interes.class.getName() + ".perfilPlanFormativos");
            createCache(cm, com.experis.formacion.alexa.poc.domain.Interes.class.getName() + ".interesUsuarios");
            createCache(cm, com.experis.formacion.alexa.poc.domain.Interes.class.getName() + ".contenidoCursos");
            createCache(cm, com.experis.formacion.alexa.poc.domain.CursoPlanFormativo.class.getName());
            createCache(cm, com.experis.formacion.alexa.poc.domain.PerfilPlanFormativo.class.getName());
            createCache(cm, com.experis.formacion.alexa.poc.domain.CursoUsuario.class.getName());
            createCache(cm, com.experis.formacion.alexa.poc.domain.HabilidadUsuario.class.getName());
            createCache(cm, com.experis.formacion.alexa.poc.domain.InteresUsuario.class.getName());
            createCache(cm, com.experis.formacion.alexa.poc.domain.PlanFormativoUsuario.class.getName());
            createCache(cm, com.experis.formacion.alexa.poc.domain.ContenidoCurso.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cm.destroyCache(cacheName);
        }
        cm.createCache(cacheName, jcacheConfiguration);
    }

}
