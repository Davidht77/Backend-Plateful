package com.dbp.projectofinal.ubicacion.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UbicacionDTO {
    private Long id_ubicacion;
    private String ciudad;
    private String direccionCompleta;
    private String longitud;
    private String latitud;
}
