package com.dbp.projectofinal.comentario.domain;
import jakarta.persistence.*;
import com.dbp.projectofinal.resena.domain.Resena;
import com.dbp.projectofinal.usuario.domain.Usuario;
import java.util.Date;

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

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    public Long getId_comentario() {
        return id_comentario;
    }

    public void setId_comentario(Long id_comentario) {
        this.id_comentario = id_comentario;
    }

    public Resena getResena() {
        return resena;
    }

    public void setResena(Resena resena) {
        this.resena = resena;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
