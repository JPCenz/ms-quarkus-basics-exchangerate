package pe.com.demoquarkus.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import pe.com.demoquarkus.dto.ExchangeRateDto;

@Path("/api/exchange-rate")
public interface ExchangeRateResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    ExchangeRateDto getExchangeRate(@QueryParam("dni") String dni);
}
