package com.springboot.restaubarcordano.Modelo.Service;

import java.util.List;
import java.util.Optional;

import com.springboot.restaubarcordano.Modelo.Entidades.Menu;

public interface MenuService {
    public List<Menu>listar();
    public List<Menu>listarPorCategoria(String categoria);
    public void guardar(Menu menu);
    public Optional<Menu>buscarId(int id);
    public void borrar(int id);
}
