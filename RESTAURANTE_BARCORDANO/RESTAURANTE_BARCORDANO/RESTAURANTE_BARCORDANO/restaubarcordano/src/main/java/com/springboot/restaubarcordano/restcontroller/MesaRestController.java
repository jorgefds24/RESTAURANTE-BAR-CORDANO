package com.springboot.restaubarcordano.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restaubarcordano.Modelo.Entidades.Mesa;
import com.springboot.restaubarcordano.Modelo.Repository.MesaRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/mesas")
@Tag(name = "Mesas", description = "Consulta de mesas del Restaurante Bar Cordano")
public class MesaRestController {

    @Autowired
    private MesaRepository mesaRepository;

    @GetMapping
    @Operation(summary = "Listar todas las mesas")
    public List<Mesa> listarMesas() {
        return mesaRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar mesa por ID")
    public ResponseEntity<Mesa> buscarPorId(@PathVariable Integer id) {
        Optional<Mesa> mesa = mesaRepository.findById(id);
        return mesa.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
}