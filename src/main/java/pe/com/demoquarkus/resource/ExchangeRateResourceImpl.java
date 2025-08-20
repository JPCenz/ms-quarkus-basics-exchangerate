package pe.com.demoquarkus.resource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import pe.com.demoquarkus.dto.ExchangeRateDto;
import pe.com.demoquarkus.service.TipoCambioService;

@ApplicationScoped
public class ExchangeRateResourceImpl implements ExchangeRateResource {
    @Inject
    Logger logger;

    @Inject
    TipoCambioService service;

    /**
     * Obtiene el cambio del dia
     * @param dni
     * @return ExchangeRateDto
     */
    @Override
    public ExchangeRateDto getExchangeRate(String dni) {
        logger.info("Request para dni "+ dni);
        if (dni == null || dni.isBlank()) {
            throw new WebApplicationException("El DNI es obligatorio", Response.Status.BAD_REQUEST);
        }
        return service.consultarTipoCambio(dni);
    }
}
