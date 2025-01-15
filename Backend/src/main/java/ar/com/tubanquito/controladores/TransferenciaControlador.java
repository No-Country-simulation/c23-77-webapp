package ar.com.tubanquito.controladores;

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
    public String getMethodName(@RequestParam("id") Long id) {
        return new String();
    }
    

}
