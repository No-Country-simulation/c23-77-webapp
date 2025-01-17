package ar.com.tubanquito.controladores;

import ar.com.tubanquito.dto.request.NotificacionRequest;
import ar.com.tubanquito.entidades.Notificacion;
import ar.com.tubanquito.servicios.NotificacionServicio;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificacion")
public class NotificacionControlador {

    public final NotificacionServicio notificacionServicio;

    public NotificacionControlador(NotificacionServicio notificacionServicio, ListableBeanFactory listableBeanFactory) {
        this.notificacionServicio = notificacionServicio;
    }

    @PostMapping("")
    public ResponseEntity<String> creacionNotificacion(@RequestBody NotificacionRequest notificacion){
        notificacionServicio.creacionNotificacion(notificacion);
        return ResponseEntity.ok("Notificación creada correctamente" + notificacion.toString());
    }

    @PatchMapping("/leido/{id}")
    public ResponseEntity<String> marcarLeido(@PathVariable Long id){
        notificacionServicio.confirmarLeido(id);
        return ResponseEntity.ok("Marcado Exitosamente");
    }
}
