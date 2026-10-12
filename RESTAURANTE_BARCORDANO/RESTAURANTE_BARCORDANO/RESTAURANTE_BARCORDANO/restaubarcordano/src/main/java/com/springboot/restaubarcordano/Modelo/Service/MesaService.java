package com.springboot.restaubarcordano.Modelo.Service;

import java.util.List;
import java.util.Optional;

import com.springboot.restaubarcordano.Modelo.Entidades.Mesa;

public interface MesaService {
    public List<Mesa>listar();
    public Optional<Mesa>buscarId(int id);
    public void guardar(Mesa mesa);
    public void borrar(int id);
}
