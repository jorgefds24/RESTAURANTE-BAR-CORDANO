package com.springboot.restaubarcordano.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restaubarcordano.Modelo.Entidades.Reserva;
import com.springboot.restaubarcordano.Modelo.Repository.ReservaRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/reservas")
@Tag(name = "Reservas", description = "Consulta de reservas del Restaurante Bar Cordano")
public class ReservaRestController {

    @Autowired
    private ReservaRepository reservaRepository;

    @GetMapping
    @Operation(summary = "Listar todas las reservas")
    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar reserva por ID")
    public ResponseEntity<Reserva> buscarPorId(@PathVariable Integer id) {
        Optional<Reserva> reserva = reservaRepository.findById(id);
        return reserva.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }
}