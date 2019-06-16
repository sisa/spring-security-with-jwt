package io.sisa.core.model.service.impl;

import io.sisa.core.model.domain.City;
import io.sisa.core.model.repository.CityRepository;
import io.sisa.core.model.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * @author isaozturk
 */

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    public List<City> fetchAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public City findById(Long id) {
        return cityRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void save(City city) {
        cityRepository.save(city);
    }
}
