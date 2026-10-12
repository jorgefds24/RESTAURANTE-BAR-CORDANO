package com.springboot.restaubarcordano.Seguridad;

import com.springboot.restaubarcordano.Modelo.Entidades.Cliente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios_seguridad")
public class UsuarioSeguridad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    // "Rol_Administrador" o "Rol_Cliente"
    @Column(nullable = false)
    private String rol;

    @OneToOne
    @JoinColumn(name = "idcliente", nullable = true)
    private Cliente cliente;

    // Constructor vacío requerido por JPA
    public UsuarioSeguridad() {}

    // Constructor para Administradores (sin cliente)
    public UsuarioSeguridad(String username, String password, String rol) {
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.cliente = null;
    }

    // Constructor para Clientes (con cliente vinculado)
    public UsuarioSeguridad(String username, String password, String rol, Cliente cliente) {
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }

    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}