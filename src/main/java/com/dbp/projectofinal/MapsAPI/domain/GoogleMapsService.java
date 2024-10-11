package com.dbp.projectofinal.MapsAPI.domain;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.errors.ApiException;
import java.io.IOException;

public class GoogleMapsService {

    private GeoApiContext context;

    public GoogleMapsService() {
        // Crea el contexto con la API Key
        context = new GeoApiContext.Builder()
                .apiKey(System.getenv("google.maps.api.key")) // O usa application.properties
                .build();
    }

    // Método para obtener coordenadas desde una dirección
    public GeocodingResult[] getCoordinatesFromAddress(String address) throws ApiException, InterruptedException, IOException {
        return GeocodingApi.geocode(context, address).await();
    }

}

