package ar.com.tubanquito.entity;


import ar.com.tubanquito.dominio.trasnferencia.Transferencia;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "Historial_Transacciones")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HistorialTransacciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_transaccion", nullable = false)
    private String tipoTransaccion;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal monto;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "cuenta_id", nullable = false)
    private CuentaBancaria cuenta;

    @ManyToOne
    @JoinColumn(name = "transferencia_id")
    private Transferencia transferencia;
}
