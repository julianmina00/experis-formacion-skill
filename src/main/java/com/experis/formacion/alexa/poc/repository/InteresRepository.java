package com.experis.formacion.alexa.poc.repository;

import com.experis.formacion.alexa.poc.domain.Interes;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Interes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InteresRepository extends JpaRepository<Interes, Long> {

    Optional<Interes> findOneByDescripcionIgnoreCase(String interes);

}
