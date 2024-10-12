package com.dbp.projectofinal.restaurante.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UbiRequestDTO {
    private Double longitud;
    private Double latitud;
}
