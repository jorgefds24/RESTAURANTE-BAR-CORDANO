package com.springboot.restaubarcordano.Modelo.Serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.restaubarcordano.Modelo.Entidades.Menu;
import com.springboot.restaubarcordano.Modelo.Repository.MenuRepository;
import com.springboot.restaubarcordano.Modelo.Service.MenuService;
@Service
public class MenuServiceImpl implements MenuService{
    @Autowired
    private MenuRepository menuRepository;
    
    @Override
    public List<Menu> listar(){
        return menuRepository.findAll();
    }
    @Override
    public List<Menu> listarPorCategoria(String categoria){
        return menuRepository.findByCategoria(categoria);
    }
    @Override
    public void guardar(Menu menu){
        menuRepository.save(menu);
    }
    @Override
    public Optional<Menu> buscarId(int id){
        return menuRepository.findById(id);
    }
    @Override
    public void borrar(int id){
        menuRepository.deleteById(id);
    }
}