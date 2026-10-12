package com.springboot.restaubarcordano.Modelo.Serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.restaubarcordano.Modelo.Entidades.Mesa;
import com.springboot.restaubarcordano.Modelo.Repository.MesaRepository;
import com.springboot.restaubarcordano.Modelo.Service.MesaService;
@Service
public class MesaServiceImpl implements MesaService{
    @Autowired
    private MesaRepository repository;

    @Override
    public List<Mesa> listar() {
        return repository.findAll();
    }
    @Override
    public Optional<Mesa> buscarId(int id) {
        return repository.findById(id);
    }
    @Override
    public void guardar(Mesa mesa) {
        repository.save(mesa);
    }
    @Override
    public void borrar(int id) {
        repository.deleteById(id);
    }
}