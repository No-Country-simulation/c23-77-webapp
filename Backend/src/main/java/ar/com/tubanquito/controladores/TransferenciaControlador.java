package ar.com.tubanquito.controladores;

import java.util.List;

import org.apache.catalina.connector.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import ar.com.tubanquito.dto.request.CreateTransferDTO;
import ar.com.tubanquito.entidades.Transferencia;
import ar.com.tubanquito.servicios.TransferenciaServicio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/transferencias")
public class TransferenciaControlador {
    
    @Autowired
    private TransferenciaServicio transferenciaServicio;


    @GetMapping("")
    public ResponseEntity<?> getTransferenciasByIdUsuario(@RequestParam("id_user") Long idUser, @RequestParam("id_cuenta") Long idCuenta) throws Exception {
        List<Transferencia> response  = transferenciaServicio.getTransferenciasByIdUsuario(idUser, idCuenta);
        return ResponseEntity.ok(response);
    }

    //crear transferencia

    @PostMapping("")
    public ResponseEntity<?> createTransfer(@RequestBody CreateTransferDTO transfer) {
        Object response = transferenciaServicio.create(transfer);
        return ResponseEntity.ok(response);
    }
    
    

}
