package com.dbp.projectofinal.usuario.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // Ejemplos de nombres: "PROPIETARIO", "CLIENTE"

    @ManyToMany(mappedBy = "roles")
    List<Usuario> usuarios;

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
