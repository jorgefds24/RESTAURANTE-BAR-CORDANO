package com.springboot.restaubarcordano.Modelo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.restaubarcordano.Modelo.Entidades.Mesa;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Integer>{
}