package com.springboot.restaubarcordano.Modelo.Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="clientes")
public class Cliente {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idcliente;
	
	@Column(name="dnicliente")
	private String dnicliente;
	@Column(name="nomcliente")
	private String nomcliente;
	@Column(name="telcliente")
	private String telcliente;
	@Column(name="corcliente")
	private String corcliente;
	public int getIdcliente() {
		return idcliente;
	}
	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}
	public String getDnicliente() {
		return dnicliente;
	}
	public void setDnicliente(String dnicliente) {
		this.dnicliente = dnicliente;
	}
	public String getNomcliente() {
		return nomcliente;
	}
	public void setNomcliente(String nomcliente) {
		this.nomcliente = nomcliente;
	}
	public String getTelcliente() {
		return telcliente;
	}
	public void setTelcliente(String telcliente) {
		this.telcliente = telcliente;
	}
	public String getCorcliente() {
		return corcliente;
	}
	public void setCorcliente(String corcliente) {
		this.corcliente = corcliente;
	}
	
	
}
