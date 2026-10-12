package com.springboot.restaubarcordano.Modelo.Serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.restaubarcordano.Modelo.Entidades.Reserva;
import com.springboot.restaubarcordano.Modelo.Repository.ReservaRepository;
import com.springboot.restaubarcordano.Modelo.Service.ReservaService;
@Service
public class ReservaServiceImpl implements ReservaService{
    @Autowired
    private ReservaRepository repository;

    @Override
    public List<Reserva> listar() {
        return repository.findAll();
    }
    @Override
    public Optional<Reserva> buscarId(int id) {
        return repository.findById(id);
    }
    @Override
    public void guardar(Reserva reserva) {
        repository.save(reserva);
    }
    @Override
    public void borrar(int id) {
        repository.deleteById(id);
    }
    @Override
    public List<Reserva> listarPorCliente(int idcliente) {
        return repository.findByClienteIdcliente(idcliente);
    }
}