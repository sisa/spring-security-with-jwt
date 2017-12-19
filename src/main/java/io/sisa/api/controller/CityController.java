package io.sisa.api.controller;

import io.sisa.core.model.domain.City;
import io.sisa.core.model.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author isaozturk
 */

@RestController
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @RequestMapping(value ="/cities", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_STANDARD')")
    public List<City> getCity(){
        return cityService.fetchAllCities();
    }


    @RequestMapping(value ="/cities/{cityId}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public City getCity(@PathVariable("cityId") Long cityId){
        return cityService.findById(cityId);
    }

}
