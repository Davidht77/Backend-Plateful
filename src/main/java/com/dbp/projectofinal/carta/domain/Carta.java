package com.dbp.projectofinal.carta.domain;

import com.dbp.projectofinal.plato.domain.Plato;
import jakarta.persistence.*;
import com.dbp.projectofinal.restaurante.domain.Restaurante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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

    @OneToMany(mappedBy = "carta")
    private List<Plato> platos;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_restaurante", nullable = false)
    private Restaurante restaurante;

    public Carta(Long id_carta) {
        this.id_carta = id_carta;
    }

}
