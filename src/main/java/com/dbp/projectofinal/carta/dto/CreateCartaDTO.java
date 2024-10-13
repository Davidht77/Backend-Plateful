package com.dbp.projectofinal.carta.dto;

import com.dbp.projectofinal.restaurante.domain.Restaurante;

import java.time.LocalDate;

public class CreateCartaDTO {

    private String nombre;
    private LocalDate fecha_actualizacion;
    private Restaurante restaurante;

    public CreateCartaDTO() {}

    public CreateCartaDTO(String nombre, LocalDate fecha_actualizacion, Restaurante restaurante) {
        this.nombre = nombre;
        this.fecha_actualizacion = fecha_actualizacion;
        this.restaurante = restaurante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    public void setFecha_actualizacion(LocalDate fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }
}
