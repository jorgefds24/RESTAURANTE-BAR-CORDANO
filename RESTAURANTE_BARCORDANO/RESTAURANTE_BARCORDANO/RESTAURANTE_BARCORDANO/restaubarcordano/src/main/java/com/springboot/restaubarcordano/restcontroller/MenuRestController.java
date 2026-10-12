package com.springboot.restaubarcordano.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restaubarcordano.Modelo.Entidades.Menu;
import com.springboot.restaubarcordano.Modelo.Repository.MenuRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/menu")
@Tag(name = "Menú", description = "Consulta del catálogo de platos del Restaurante Bar Cordano")
public class MenuRestController {

    @Autowired
    private MenuRepository menuRepository;

    @GetMapping
    @Operation(summary = "Listar todos los platos del menú")
    public List<Menu> listarMenu() {
        return menuRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar plato por ID")
    public ResponseEntity<Menu> buscarPorId(@PathVariable Integer id) {
        Optional<Menu> menu = menuRepository.findById(id);
        return menu.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
}