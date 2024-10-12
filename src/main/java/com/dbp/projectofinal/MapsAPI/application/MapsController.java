package com.dbp.projectofinal.MapsAPI.application;

import com.dbp.projectofinal.MapsAPI.domain.GoogleMapsService;
import com.dbp.projectofinal.exceptions.UbicacionNotFoundException;
import com.dbp.projectofinal.ubicacion.dto.UbicacionResponseDTO;
import com.google.maps.errors.ApiException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MapsController {

    private final GoogleMapsService googleMapsService = new GoogleMapsService();


    @GetMapping("/geocode")
    public UbicacionResponseDTO geocodeAddress(@RequestParam String address) throws IOException, InterruptedException, ApiException, UbicacionNotFoundException {
        return googleMapsService.getUbicationDetails(address);
    }
}