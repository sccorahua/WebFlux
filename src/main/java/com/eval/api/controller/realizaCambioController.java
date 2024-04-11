package com.eval.api.controller;

import com.eval.api.entity.devolucionCambio;
import com.eval.api.entity.regAuditoria;
import com.eval.api.entity.solicitudCambio;
import com.eval.api.entity.tipoCambio;
import com.eval.api.service.realizaCambioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

@RestController
@RequestMapping("/cambio")
@Slf4j
public class realizaCambioController {
    @Autowired
    private realizaCambioService cambioService;

    @PostMapping
    public Mono<devolucionCambio> cambiaDinero(@RequestBody solicitudCambio sol){
        String accion = "solicitud cambio:" + sol.getMontoOrigen() + " " + sol.getMonedaOrigen() + " a " + sol.getMonedaDestino();
        cambioService.regAuditoria(accion, sol.getUsuarioSol());
        Mono<devolucionCambio> rpta = cambioService.cambiarDinero(sol);
        return rpta;
    }

    @GetMapping("/tipo/{monedaOrigen}/{monedaDestino}")
    public Mono<tipoCambio> obtenerTipoCambio(@PathVariable("monedaOrigen") String monOrigen, @PathVariable("monedaDestino") String monDestino){
        Mono<tipoCambio> cambio = cambioService.buscaTipoCambio(monOrigen,monDestino);
        return cambio;
    }

    @PostMapping("/tipo")
    public Mono<tipoCambio> registraTipoCambio(@RequestBody tipoCambio tip) {
        return cambioService.buscaTipoCambio(tip.getMonedaOrigen(), tip.getMonedaDestino())
                .flatMap(cambio -> {
                    cambio.setMontoCambio(tip.getMontoCambio());
                    return cambioService.registraTipoCambio(cambio);
                })
                .switchIfEmpty(Mono.defer(() -> {
                    return cambioService.registraTipoCambio(tip);
                }))
                .onErrorResume(error -> {
                    return Mono.error(new RuntimeException("Error al ecribir/sobrescribir el tipo de cambio"));
                });
    }

    @GetMapping("/auditoria")
    public Flux<regAuditoria> obtenerRegAuditoria(){
        return cambioService.obtenerRegAuditoria();
    }

}
