package com.springboot.restaubarcordano.Modelo.Entidades;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="reservas")
public class Reserva {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idreserva;
	
	@Column(name="fecha")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate fecha;
	@Column(name="hora")
	@DateTimeFormat(pattern="HH:mm")
	private LocalTime hora;
	@Column(name="num_personas")
	private int num_personas;
	@Column(name="estado")
	private String estado;
	@Column(name="observaciones")
	private String observaciones;
	
	@ManyToOne
	@JoinColumn(name="idmesa")
	private Mesa mesa;
	
	@ManyToOne
	@JoinColumn(name="idcliente")
	private Cliente cliente;

	public int getIdreserva() {
		return idreserva;
	}

	public void setIdreserva(int idreserva) {
		this.idreserva = idreserva;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public int getNum_personas() {
		return num_personas;
	}

	public void setNum_personas(int num_personas) {
		this.num_personas = num_personas;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}
