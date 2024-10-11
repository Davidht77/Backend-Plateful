package com.dbp.projectofinal.MapsAPI.application;

import com.dbp.projectofinal.MapsAPI.domain.GoogleMapsService;
import com.google.maps.model.GeocodingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MapsController {

    private final GoogleMapsService googleMapsService = new GoogleMapsService();


    @GetMapping("/geocode")
    public String geocodeAddress(@RequestParam String address) {
        try {
            GeocodingResult[] results = googleMapsService.getCoordinatesFromAddress(address);
            return results[0].geometry.location.toString();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}

