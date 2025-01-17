package ar.com.tubanquito.servicios;

import ar.com.tubanquito.dto.request.NotificacionRequest;
import ar.com.tubanquito.entidades.Notificacion;
import ar.com.tubanquito.entidades.Usuario;
import ar.com.tubanquito.repositorio.NotificacionRepositorio;
import ar.com.tubanquito.repositorios.UsuarioRepositorio;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacionServicio {
    private final NotificacionRepositorio notificacionRepositorio;

    private final UsuarioRepositorio usuarioRepositorio;

    public NotificacionServicio(NotificacionRepositorio notificacionRepositorio, UsuarioRepositorio usuarioRepositorio) {
        this.notificacionRepositorio = notificacionRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
    }

    // Creacion Basica de Notificaciones
    public void creacionNotificacion(NotificacionRequest notificacionRequest) {
        Notificacion notificacion = new Notificacion();
        Usuario usuario = usuarioRepositorio.findById(notificacionRequest.id()).orElse(null);
        notificacion.setUsuario(usuario);
        notificacion.setMensaje(notificacionRequest.mensaje());
        notificacion.setTipo(notificacionRequest.tipo());
        notificacion.setLeido(false);
        notificacionRepositorio.save(notificacion);
    }


    // Marcar como leido la notificacion
    public void confirmarLeido(Long id){
        Notificacion notificacion = notificacionRepositorio.findById(id).orElse(null);
        assert notificacion != null;
        if (!notificacion.getLeido()){
            notificacion.setLeido(true);
            notificacionRepositorio.save(notificacion);
        }
    }
}
