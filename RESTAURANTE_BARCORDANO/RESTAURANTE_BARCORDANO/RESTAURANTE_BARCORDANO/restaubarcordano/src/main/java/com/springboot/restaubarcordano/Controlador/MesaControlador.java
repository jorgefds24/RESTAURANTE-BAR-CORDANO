package com.springboot.restaubarcordano.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.springboot.restaubarcordano.Modelo.Entidades.Mesa;
import com.springboot.restaubarcordano.Modelo.Service.MesaService;


@Controller
public class MesaControlador {

    @Autowired
    private MesaService mesaService;

    @GetMapping("/indexmes")
    public String index(Model modelo) {
        List<Mesa> lista = mesaService.listar();
        modelo.addAttribute("listaMesas", lista);
        return "indexmes";
    }

    @GetMapping("/nuevomes")
    public String nuevo(Model modelo) {
        modelo.addAttribute("mesa", new Mesa());
        return "viewaddmes";
    }

    @GetMapping("/guardarmes")
    public String guardar(@ModelAttribute Mesa mesa) {
        mesaService.guardar(mesa);
        return "redirect:/indexmes";
    }

    @GetMapping("/editarmes/{id}")
    public String editar(@PathVariable int id, Model modelo) {
        Mesa mesa = mesaService.buscarId(id).get();
        modelo.addAttribute("mesa", mesa);
        return "viewaddmes";
    }

    @GetMapping("/borrarmes/{id}")
    public String borrar(@PathVariable int id) {
        mesaService.borrar(id);
        return "redirect:/indexmes";
    }
}
