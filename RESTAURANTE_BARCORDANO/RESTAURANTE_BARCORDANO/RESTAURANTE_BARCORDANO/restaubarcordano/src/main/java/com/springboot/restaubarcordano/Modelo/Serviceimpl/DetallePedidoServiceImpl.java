package com.springboot.restaubarcordano.Modelo.Serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.restaubarcordano.Modelo.Entidades.DetallePedido;
import com.springboot.restaubarcordano.Modelo.Repository.DetallePedidoRepository;
import com.springboot.restaubarcordano.Modelo.Service.DetallePedidoService;
@Service
public class DetallePedidoServiceImpl implements DetallePedidoService{
    @Autowired
    private DetallePedidoRepository detalleRepo;
    
    @Override
    public void guardar(DetallePedido detallePedido){
        detalleRepo.save(detallePedido);
    }
    @Override
    public List<DetallePedido> listarPorPedidos(int idpedido){
        return detalleRepo.findByPedido_Idpedido(idpedido);
    }
}