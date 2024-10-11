package com.dbp.projectofinal.carta.domain;

import jakarta.persistence.*;
import com.dbp.projectofinal.restaurante.domain.Restaurante;
import java.util.Date;

@Entity
@Table(name = "cartas")
public class Carta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_carta;

    private String nombre;

    @Temporal(TemporalType.DATE)
    private Date fecha_actualizacion;

    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;

    // Getters y Setters
    public Long getId_carta() {
        return id_carta;
    }

    public void setId_carta(Long id_carta) {
        this.id_carta = id_carta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    public void setFecha_actualizacion(Date fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }
}
