package io.sisa.core.model.service;

import io.sisa.core.model.domain.City;

import java.util.List;

/**
 * @author isaozturk
 */

public interface CityService{

    List<City> fetchAllCities();

    City findById(Long id);

    void save(City city);
}
