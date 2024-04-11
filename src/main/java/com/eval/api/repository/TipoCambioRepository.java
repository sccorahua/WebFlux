package com.eval.api.repository;

import com.eval.api.entity.tipoCambio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoCambioRepository extends JpaRepository<tipoCambio,Number> {
    public tipoCambio findByMonedaOrigenAndMonedaDestino(String MonegaOrigen, String MonedaDestino);
}
