package ar.com.tubanquito.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ar.com.tubanquito.entidades.Transferencia;
import ar.com.tubanquito.repositorios.TransferenciaRepositorio;

@Service
public class TransferenciaServicio {

    @Autowired
    private TransferenciaRepositorio transferenciaRepositorio;

    public List<Transferencia> getTransferenciasByIdUsuario(Long idUsuario, Long idCuenta) {
       
        return null;

    }
    
}
