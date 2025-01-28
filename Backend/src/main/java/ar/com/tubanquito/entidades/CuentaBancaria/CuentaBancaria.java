package ar.com.tubanquito.entidades.CuentaBancaria;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import ar.com.tubanquito.entidades.Empresa;
import ar.com.tubanquito.entidades.HistorialTransacciones;
import ar.com.tubanquito.entidades.Usuario;

@Table(name = "cuenta_bancaria")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CuentaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 10, nullable = false)
    private Moneda moneda;

    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal saldo;

    @Column(length = 50, nullable = false)
    private String tipoCuenta;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @Column(name = "banco_emisor", nullable = true)
    private String bancoEmisor;

    @ManyToOne
    @JoinColumn(name = "propietario_id")
    private Usuario usuario;

    @ManyToMany(mappedBy = "cuentasBancarias") // Nota: Corregido para que coincida
    private Set<Empresa> empresas;

   @Column(name = "cbu", nullable = false)
   private Long cbu;

   @Column(name = "alias")
   private String alias;
}
