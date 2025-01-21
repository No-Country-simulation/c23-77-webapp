package ar.com.tubanquito.servicios;

import ar.com.tubanquito.dto.request.DireccionRequest;
import ar.com.tubanquito.dto.response.DireccionResponse;
import ar.com.tubanquito.entidades.Direccion;
import ar.com.tubanquito.entidades.Usuario;
import ar.com.tubanquito.mapper.DireccionMapper;
import ar.com.tubanquito.repositorios.DireccionRepositorio;
import ar.com.tubanquito.repositorios.UsuarioRepositorio;
import org.springframework.stereotype.Service;

@Service
public class DireccionServicio {

    private final DireccionRepositorio direccionRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final DireccionMapper direccionMapper;

    public DireccionServicio(DireccionRepositorio direccionRepositorio, UsuarioRepositorio usuarioRepositorio, DireccionMapper direccionMapper) {
        this.direccionRepositorio = direccionRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.direccionMapper = direccionMapper;
    }

    public DireccionResponse crearDireccion(DireccionRequest request) {
        Usuario usuario = usuarioRepositorio.findById(request.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Direccion direccion = direccionMapper.toEntity(request, usuario);
        Direccion guardada = direccionRepositorio.save(direccion);

        return direccionMapper.toResponse(guardada);
    }
}
