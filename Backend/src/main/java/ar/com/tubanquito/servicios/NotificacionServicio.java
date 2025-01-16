package ar.com.tubanquito.servicios;

import ar.com.tubanquito.entidades.Notificacion;
import ar.com.tubanquito.repositorio.NotificacionRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacionServicio {
    private final NotificacionRepositorio notificacionRepositorio;

    public NotificacionServicio(NotificacionRepositorio notificacionRepositorio    ) {
        this.notificacionRepositorio = notificacionRepositorio;
    }

    public List<Notificacion> obtenerNotificaciones() {
        return notificacionRepositorio.findAll();
    }
}
