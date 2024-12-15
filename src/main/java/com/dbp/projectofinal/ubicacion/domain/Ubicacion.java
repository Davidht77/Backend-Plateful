package com.dbp.projectofinal.ubicacion.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
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

    public Ubicacion(Double latitud, Double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

}
