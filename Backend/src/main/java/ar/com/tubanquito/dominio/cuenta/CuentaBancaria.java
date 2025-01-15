package ar.com.tubanquito.dominio.cuenta;


import ar.com.tubanquito.dominio.empresa.Empresa;
import ar.com.tubanquito.dominio.usuario.Usuario;
import ar.com.tubanquito.dominio.historial.HistorialTransacciones;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Table(name = "Cuenta_Bancaria")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CuentaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 10, nullable = false)
    private String moneda;

    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal saldo;

    @Column(length = 50, nullable = false)
    private String tipoCuenta;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @Column(name = "banco_emisor")
    private String bancoEmisor;

    @ManyToOne
    @JoinColumn(name = "propietario_id")
    private Usuario usuario;


    @ManyToMany(mappedBy = "cuentaBancarias")
    private Set<Empresa> empresa;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL)
    private Set<HistorialTransacciones> historialTransacciones;
}
