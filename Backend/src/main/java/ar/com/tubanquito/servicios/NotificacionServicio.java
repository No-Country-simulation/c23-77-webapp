package ar.com.tubanquito.servicios;

import ar.com.tubanquito.dto.request.NotificacionRequest;
import ar.com.tubanquito.dto.response.NotificacionResponse;
import ar.com.tubanquito.entidades.Notificacion;
import ar.com.tubanquito.entidades.Usuario;
import ar.com.tubanquito.mapper.NotificacionMapper;
import ar.com.tubanquito.repositorios.NotificacionRepositorio;
import ar.com.tubanquito.repositorios.UsuarioRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificacionServicio {
    private final NotificacionRepositorio notificacionRepositorio;
    private final NotificacionMapper notificacionMapper;
    private final UsuarioRepositorio usuarioRepositorio;

    public NotificacionServicio(NotificacionRepositorio notificacionRepositorio, NotificacionMapper notificacionMapper,UsuarioRepositorio usuarioRepositorio) {
        this.notificacionRepositorio = notificacionRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.notificacionMapper = notificacionMapper;
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

    public void eliminarNotificacion(Long id) {
        Notificacion notificacion = notificacionRepositorio.findById(id).orElse(null);
        assert notificacion != null;
        notificacionRepositorio.delete(notificacion);
    }

    public List<NotificacionResponse> obtenerNotificacionesporId(Long id) {
        Usuario usuario = usuarioRepositorio.findById(id).orElse(null);
        System.out.println("Capturado con el Id: " + id + usuario);
        System.out.println(usuarioRepositorio.findAll());
        return notificacionRepositorio.findAll().stream().filter(notificacion -> notificacion.getUsuario().equals(usuario)).map(notificacionMapper::toResponse).toList();
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
