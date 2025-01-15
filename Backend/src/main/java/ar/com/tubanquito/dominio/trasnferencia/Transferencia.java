package ar.com.tubanquito.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Table(name = "Transferencia")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal saldo;

    @ManyToOne
    @JoinColumn(name = "cuenta_origen", nullable = false)
    private CuentaBancaria cuentaOrgien;

    @ManyToOne
    @JoinColumn(name = "cuenta_destino", nullable = false)
    private CuentaBancaria cuentaDestino;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private String estado;

    @Column(length = 500)
    private String notas;

    @OneToMany(mappedBy = "transferencia", cascade = CascadeType.ALL)
    private Set<HistorialTransacciones> historialTransacciones;

}
