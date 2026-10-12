package com.springboot.restaubarcordano.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class LogoutControlador {

    // Logout para el administrador
    @PostMapping("/cerrar-admin")
    public String logoutAdmin(HttpSession session) {
        session.invalidate();
        return "redirect:/login-admin?logout=true";
    }

    // Logout para el cliente
    @PostMapping("/cerrar-cliente")
    public String logoutCliente(HttpSession session) {
        session.invalidate();
        return "redirect:/login-cliente?logout=true";
    }
}
