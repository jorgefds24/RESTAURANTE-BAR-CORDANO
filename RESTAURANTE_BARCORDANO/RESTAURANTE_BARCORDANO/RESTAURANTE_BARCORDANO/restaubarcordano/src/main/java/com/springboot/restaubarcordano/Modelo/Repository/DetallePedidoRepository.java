package com.springboot.restaubarcordano.Modelo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.restaubarcordano.Modelo.Entidades.DetallePedido;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer>{
    List<DetallePedido> findByPedido_Idpedido(int idpedido);
}

