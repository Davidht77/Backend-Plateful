package com.dbp.projectofinal.carta.domain;

import jakarta.persistence.*;
import com.dbp.projectofinal.restaurante.domain.RestauranteDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cartas")
public class Carta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_carta;

    private String nombre;

    private LocalDate fecha_actualizacion;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_restaurante", nullable = false)
    private RestauranteDTO restaurante;

    public Carta(Long id_carta) {
        this.id_carta = id_carta;
    }

}
