package pe.com.demoquarkus.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    ExchangeRateDto getExchangeRate(
            @NotBlank(message = "DNI cannot be blank")
            @Size(min = 8, max = 8, message = "DNI must be 8 characters")
            @Pattern(regexp = "^[0-9]{8}$", message = "DNI must contain only digits")
            @QueryParam("dni") String dni);
}
