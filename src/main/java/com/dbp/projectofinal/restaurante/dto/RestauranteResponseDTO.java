package com.dbp.projectofinal.restaurante.dto;

import com.dbp.projectofinal.ubicacion.dto.UbicacionResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RestauranteResponseDTO {
    private Long id;
    private String nombre_restaurante;
    private String horario;
    private String tipoRestaurante;
    private Double calificacion_promedio;
    private UbicacionResponseDTO ubicacion;
}
