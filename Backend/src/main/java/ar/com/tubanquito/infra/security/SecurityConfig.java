package ar.com.tubanquito.infra.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> {
                    // Endpoints públicos
                    req.requestMatchers("/login", "/").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/personas", "/usuarios/registro").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/personas", "/usuarios/registro").permitAll();
                    req.requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/persona/**").permitAll(); // Si deben ser públicos

                    // Endpoints protegidos
                    req.requestMatchers("/dashboard").authenticated();
                    req.requestMatchers(HttpMethod.POST, "/personas/**").authenticated();
                    req.requestMatchers(HttpMethod.PUT, "/personas/**").authenticated();
                    req.requestMatchers(HttpMethod.DELETE, "/personas/**").hasRole("ADMIN"); // Solo administradores

                    // Cualquier otra solicitud requiere autenticación
                    req.anyRequest().authenticated();
                })
                // Aquí puedes agregar filtros adicionales, si es necesario
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
