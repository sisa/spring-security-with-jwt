package io.sisa.core.model.service.impl;

import io.sisa.core.model.domain.City;
import io.sisa.core.model.repository.CityRepository;
import io.sisa.core.model.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author isaozturk
 */

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> fetchAllCities() {
        return (List<City>)cityRepository.findAll();
    }

    @Override
    public City findById(Long id) {
        return cityRepository.findOne(id);
    }
}
