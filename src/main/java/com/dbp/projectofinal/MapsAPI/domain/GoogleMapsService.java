package com.dbp.projectofinal.MapsAPI.domain;

import com.dbp.projectofinal.exceptions.UbicacionNotFoundException;
import com.dbp.projectofinal.ubicacion.dto.UbicacionResponseDTO;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.GeocodingResult;
import com.google.maps.errors.ApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;

import java.io.IOException;

@Service
public class GoogleMapsService {

    @Value("${key}")
    private String encripted = "QUl6YVN5QnhLSnRlakNMUFgzZ2NKSEhWUHh3azR1Vkd6eVRrWWpj";

    private final GeoApiContext context;

    private String decodeApiKey(String encryptedKey) {
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedKey);
        return new String(decodedBytes);
    }

    public GoogleMapsService() {
        String apikey = decodeApiKey(encripted);
        context = new GeoApiContext.Builder()
                .apiKey(apikey)
                .build();
    }

    public UbicacionResponseDTO getUbicationDetails(String address) throws ApiException, InterruptedException, IOException {
        GeocodingResult[] results = GeocodingApi.geocode(context, address).await();
        if (results != null && results.length > 0) {
            GeocodingResult result = results[0];

            Double latitud = result.geometry.location.lat;
            Double longitud = result.geometry.location.lng;

            String fullAddress = result.formattedAddress;

            String city = null;
            String postalCode = null;

            for (AddressComponent component : result.addressComponents) {
                if (component.types[0] == AddressComponentType.LOCALITY) {
                    city = component.longName;
                }
                if (component.types[0] == AddressComponentType.POSTAL_CODE) {
                    postalCode = component.longName;
                }
            }
            return new UbicacionResponseDTO(city,fullAddress,latitud,longitud,postalCode);
        }
         throw new UbicacionNotFoundException("");
    }
}