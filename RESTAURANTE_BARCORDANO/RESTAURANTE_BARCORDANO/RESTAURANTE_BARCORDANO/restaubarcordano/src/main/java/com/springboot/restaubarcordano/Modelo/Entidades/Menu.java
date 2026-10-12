package com.springboot.restaubarcordano.Modelo.Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="menu")
public class Menu {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idmenu;

    @Column(name="nombre")
    private String nombre;

    @Column(name="precio")
    private double precio;

    @Column(name="categoria")
    private String categoria;

    @Column(name="imagen")
    private String imagen;

    public int getIdmenu() { 
    	return idmenu; 
    }
    public void setIdmenu(int idmenu) { 
    	this.idmenu = idmenu; 
    }
    public String getNombre() { 
    	return nombre; 
    }
    public void setNombre(String nombre) { 
    	this.nombre = nombre; 
    }
    public double getPrecio() { 
    	return precio; 
    }
    public void setPrecio(double precio) { 
    	this.precio = precio; 
    }
    public String getCategoria() { 
    	return categoria; 
    }
    public void setCategoria(String categoria) { 
    	this.categoria = categoria; 
    }
    public String getImagen() { 
    	return imagen; 
    }
    public void setImagen(String imagen) { 
    	this.imagen = imagen; 
    }
}
