package com.eval.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
@Entity
@Data
public class tipoCambio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipCambio;
    private String monedaOrigen;
    private String monedaDestino;
    private BigDecimal montoCambio;
}
