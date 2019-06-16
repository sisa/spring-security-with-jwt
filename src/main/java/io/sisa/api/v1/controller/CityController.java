package io.sisa.api.v1.controller;

import io.sisa.core.model.domain.City;
import io.sisa.core.model.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author isaozturk
 */

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class CityController {

    private final CityService cityService;

    @GetMapping(value = "/cities")
    @PreAuthorize("hasRole('ROLE_STANDARD')")
    public List<City> getCity(){
        return cityService.fetchAllCities();
    }


    @GetMapping(value = "/cities/{cityId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public City getCity(@PathVariable("cityId") Long cityId){
        return cityService.findById(cityId);
    }

}
