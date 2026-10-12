package com.springboot.restaubarcordano.Modelo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.restaubarcordano.Modelo.Entidades.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
}