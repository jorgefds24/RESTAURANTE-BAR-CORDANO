package com.springboot.restaubarcordano.Modelo.Service;

import java.util.List;

import com.springboot.restaubarcordano.Modelo.Entidades.DetallePedido;

public interface DetallePedidoService {
    public void guardar(DetallePedido detallePedido);
    List<DetallePedido> listarPorPedidos(int idpedido);
}
