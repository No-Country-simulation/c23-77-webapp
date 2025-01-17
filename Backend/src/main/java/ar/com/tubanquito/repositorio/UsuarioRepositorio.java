package ar.com.tubanquito.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.tubanquito.entidades.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
}
