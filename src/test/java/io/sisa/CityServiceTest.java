package io.sisa;

import io.sisa.core.model.domain.City;
import io.sisa.core.model.service.CityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by isaozturk on 17.06.2019
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CityServiceTest {

    @Autowired
    private CityService cityService;

    @Test
    public void fetchAllCitiesTest() {

        final List<City> cities = cityService.fetchAllCities();
        assertThat(cities).isNotNull();
    }

    @Test
    public void findCityByIdTest() {

        final City city = cityService.findById(1L);
        assertThat(city).isNotNull();
    }


    @Test
    public void saveCityTest() {

        final City city = new City();
        city.setName("Bartin");
        cityService.save(city);
        assertThat(city.getId()).isNotNull();
    }
}