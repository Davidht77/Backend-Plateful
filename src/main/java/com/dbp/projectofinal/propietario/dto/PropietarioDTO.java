package com.dbp.projectofinal.propietario.dto;

import java.util.List;

public class PropietarioDTO {
    private Long id_usuario;
    private String nombre;
    private String correo;
    private String tipoDocumento;
    private String numeroDocumento;
    private String fotoPerfil;
    private List<Long> restauranteIds;

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public List<Long> getRestauranteIds() {
        return restauranteIds;
    }

    public void setRestauranteIds(List<Long> restauranteIds) {
        this.restauranteIds = restauranteIds;
    }
}
