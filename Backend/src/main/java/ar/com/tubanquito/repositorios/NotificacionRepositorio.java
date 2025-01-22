package ar.com.tubanquito.repositorios;

import ar.com.tubanquito.entidades.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacionRepositorio extends JpaRepository<Notificacion, Long> {
}
