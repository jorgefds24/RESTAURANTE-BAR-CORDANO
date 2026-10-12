package com.springboot.restaubarcordano.Controlador;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.restaubarcordano.Seguridad.UsuarioSeguridad;
import com.springboot.restaubarcordano.Seguridad.UsuarioSeguridadRepository;

@Controller
public class RegisterLoginAdministradorControlador {

    private final UsuarioSeguridadRepository usuarioRepo;
    private final PasswordEncoder passwordEncoder;

    public RegisterLoginAdministradorControlador(UsuarioSeguridadRepository usuarioRepo,
                                                  PasswordEncoder passwordEncoder) {
        this.usuarioRepo = usuarioRepo;
        this.passwordEncoder = passwordEncoder;
    }

    //REGISTRO

    @GetMapping("/RegistroAdministrador")
    public String registro() {
        return "registroAdmin";
    }

    @PostMapping("/RegistroAdministrador")
    public String procesarRegistro(@RequestParam String usuario,
                                   @RequestParam String password,
                                   @RequestParam String confirmar,
                                   Model modelo) {

        if (!password.equals(confirmar)) {
            modelo.addAttribute("error", "Las contraseñas no coinciden");
            return "registroAdmin";
        }

        if (usuarioRepo.findByUsername(usuario).isPresent()) {
            modelo.addAttribute("error", "El nombre de usuario ya está en uso");
            return "registroAdmin";
        }

        // Guardar con contraseña encriptada y rol Administrador
        UsuarioSeguridad nuevo = new UsuarioSeguridad(usuario, passwordEncoder.encode(password), "Rol_Administrador");
        usuarioRepo.save(nuevo);

        return "redirect:/login-admin?registrado=true";
    }

    //LOGIN
    //Spring Security procesa el POST /login-admin automáticamente.
    //Solo necesitamos devolver la vista para GET.

    @GetMapping("/login-admin")
    public String loginAdmin() {
        return "loginAdmin";
    }
}
