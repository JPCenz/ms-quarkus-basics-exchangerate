package pe.com.demoquarkus.service;

import pe.com.demoquarkus.dto.ExchangeRateDto;

public interface TipoCambioService {

    ExchangeRateDto consultarTipoCambio(String dni);
}
