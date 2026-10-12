package com.springboot.restaubarcordano.Controlador;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.restaubarcordano.Modelo.Entidades.Cliente;
import com.springboot.restaubarcordano.Modelo.Service.ClienteService;
import com.springboot.restaubarcordano.Seguridad.UsuarioSeguridad;
import com.springboot.restaubarcordano.Seguridad.UsuarioSeguridadRepository;

@Controller
public class RegisterLoginClienteControlador {

    private final ClienteService clienteService;
    private final UsuarioSeguridadRepository usuarioRepo;
    private final PasswordEncoder passwordEncoder;

    public RegisterLoginClienteControlador(ClienteService clienteService,
                                            UsuarioSeguridadRepository usuarioRepo,
                                            PasswordEncoder passwordEncoder) {
        this.clienteService = clienteService;
        this.usuarioRepo = usuarioRepo;
        this.passwordEncoder = passwordEncoder;
    }

    // REGISTRO
    @GetMapping("/RegistroCliente")
    public String registro() {
        return "clienteregistro";
    }

    @PostMapping("/RegistroCliente")
    public String procesarRegistro(@RequestParam String usuario,
                                   @RequestParam String password,
                                   @RequestParam String confirmar,
                                   @RequestParam String dni,
                                   @RequestParam String telefono,
                                   @RequestParam String correo,
                                   Model modelo) {

        // Validar que las contraseñas coincidan
        if (!password.equals(confirmar)) {
            modelo.addAttribute("error", "Las contraseñas no coinciden");
            return "clienteregistro";
        }

        // Validar que el username no esté en uso
        if (usuarioRepo.findByUsername(usuario).isPresent()) {
            modelo.addAttribute("error", "El nombre de usuario ya está en uso");
            return "clienteregistro";
        }

        // 1. Guardar datos del cliente en la tabla clientes
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setDnicliente(dni);
        nuevoCliente.setNomcliente(usuario);
        nuevoCliente.setTelcliente(telefono);
        nuevoCliente.setCorcliente(correo);
        clienteService.guardar(nuevoCliente);

        
        // 2. Guardar credenciales vinculadas al cliente recién creado
        //    La FK idcliente en usuarios_seguridad apunta al cliente creado arriba
        UsuarioSeguridad nuevo = new UsuarioSeguridad(usuario, passwordEncoder.encode(password), "Rol_Cliente",nuevoCliente);
        usuarioRepo.save(nuevo);

        return "redirect:/login-cliente?registrado=true";
    }

    // LOGIN
    // Spring Security procesa el POST /login-cliente automáticamente.
    // Solo necesitamos devolver la vista para GET.
    @GetMapping("/login-cliente")
    public String loginCliente() {
        return "clientelogin";
    }
}