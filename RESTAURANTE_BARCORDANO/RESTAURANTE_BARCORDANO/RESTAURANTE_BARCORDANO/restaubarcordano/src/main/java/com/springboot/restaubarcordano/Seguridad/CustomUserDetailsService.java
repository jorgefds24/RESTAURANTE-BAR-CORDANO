package com.springboot.restaubarcordano.Seguridad;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioSeguridadRepository usuarioRepo;

    public CustomUserDetailsService(UsuarioSeguridadRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioSeguridad usuario = usuarioRepo.findByUsername(username)
            .orElseThrow(() ->
                new UsernameNotFoundException("Usuario no encontrado: " + username));

        return new User(
            usuario.getUsername(),
            usuario.getPassword(),
            List.of(new SimpleGrantedAuthority(usuario.getRol()))
        );
    }
}
