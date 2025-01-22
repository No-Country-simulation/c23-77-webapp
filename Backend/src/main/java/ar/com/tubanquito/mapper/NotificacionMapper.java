package ar.com.tubanquito.mapper;

import ar.com.tubanquito.dto.request.NotificacionRequest;
import ar.com.tubanquito.dto.response.NotificacionResponse;
import ar.com.tubanquito.entidades.Notificacion;
import ar.com.tubanquito.entidades.Usuario;
import org.springframework.stereotype.Component;

@Component
public class NotificacionMapper {
    public Notificacion toEntity(NotificacionRequest request, Usuario usuario) {
        Notificacion notificacion = new Notificacion();
        notificacion.setUsuario(usuario);
        notificacion.setMensaje(request.mensaje());
        notificacion.setTipo(request.tipo());
        notificacion.setLeido(false);
        return notificacion;
    }

    public NotificacionResponse toResponse(Notificacion notificacion) {
        return new NotificacionResponse(
                notificacion.getId(),
                notificacion.getMensaje(),
                notificacion.getTipo(),
                notificacion.getFechaCreacion(),
                notificacion.getLeido()
        );
    }
}
