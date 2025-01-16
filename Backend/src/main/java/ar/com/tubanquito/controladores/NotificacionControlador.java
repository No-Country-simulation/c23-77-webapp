package ar.com.tubanquito.controladores;

import ar.com.tubanquito.entidades.Notificacion;
import ar.com.tubanquito.servicios.NotificacionServicio;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notificacion")
public class NotificacionControlador {

    public final NotificacionServicio notificacionServicio;

    public NotificacionControlador(NotificacionServicio notificacionServicio, ListableBeanFactory listableBeanFactory) {
        this.notificacionServicio = notificacionServicio;
    }

    @GetMapping("/string")
    public String noti_prueba(){
        return "Ready";
    }

    @GetMapping("")
    public ResponseEntity<List<Notificacion>> obtenerNotificaciones(){
        List<Notificacion> resultado = notificacionServicio.obtenerNotificaciones();

        return ResponseEntity.ok(resultado);
    }
}
