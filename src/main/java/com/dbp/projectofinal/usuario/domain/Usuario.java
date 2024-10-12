package com.dbp.projectofinal.usuario.domain;

import com.dbp.projectofinal.ubicacion.domain.Ubicacion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    private String nombre;
    @Column(unique = true)
    private String email;
    private String password;
    private String telefono;
    private LocalDate fechaNacimiento;
    private LocalDate fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "id_ubicacion")
    private Ubicacion ubicacion;

    public Usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }
    public Usuario(String nombre, String email, String password, String telefono, LocalDate fechaNacimiento, Ubicacion ubicacion) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaCreacion = LocalDate.now();
        this.ubicacion = ubicacion;
    }
}
