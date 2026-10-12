package com.springboot.restaubarcordano.Modelo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.restaubarcordano.Modelo.Entidades.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer>{
    List<Reserva>findByClienteIdcliente(int idcliente);
}