package com.springboot.restaubarcordano.Controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.restaubarcordano.Modelo.Entidades.Cliente;
import com.springboot.restaubarcordano.Modelo.Entidades.DetallePedido;
import com.springboot.restaubarcordano.Modelo.Entidades.Menu;
import com.springboot.restaubarcordano.Modelo.Entidades.Mesa;
import com.springboot.restaubarcordano.Modelo.Entidades.Pedido;
import com.springboot.restaubarcordano.Modelo.Entidades.Reserva;
import com.springboot.restaubarcordano.Modelo.Service.ClienteService;
import com.springboot.restaubarcordano.Modelo.Service.DetallePedidoService;
import com.springboot.restaubarcordano.Modelo.Service.MenuService;
import com.springboot.restaubarcordano.Modelo.Service.MesaService;
import com.springboot.restaubarcordano.Modelo.Service.PedidoService;
import com.springboot.restaubarcordano.Modelo.Service.ReservaService;
import com.springboot.restaubarcordano.Seguridad.UsuarioSeguridad;
import com.springboot.restaubarcordano.Seguridad.UsuarioSeguridadRepository;

@Controller
public class ViewClienteControlador {

    @Autowired
    private ClienteService clienteservice;

    @Autowired
    private MesaService mesaservice;

    @Autowired
    private ReservaService reservaservice;

    @Autowired
    private PedidoService pedidoservice;

    @Autowired
    private MenuService menuService;

    @Autowired
    private DetallePedidoService detallePedidoService;

    // Se inyecta el repositorio de seguridad para obtener el cliente
    // directamente desde la relación FK, sin hacer loop por todos los clientes
    @Autowired
    private UsuarioSeguridadRepository usuarioRepo;

    // Obtiene el cliente actual usando la FK idcliente de usuarios_seguridad
    private Cliente obtenerClienteActual(UserDetails userDetails) {
        if (userDetails == null) return null;
        return usuarioRepo.findByUsername(userDetails.getUsername())
                .map(UsuarioSeguridad::getCliente)
                .orElse(null);
    }

    // ===================== RESERVAS =====================

    @GetMapping("/ReservaCliente")
    public String reserva(@AuthenticationPrincipal UserDetails userDetails, Model modelo) {
        Cliente clienteActual = obtenerClienteActual(userDetails);
        modelo.addAttribute("reserva", new Reserva());
        modelo.addAttribute("listaMesas", mesaservice.listar());
        modelo.addAttribute("listaClientes", clienteservice.listar());
        modelo.addAttribute("clienteActual", clienteActual);
        return "clientereserva";
    }

    @GetMapping("/GuardarreservaCliente")
    public String guardarReserva(@ModelAttribute Reserva reserva,
                                 @AuthenticationPrincipal UserDetails userDetails) {
        Cliente cliente = clienteservice.buscarId(reserva.getCliente().getIdcliente()).get();
        reserva.setCliente(cliente);
        Mesa mesa = mesaservice.buscarId(reserva.getMesa().getIdmesa()).get();
        reserva.setMesa(mesa);
        reservaservice.guardar(reserva);
        return "redirect:/MisreservasCliente";
    }

    @GetMapping("/MisreservasCliente")
    public String misReservas(@AuthenticationPrincipal UserDetails userDetails, Model modelo) {
        Cliente clienteActual = obtenerClienteActual(userDetails);
        if (clienteActual != null) {
            modelo.addAttribute("listaReservas",
                reservaservice.listarPorCliente(clienteActual.getIdcliente()));
        } else {
            modelo.addAttribute("listaReservas", new ArrayList<>());
        }
        return "clientemisreservas";
    }

    // ===================== PEDIDOS =====================

    @GetMapping("/PedidoCliente")
    public String pedido(@AuthenticationPrincipal UserDetails userDetails, Model modelo) {
        Cliente clienteActual = obtenerClienteActual(userDetails);
        modelo.addAttribute("pedido", new Pedido());
        modelo.addAttribute("clienteActual", clienteActual);
        modelo.addAttribute("listaPlatos",  menuService.listarPorCategoria("PLATO"));
        modelo.addAttribute("listaPostres", menuService.listarPorCategoria("POSTRE"));
        modelo.addAttribute("listaBebidas", menuService.listarPorCategoria("BEBIDA"));
        return "clientepedido";
    }

    @GetMapping("/GuardarpedidoCliente")
    public String guardarPedido(
            @RequestParam(value = "idmenus",       required = false) List<Integer> idmenus,
            @RequestParam(value = "cantidades",    required = false) List<Integer> cantidades,
            @RequestParam(value = "total",         required = false, defaultValue = "0") double total,
            @RequestParam(value = "observaciones", required = false) String observaciones,
            @AuthenticationPrincipal UserDetails userDetails) {

        Cliente clienteActual = obtenerClienteActual(userDetails);
        if (clienteActual == null || idmenus == null || idmenus.isEmpty()) {
            return "redirect:/PedidoCliente";
        }

        // 1. Construir descripción del pedido
        StringBuilder descripcion = new StringBuilder();
        for (int i = 0; i < idmenus.size(); i++) {
            Menu menu = menuService.buscarId(idmenus.get(i)).get();
            int cantidad = cantidades.get(i);
            descripcion.append(menu.getNombre())
                       .append(" (").append(cantidad).append(")");
            if (i < idmenus.size() - 1) descripcion.append(", ");
        }

        // 2. Guardar pedido principal
        Pedido pedido = new Pedido();
        pedido.setCliente(clienteActual);
        pedido.setDescripcion(descripcion.toString());
        pedido.setTotal(total);
        pedido.setEstado("PENDIENTE");
        pedido.setObservaciones(observaciones);
        pedidoservice.guardar(pedido);

        // 3. Guardar cada detalle vinculado al pedido
        for (int i = 0; i < idmenus.size(); i++) {
            Menu menu = menuService.buscarId(idmenus.get(i)).get();
            int cantidad = cantidades.get(i);

            DetallePedido detalle = new DetallePedido();
            detalle.setPedido(pedido);
            detalle.setMenu(menu);
            detalle.setCantidad(cantidad);
            detalle.setPrecio_unit(menu.getPrecio());
            detalle.setSubtotal(menu.getPrecio() * cantidad);
            detallePedidoService.guardar(detalle);
        }

        return "redirect:/MispedidosCliente";
    }

    @GetMapping("/MispedidosCliente")
    public String misPedidos(@AuthenticationPrincipal UserDetails userDetails, Model modelo) {
        Cliente clienteActual = obtenerClienteActual(userDetails);
        if (clienteActual != null) {
            modelo.addAttribute("listaPedidos",
                pedidoservice.listarPorCliente(clienteActual.getIdcliente()));
        } else {
            modelo.addAttribute("listaPedidos", new ArrayList<>());
        }
        return "clientemispedidos";
    }
}