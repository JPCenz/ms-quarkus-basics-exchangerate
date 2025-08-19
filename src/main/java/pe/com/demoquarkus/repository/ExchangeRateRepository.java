package pe.com.demoquarkus.repository;

import pe.com.demoquarkus.proxy.model.TipoCambioProxy;

public interface ExchangeRateRepository {

    TipoCambioProxy getExchangeRate();

}
