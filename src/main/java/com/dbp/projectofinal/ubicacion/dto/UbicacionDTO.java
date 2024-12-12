package com.dbp.projectofinal.ubicacion.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UbicacionDTO {
    private String ciudad;
    private String direccionCompleta;
    private Double longitud;
    private Double latitud;
}
