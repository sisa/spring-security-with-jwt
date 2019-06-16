package io.sisa.core.model.repository;

import io.sisa.core.model.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author isaozturk
 */

public interface CityRepository extends JpaRepository<City, Long> {

}
