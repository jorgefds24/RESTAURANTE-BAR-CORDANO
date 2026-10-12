package com.springboot.restaubarcordano.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot.restaubarcordano.Modelo.Entidades.Cliente;
import com.springboot.restaubarcordano.Modelo.Entidades.Mesa;
import com.springboot.restaubarcordano.Modelo.Entidades.Reserva;
import com.springboot.restaubarcordano.Modelo.Service.ClienteService;
import com.springboot.restaubarcordano.Modelo.Service.MesaService;
import com.springboot.restaubarcordano.Modelo.Service.ReservaService;



@Controller
public class ReservaControlador {
	@Autowired
	private ReservaService reservaService;

	@Autowired
	private MesaService mesaService;
	
	@Autowired
	private ClienteService clienteservice;
	
	@GetMapping("/indexres")
	public String index(Model modelo) {
		List<Reserva>lista=reservaService.listar();
		modelo.addAttribute("listaReservas", lista);
		return "indexres";
		
	}
	
	@GetMapping("/nuevores")
	public String nuevo(Model modelo) {
		modelo.addAttribute("reserva", new Reserva());
		List<Cliente>listaClientes=clienteservice.listar();
		List<Mesa>listaMesas=mesaService.listar();
		modelo.addAttribute("listaClientes", listaClientes);
		modelo.addAttribute("listaMesas", listaMesas);
		return "viewaddres";
		
	}
	
	@PostMapping("/guardarres")
	public String guardar(@ModelAttribute Reserva reserva) {
		Cliente cliente=clienteservice.buscarId(reserva.getCliente().getIdcliente()).get();
		reserva.setCliente(cliente);
		Mesa mesa=mesaService.buscarId(reserva.getMesa().getIdmesa()).get();
		reserva.setMesa(mesa);
		reservaService.guardar(reserva);
		return "redirect:/indexres";
		
	}
	
	@GetMapping("/editarres/{id}")
	public String editar(@PathVariable int id, Model modelo) {
		Reserva reserva=reservaService.buscarId(id).get();
		modelo.addAttribute("reserva", reserva);
		
		List<Mesa>listaMesas=mesaService.listar();
		List<Cliente>listaClientes=clienteservice.listar();
		
		modelo.addAttribute("listaMesas", listaMesas);
		modelo.addAttribute("listaClientes", listaClientes);
		return "viewaddres";
		
	}
	
	
	@GetMapping("/borrarres/{id}")
	public String borrar(@PathVariable int id) {
		reservaService.borrar(id);
		return "redirect:/indexres";
		
	}
	
}