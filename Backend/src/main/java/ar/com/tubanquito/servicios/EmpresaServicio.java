package ar.com.tubanquito.servicios;

import ar.com.tubanquito.dto.request.EmpresaRequest;
import ar.com.tubanquito.dto.response.EmpresaResponse;
import ar.com.tubanquito.entidades.Empresa;
import ar.com.tubanquito.entidades.Usuario;
import ar.com.tubanquito.mapper.EmpresaMapper;
import ar.com.tubanquito.repositorios.EmpresaRepositorio;
import ar.com.tubanquito.repositorios.UsuarioRepositorio;
import ar.com.tubanquito.validaciones.Validador;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaServicio {

    private final EmpresaRepositorio empresaRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final EmpresaMapper empresaMapper;
    private final List<Validador<EmpresaRequest>> validadores;

    public EmpresaServicio(EmpresaRepositorio empresaRepositorio, UsuarioRepositorio usuarioRepositorio, EmpresaMapper empresaMapper, List<Validador<EmpresaRequest>> validadores) {
        this.empresaRepositorio = empresaRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.empresaMapper = empresaMapper;
        this.validadores = validadores;
    }

    public EmpresaResponse crearEmpresa(EmpresaRequest request) {
        Usuario usuario = usuarioRepositorio.findById(request.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        validadores.forEach(v -> v.validar(request));

        Empresa empresa = empresaMapper.toEntity(request, usuario);
        Empresa guardada = empresaRepositorio.save(empresa);
        return empresaMapper.toResponse(guardada);
    }

    public EmpresaResponse obtenerEmpresaPorId(Long id) {
        Empresa empresa = empresaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
        return empresaMapper.toResponse(empresa);
    }

    public List<Empresa> listarEmpresas() {
        return empresaRepositorio.findAll();
    }

    public Empresa actualizarEmpresa(Long id, Empresa empresaActualizada) {
        Empresa empresa = empresaRepositorio.findById(id).orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        empresa.setNombre(empresaActualizada.getNombre());
        empresa.setCuentasBancarias(empresaActualizada.getCuentasBancarias());
        empresa.setPersonas(empresaActualizada.getPersonas());

        return empresaRepositorio.save(empresa);
    }

    public void eliminarEmpresa(Long id) {
        Empresa empresa = empresaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        empresaRepositorio.delete(empresa);
    }


}
