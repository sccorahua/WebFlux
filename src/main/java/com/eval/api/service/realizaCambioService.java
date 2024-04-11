package com.eval.api.service;

import com.eval.api.entity.devolucionCambio;
import com.eval.api.entity.regAuditoria;
import com.eval.api.entity.solicitudCambio;
import com.eval.api.entity.tipoCambio;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface realizaCambioService {
    Mono<devolucionCambio> cambiarDinero(solicitudCambio sol);
    Mono<tipoCambio> buscaTipoCambio(String monedaOrigen, String monedaDevolucion);

    void regAuditoria(String accion, String usuario);

    Mono<tipoCambio> registraTipoCambio(tipoCambio tip);

    Flux<regAuditoria> obtenerRegAuditoria();
}
