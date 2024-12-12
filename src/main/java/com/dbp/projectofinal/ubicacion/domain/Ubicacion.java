package com.dbp.projectofinal.ubicacion.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ubicaciones")
public class Ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ubicacion;
    private String ciudad;
    private String direccionCompleta;
    private Double longitud;
    private Double latitud;
    private String codigoPostal;

    public Ubicacion(Long id_ubicacion) {
        this.id_ubicacion = id_ubicacion;
    }
}
