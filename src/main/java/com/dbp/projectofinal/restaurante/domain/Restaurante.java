package com.dbp.projectofinal.restaurante.domain;
import jakarta.persistence.*;
import com.dbp.projectofinal.usuario.domain.Usuario;

@Entity
@Table(name = "restaurantes")
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_restaurante;

    private String nombre_restaurante;
    private String ubicacion;
    private String horario;
    private String tipoRestaurante;

    @ManyToOne
    @JoinColumn(name = "propietario_id")
    private Usuario propietario;

    private Double calificacion_promedio;

    public Long getId_restaurante() {
        return id_restaurante;
    }

    public void setId_restaurante(Long id_restaurante) {
        this.id_restaurante = id_restaurante;
    }

    public String getNombre_restaurante() {
        return nombre_restaurante;
    }

    public void setNombre_restaurante(String nombre_restaurante) {
        this.nombre_restaurante = nombre_restaurante;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getTipoRestaurante() {
        return tipoRestaurante;
    }

    public void setTipoRestaurante(String tipoRestaurante) {
        this.tipoRestaurante = tipoRestaurante;
    }

    public Usuario getPropietario() {
        return propietario;
    }

    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }

    public Double getCalificacion_promedio() {
        return calificacion_promedio;
    }

    public void setCalificacion_promedio(Double calificacion_promedio) {
        this.calificacion_promedio = calificacion_promedio;
    }
}
