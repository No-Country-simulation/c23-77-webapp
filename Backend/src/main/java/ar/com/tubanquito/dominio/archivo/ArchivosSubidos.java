package ar.com.tubanquito.entity;

import ar.com.tubanquito.dominio.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Table(name = "Archivos_Subidos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArchivosSubidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "nombre_archivo", nullable = false)
    private String nombreArchivo;

    @Column(name = "ruta_archivo", nullable = false)
    private String rutaArchivo;

    @Column(name = "tipo_archivo")
    private String tipoArchivo;

    @Column(name = "fecha_subida", nullable = false)
    private LocalDateTime fechaSubida;
}
