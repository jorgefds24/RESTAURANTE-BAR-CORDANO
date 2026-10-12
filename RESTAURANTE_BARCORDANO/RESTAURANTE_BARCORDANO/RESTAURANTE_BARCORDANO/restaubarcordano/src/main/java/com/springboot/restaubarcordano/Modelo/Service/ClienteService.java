package com.springboot.restaubarcordano.Modelo.Service;

import java.util.List;
import java.util.Optional;

import com.springboot.restaubarcordano.Modelo.Entidades.Cliente;

public interface ClienteService {
    public List<Cliente>listar();
    public Optional<Cliente>buscarId(int id);
    public void guardar(Cliente cliente);
    public void borrar(int id);
}
