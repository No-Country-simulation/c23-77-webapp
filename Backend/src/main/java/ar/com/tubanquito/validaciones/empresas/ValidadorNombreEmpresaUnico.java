package ar.com.tubanquito.validaciones.empresas;

import ar.com.tubanquito.dto.request.EmpresaRequest;
import ar.com.tubanquito.repositorios.EmpresaRepositorio;
import ar.com.tubanquito.validaciones.Validador;
import org.springframework.stereotype.Component;

@Component
public class ValidadorNombreEmpresaUnico implements Validador<EmpresaRequest> {

    private final EmpresaRepositorio empresaRepositorio;

    public ValidadorNombreEmpresaUnico(EmpresaRepositorio empresaRepositorio) {
        this.empresaRepositorio = empresaRepositorio;
    }

    @Override
    public void validar(EmpresaRequest datos) {
        if (empresaRepositorio.findByNombre(datos.nombre()).isPresent()) {
            throw new RuntimeException("El nombre de la empresa ya está registrado: " + datos.nombre());
        }
    }
}
