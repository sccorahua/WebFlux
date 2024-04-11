package com.eval.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class devolucionCambio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDevCambio;
    private BigDecimal montoDestino;
    @ManyToOne
    @JoinColumn(name = "idTipCambio")
    private tipoCambio tipoCambio;
}
