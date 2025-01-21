package ar.com.tubanquito.mapper;

import ar.com.tubanquito.dto.request.DireccionRequest;
import ar.com.tubanquito.dto.response.DireccionResponse;
import ar.com.tubanquito.entidades.Direccion;
import ar.com.tubanquito.entidades.Usuario;
import org.springframework.stereotype.Component;

@Component
public class DireccionMapper {

    public Direccion toEntity(DireccionRequest request, Usuario usuario) {
        Direccion direccion = new Direccion();
        direccion.setCalle(request.calle());
        direccion.setCiudad(request.ciudad());
        direccion.setEstadoProvincia(request.estadoProvincia());
        direccion.setCp(request.cp());
        direccion.setPais(request.pais());
        direccion.setUsuario(usuario); // Asigna la relación con el usuario
        return direccion;
    }

    public DireccionResponse toResponse(Direccion direccion) {
        return new DireccionResponse(
                direccion.getId(),
                direccion.getCalle(),
                direccion.getCiudad(),
                direccion.getEstadoProvincia(),
                direccion.getCp(),
                direccion.getPais(),
                direccion.getUsuario() != null ? direccion.getUsuario().getId() : null
        );
    }
}
