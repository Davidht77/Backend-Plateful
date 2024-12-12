package com.dbp.projectofinal.ubicacion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUbicacionDTO {
    private String ciudad;
    private String direccionCompleta;
    private Double longitud;
    private Double latitud;
}
