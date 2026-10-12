package com.springboot.restaubarcordano.Modelo.Serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.restaubarcordano.Modelo.Entidades.Cliente;
import com.springboot.restaubarcordano.Modelo.Repository.ClienteRepository;
import com.springboot.restaubarcordano.Modelo.Service.ClienteService;
@Service
public class ClienteServiceImpl implements ClienteService{
    @Autowired
    private ClienteRepository repository;
    
    @Override
    public List<Cliente> listar(){
        return repository.findAll();
    }
    @Override
    public Optional<Cliente> buscarId(int id) {
        return repository.findById(id);
    }
    @Override
    public void guardar(Cliente cliente) {
        repository.save(cliente);
    }
    @Override
    public void borrar(int id) {
        repository.deleteById(id);
    }
}