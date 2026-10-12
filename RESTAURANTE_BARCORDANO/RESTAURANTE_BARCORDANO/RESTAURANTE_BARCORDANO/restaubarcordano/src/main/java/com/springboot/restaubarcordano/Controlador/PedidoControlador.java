package com.springboot.restaubarcordano.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.springboot.restaubarcordano.Modelo.Entidades.Cliente;
import com.springboot.restaubarcordano.Modelo.Entidades.Pedido;
import com.springboot.restaubarcordano.Modelo.Service.ClienteService;
import com.springboot.restaubarcordano.Modelo.Service.PedidoService;



@Controller
public class PedidoControlador {
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private ClienteService clienteservice;
	
	@GetMapping("/indexped")
	public String index(Model modelo) {
		List<Pedido>lista=pedidoService.listar();
		modelo.addAttribute("listaPedidos", lista);
		return "indexped";
		
	}
	
	@GetMapping("/nuevoped")
	public String nuevo(Model modelo) {
		modelo.addAttribute("pedido", new Pedido());
		List<Cliente>listaClientes=clienteservice.listar();
		modelo.addAttribute("listaClientes", listaClientes);
		return "viewaddped";
		
		
	}
	
	@GetMapping("/guardarped")
	public String guardar(@ModelAttribute Pedido pedido) {
		pedidoService.guardar(pedido);
		return "redirect:/indexped";
		
	}
	
	@GetMapping("/editarped/{id}")
	public String editar(@PathVariable int id, Model modelo) {
		Pedido pedido = pedidoService.buscarId(id).get();
		modelo.addAttribute("pedido", pedido);
		
		List<Cliente>listaClientes=clienteservice.listar();
		modelo.addAttribute("listaClientes", listaClientes);
		return "viewaddped";
		
	}
	
	@GetMapping("/borrarped/{id}")
	public String borrar(@PathVariable int id) {
		pedidoService.borrar(id);
		return "redirect:/indexped";
		
	}
}