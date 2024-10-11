package com.dbp.projectofinal.resena.domain;

import jakarta.persistence.*;
import com.dbp.projectofinal.usuario.domain.Usuario;
import com.dbp.projectofinal.restaurante.domain.Restaurante;
import java.util.Date;

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

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    // Getters y Setters
    public Long getId_resena() {
        return id_resena;
    }

    public void setId_resena(Long id_resena) {
        this.id_resena = id_resena;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
