package com.dbp.projectofinal.plato.domain;

import jakarta.persistence.*;
import com.dbp.projectofinal.carta.domain.Carta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "platos")
public class Plato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_plato;

    private String nombre;
    private Double precio;
    private Boolean disponibilidad;

    @ManyToOne
    @JoinColumn(name = "id_carta")
    private Carta carta;  // Relación con la carta

}
