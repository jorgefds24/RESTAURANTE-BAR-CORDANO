package com.springboot.restaubarcordano.Seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
            boolean esAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("Rol_Administrador"));
            if (esAdmin) {
                response.sendRedirect("/indexmes");
            } else {
                response.sendRedirect("/PedidoCliente");
            }
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/login-admin", "/login-cliente",
                    "/RegistroAdministrador", "/RegistroCliente",
                    "/cerrar-admin", "/cerrar-cliente",
                    "/css/**", "/js/**", "/images/**", "/webjars/**",
                    // SWAGGER
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/v3/api-docs/**",
                    "/v3/api-docs"
                ).permitAll()
                .requestMatchers(
                    "/indexmes", "/indexmenu", "/indexped", "/indexres",
                    "/nuevomes", "/editarmes/**", "/borrarmes/**", "/guardarmes",
                    "/nuevomenu", "/editarmenu/**", "/borrarmenu/**", "/guardarmenu",
                    "/nuevores", "/editarres/**", "/borrarres/**", "/guardarres",
                    "/nuevoped", "/editarped/**", "/borrarped/**", "/guardarped",
                    "/viewaddmes", "/viewaddmenu", "/viewaddped", "/viewaddres",
                    "/viewaddcliente/**"
                ).hasAuthority("Rol_Administrador")
                .requestMatchers(
                    "/PedidoCliente", "/ReservaCliente",
                    "/MispedidosCliente", "/MisreservasCliente",
                    "/GuardarpedidoCliente", "/GuardarreservaCliente"
                ).hasAuthority("Rol_Cliente")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login-admin")
                .loginProcessingUrl("/login-admin")
                .successHandler(successHandler())
                .failureHandler((request, response, exception) -> {
                    String referer = request.getHeader("Referer");
                    if (referer != null && referer.contains("login-cliente")) {
                        response.sendRedirect("/login-cliente?error=true");
                    } else {
                        response.sendRedirect("/login-admin?error=true");
                    }
                })
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout-admin")
                .logoutSuccessUrl("/login-admin?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
            .csrf(csrf -> csrf
                .ignoringRequestMatchers(
                    "/RegistroAdministrador", "/RegistroCliente",
                    "/login-admin", "/login-cliente",
                    "/cerrar-admin", "/cerrar-cliente",
                    // SWAGGER
                    "/v3/api-docs/**",
                    "/swagger-ui/**"
                )
            );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder =
            http.getSharedObject(AuthenticationManagerBuilder.class);
        builder
            .userDetailsService(customUserDetailsService)
            .passwordEncoder(passwordEncoder());
        return builder.build();
    }
}