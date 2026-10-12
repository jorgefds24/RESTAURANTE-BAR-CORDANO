package com.springboot.restaubarcordano.Modelo.Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="mesas")
public class Mesa {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idmesa;
	
	@Column(name="numero")
	private int numero;
	@Column(name="capacidad")
	private int capacidad;
	@Column(name="disponible")
	private boolean disponible;
	@Column(name="ubicacion")
	private String ubicacion;
	public int getIdmesa() {
		return idmesa;
	}
	public void setIdmesa(int idmesa) {
		this.idmesa = idmesa;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	public boolean isDisponible() {
		return disponible;
	}
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
}
