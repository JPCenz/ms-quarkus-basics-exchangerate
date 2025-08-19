package pe.com.demoquarkus.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import pe.com.demoquarkus.proxy.ExchangeRateApiProxy;
import pe.com.demoquarkus.proxy.model.TipoCambioProxy;

@ApplicationScoped
public class ExchangeRateRepositoryImpl implements ExchangeRateRepository {

    @RestClient
    @Inject
    ExchangeRateApiProxy apiProxy;

    @Override
    public TipoCambioProxy getExchangeRate() {
        return apiProxy.obtenerTipoCambio();
    }
}
