package com.dbp.projectofinal.ubicacion.domain;

import com.dbp.projectofinal.resena.domain.Resena;
import com.dbp.projectofinal.restaurante.domain.Restaurante;
import com.dbp.projectofinal.usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name = "ubicaciones")
public class Ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ubicacion;
    private String ciudad;
    private String direccionCompleta;
    private Double longitud;
    private Double latitud;
    private String codigoPostal;

    @OneToMany(mappedBy = "ubicacion")
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "ubicacion")
    private List<Restaurante> restaurantes;

    public Ubicacion(String ciudad, String direccionCompleta, Double longitud, Double latitud) {
        this.ciudad = ciudad;
        this.direccionCompleta = direccionCompleta;
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public Ubicacion(Long id_ubicacion, String ciudad, String direccionCompleta, Double longitud, Double latitud, String codigoPostal) {
        this.id_ubicacion = id_ubicacion;
        this.ciudad = ciudad;
        this.direccionCompleta = direccionCompleta;
        this.longitud = longitud;
        this.latitud = latitud;
        this.codigoPostal = codigoPostal;
    }

    public Ubicacion(Long id_ubicacion) {
        this.id_ubicacion = id_ubicacion;
    }

    public Ubicacion(Double latitud, Double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

}
