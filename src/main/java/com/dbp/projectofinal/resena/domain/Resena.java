package com.dbp.projectofinal.resena.domain;

import com.dbp.projectofinal.comentario.domain.Comentario;
import jakarta.persistence.*;
import com.dbp.projectofinal.usuario.domain.Usuario;
import com.dbp.projectofinal.restaurante.domain.Restaurante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

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

    private String contenido;

    @OneToMany(mappedBy = "resena", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_restaurante")
    private Restaurante restaurante;

    private LocalDate fecha = LocalDate.now();

    public Resena(Long id_resena) {
        this.id_resena = id_resena;
    }
}
