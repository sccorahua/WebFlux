package com.eval.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
@Entity
@Data
public class solicitudCambio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSolCambio;
    private String usuarioSol;
    private BigDecimal montoOrigen;
    private String monedaOrigen;
    private String monedaDestino;
}
