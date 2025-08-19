package pe.com.demoquarkus.exception;

import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;
import pe.com.demoquarkus.exception.model.ErrorResponse;
import java.util.UUID;


@Provider
public class ThrowableMapper implements ExceptionMapper<WebApplicationException> {

    @Inject
    Logger log;

    @Override
    public Response toResponse(WebApplicationException e) {
        String errorId = UUID.randomUUID().toString();
        log.warn("errorId[{}]", errorId, e);
        ErrorResponse.ErrorMessage errorMessage = new ErrorResponse.ErrorMessage(e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(errorId, errorMessage);

        return Response.status(e.getResponse().getStatus())
                .entity(errorResponse)
                .build();
    }
}
