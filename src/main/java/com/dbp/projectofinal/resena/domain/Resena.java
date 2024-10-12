package com.dbp.projectofinal.resena.domain;

import jakarta.persistence.*;
import com.dbp.projectofinal.usuario.domain.Usuario;
import com.dbp.projectofinal.restaurante.domain.Restaurante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "resenas")
public class Resena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_resena;

    private int calificacion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;

    private LocalDate fecha;

    public Resena(Long id_resena) {
        this.id_resena = id_resena;
    }
}
