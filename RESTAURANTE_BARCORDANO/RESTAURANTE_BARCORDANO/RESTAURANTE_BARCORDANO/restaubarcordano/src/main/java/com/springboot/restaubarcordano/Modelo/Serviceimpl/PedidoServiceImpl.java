package com.springboot.restaubarcordano.Modelo.Serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.restaubarcordano.Modelo.Entidades.Pedido;
import com.springboot.restaubarcordano.Modelo.Repository.PedidoRepository;
import com.springboot.restaubarcordano.Modelo.Service.PedidoService;
@Service
public class PedidoServiceImpl implements PedidoService{
    @Autowired
    private PedidoRepository repository;

    @Override
    public List<Pedido> listar() {
        return repository.findAll();
    }
    @Override
    public Optional<Pedido> buscarId(int id) {
        return repository.findById(id);
    }
    @Override
    public void guardar(Pedido pedido) {
        repository.save(pedido);
    }
    @Override
    public void borrar(int id) {
        repository.deleteById(id);
    }
    @Override
    public List<Pedido> listarPorCliente(int idcliente) {
        return repository.findByClienteIdcliente(idcliente);
    }
}