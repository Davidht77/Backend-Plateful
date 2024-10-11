package com.dbp.projectofinal.ubicacion;

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
    private String longitud;
    private String latitud;
    private LocalDate codigoPostal;
    private LocalDate fechaCreacion;

    public Ubicacion(Long id_ubicacion) {
        this.id_ubicacion = id_ubicacion;
    }
}
