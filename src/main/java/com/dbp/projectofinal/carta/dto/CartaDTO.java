package com.dbp.projectofinal.carta.dto;

import java.time.LocalDate;

public class CartaDTO {

    private Long id_carta;
    private String nombre;
    private LocalDate fecha_actualizacion;
    private String nombre_restaurante;

    public CartaDTO() {}

    public CartaDTO(Long id_carta, String nombre, LocalDate fecha_actualizacion, String nombre_restaurante) {
        this.id_carta = id_carta;
        this.nombre = nombre;
        this.fecha_actualizacion = fecha_actualizacion;
        this.nombre_restaurante = nombre_restaurante;
    }

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

    public LocalDate getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    public void setFecha_actualizacion(LocalDate fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }

    public String getNombre_restaurante() {
        return nombre_restaurante;
    }

    public void setNombre_restaurante(String nombre_restaurante) {
        this.nombre_restaurante = nombre_restaurante;
    }
}
