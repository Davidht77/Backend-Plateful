package com.dbp.projectofinal.ubicacion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UbicacionResponseDTO {
    private String ciudad;
    @NotNull
    private String direccionCompleta;
    @NotNull
    private Double longitud;
    @NotNull
    private Double latitud;
    private String codigoPostal;
}
