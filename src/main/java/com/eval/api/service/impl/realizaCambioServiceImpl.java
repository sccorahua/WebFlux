package com.eval.api.service.impl;

import com.eval.api.entity.devolucionCambio;
import com.eval.api.entity.regAuditoria;
import com.eval.api.entity.solicitudCambio;
import com.eval.api.entity.tipoCambio;
import com.eval.api.repository.RegAuditoriaRepository;
import com.eval.api.repository.TipoCambioRepository;
import com.eval.api.service.realizaCambioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class realizaCambioServiceImpl implements realizaCambioService {
    @Autowired
    private TipoCambioRepository tipoCambioRepository;

    @Autowired
    private RegAuditoriaRepository audRepository;

    @Override
    public Mono<devolucionCambio> cambiarDinero(solicitudCambio sol) {
        tipoCambio cambio = tipoCambioRepository.findByMonedaOrigenAndMonedaDestino(sol.getMonedaOrigen(),sol.getMonedaDestino());

        if(cambio == null){
            return Mono.error(new Exception("Tipo de cambio no encontrado") );
        }

        devolucionCambio rpta = new devolucionCambio();
        rpta.setTipoCambio(cambio);
        rpta.setMontoDestino(cambio.getMontoCambio().multiply(sol.getMontoOrigen()));

        return Mono.just(rpta);
    }

    @Override
    public Mono<tipoCambio> buscaTipoCambio(String monedaOrigen, String monedaDevolucion) {
        tipoCambio rpta = tipoCambioRepository.findByMonedaOrigenAndMonedaDestino(monedaOrigen,monedaDevolucion);
        if(rpta == null){
            return Mono.empty();
        }
        return Mono.just(rpta);
    }

    @Override
    public void regAuditoria(String accion, String usuario) {
        regAuditoria aud = new regAuditoria();
        aud.setUsuarioMod(usuario);
        aud.setFechaMod(LocalDateTime.now());
        aud.setAccion(accion);
    }

    @Override
    public Mono<tipoCambio> registraTipoCambio(tipoCambio tip) {
        return Mono.just(tipoCambioRepository.save(tip));
    }

    @Override
    public Flux<regAuditoria> obtenerRegAuditoria() {
        return Flux.fromIterable(audRepository.findAll());
    }
}
