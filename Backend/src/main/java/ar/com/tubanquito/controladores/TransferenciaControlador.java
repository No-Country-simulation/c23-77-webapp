package ar.com.tubanquito.controladores;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import ar.com.tubanquito.servicios.TransferenciaServicio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/transferencias")
public class TransferenciaControlador {
    
    @Autowired
    private TransferenciaServicio tranferenciaServicio;


    @GetMapping("")
    public ResponseEntity<?> getTransferenciasByIdUsuario(@RequestParam("id_user") Long idUser, @RequestParam("id_cuenta") Long idCuenta) throws Exception {
        Object response  = tranferenciaServicio.getTransferenciasByIdUsuario(idUser, idCuenta);
        return ResponseEntity.ok(response);
    }
    

}
