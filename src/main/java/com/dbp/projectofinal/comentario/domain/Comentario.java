package com.dbp.projectofinal.comentario.domain;
import jakarta.persistence.*;
import com.dbp.projectofinal.resena.domain.Resena;
import com.dbp.projectofinal.usuario.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "comentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_comentario;

    @ManyToOne
    @JoinColumn(name = "resena_id")
    private Resena resena;  // Relación con la reseña

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;  // Relación con el usuario que hace el comentario

    private String contenido;

    private LocalDate fecha;

}
