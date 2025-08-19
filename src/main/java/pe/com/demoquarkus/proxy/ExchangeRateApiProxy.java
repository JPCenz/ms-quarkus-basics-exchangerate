package pe.com.demoquarkus.proxy;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import pe.com.demoquarkus.proxy.model.TipoCambioProxy;

@RegisterRestClient(configKey = "api-tipo-cambio")
public interface ExchangeRateApiProxy {

    @GET
    @Path("/tipo-cambio/today.json")
    TipoCambioProxy obtenerTipoCambio();

}
