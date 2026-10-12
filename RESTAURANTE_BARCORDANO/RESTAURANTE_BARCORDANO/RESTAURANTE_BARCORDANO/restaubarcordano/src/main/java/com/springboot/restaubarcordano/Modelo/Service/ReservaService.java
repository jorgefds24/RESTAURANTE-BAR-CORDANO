package com.springboot.restaubarcordano.Modelo.Service;

import java.util.List;
import java.util.Optional;

import com.springboot.restaubarcordano.Modelo.Entidades.Reserva;

public interface ReservaService {
    public List<Reserva> listar();
    List<Reserva> listarPorCliente(int idcliente);
    public Optional<Reserva>buscarId(int id);
    public void guardar(Reserva reserva);
    public void borrar(int id);
}    
