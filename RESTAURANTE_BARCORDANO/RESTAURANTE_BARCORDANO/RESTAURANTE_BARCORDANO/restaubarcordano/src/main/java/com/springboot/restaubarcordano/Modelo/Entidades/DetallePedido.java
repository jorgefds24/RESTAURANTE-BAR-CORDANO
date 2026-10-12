package com.springboot.restaubarcordano.Modelo.Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="detalle_pedidos")
public class DetallePedido {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int iddetalle;

    @Column(name="cantidad")
    private int cantidad;

    @Column(name="precio_unit")
    private double precio_unit;

    @Column(name="subtotal")
    private double subtotal;

    @ManyToOne
    @JoinColumn(name="idpedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name="idmenu")
    private Menu menu;

    public int getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(int iddetalle) {
        this.iddetalle = iddetalle;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_unit() {
        return precio_unit;
    }

    public void setPrecio_unit(double precio_unit) {
        this.precio_unit = precio_unit;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }


    
}
