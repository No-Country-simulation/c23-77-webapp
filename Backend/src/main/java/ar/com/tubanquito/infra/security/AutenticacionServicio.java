package ar.com.tubanquito.infra.security;

import ar.com.tubanquito.repositorios.UsuarioRepositorio;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionServicio implements UserDetailsService {

    private final UsuarioRepositorio repositorio;

    public AutenticacionServicio(UsuarioRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repositorio.findUserDetailsByEmail(email);
    }

}
