package ar.com.tubanquito.controladores;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import ar.com.tubanquito.servicios.TransferenciaServicio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/transferencias")
public class TransferenciaControlador {
    
    private TransferenciaServicio tranferenciaServicio;


    @GetMapping("")
    public ResponseEntity<?> getTransferenciasByIdUsuario(@RequestParam("id_user") Long idUser, @RequestParam("id_cuenta") Long idCuenta) throws Exception {
        Object response  = tranferenciaServicio.getTransferenciasByIdUsuario(idUser, idCuenta);
        return ResponseEntity.ok(response);
    }
    

}
