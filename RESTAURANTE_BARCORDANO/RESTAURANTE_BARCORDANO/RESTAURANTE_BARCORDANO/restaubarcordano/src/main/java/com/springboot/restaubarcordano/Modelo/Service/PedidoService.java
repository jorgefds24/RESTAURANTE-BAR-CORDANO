package com.springboot.restaubarcordano.Modelo.Service;

import java.util.List;
import java.util.Optional;

import com.springboot.restaubarcordano.Modelo.Entidades.Pedido;

public interface PedidoService {
    public List<Pedido>listar();
    List<Pedido>listarPorCliente(int idcliente);
    public Optional<Pedido>buscarId(int id);
    public void guardar(Pedido pedido);
    public void borrar(int id);
}
