package ar.com.tubanquito.mapper;

import ar.com.tubanquito.dto.request.EmpresaRequest;
import ar.com.tubanquito.dto.response.EmpresaResponse;
import ar.com.tubanquito.entidades.Empresa;
import ar.com.tubanquito.entidades.Usuario;
import org.springframework.stereotype.Component;

@Component
public class EmpresaMapper {

    public Empresa toEntity(EmpresaRequest request, Usuario usuario) {
        Empresa empresa = new Empresa();
        empresa.setNombre(request.nombre());
        empresa.setUsuario(usuario);
        return empresa;
    }

    public EmpresaResponse toResponse(Empresa empresa) {
        return new EmpresaResponse(
                empresa.getId(),
                empresa.getNombre(),
                empresa.getUsuario().getId()
        );
    }
}
