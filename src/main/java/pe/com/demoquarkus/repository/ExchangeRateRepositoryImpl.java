package pe.com.demoquarkus.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import pe.com.demoquarkus.proxy.ExchangeRateApiProxy;
import pe.com.demoquarkus.proxy.model.TipoCambioProxy;

@ApplicationScoped
public class ExchangeRateRepositoryImpl implements ExchangeRateRepository {
    @Inject
    Logger logger;

    @RestClient
    @Inject
    ExchangeRateApiProxy apiProxy;

    @Override
    public TipoCambioProxy getExchangeRate() {
        long startTime = System.currentTimeMillis();

        var result=  apiProxy.obtenerTipoCambio();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        logger.info("REST client call completed in {} ms"+ duration);
        return result;
    }
}
