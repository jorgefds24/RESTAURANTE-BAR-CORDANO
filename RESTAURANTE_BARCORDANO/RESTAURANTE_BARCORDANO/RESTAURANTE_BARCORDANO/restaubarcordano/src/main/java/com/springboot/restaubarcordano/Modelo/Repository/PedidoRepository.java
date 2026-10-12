package com.springboot.restaubarcordano.Modelo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.restaubarcordano.Modelo.Entidades.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
    List<Pedido>findByClienteIdcliente(int idcliente);
}