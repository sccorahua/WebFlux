package com.eval.api.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;
@Entity
@Data
public class regAuditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegAuditoria;
    private String usuarioMod;
    private LocalDateTime fechaMod;
    private String accion;
}
