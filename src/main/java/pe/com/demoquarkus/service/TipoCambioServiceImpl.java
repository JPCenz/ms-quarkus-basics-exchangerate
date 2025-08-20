package pe.com.demoquarkus.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import pe.com.demoquarkus.dto.ExchangeRateDto;
import pe.com.demoquarkus.entity.Consulta;
import pe.com.demoquarkus.entity.Usuario;
import pe.com.demoquarkus.mapper.ExchangeRateMapper;
import pe.com.demoquarkus.proxy.model.TipoCambioProxy;
import pe.com.demoquarkus.repository.ExchangeRateRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ApplicationScoped
public class TipoCambioServiceImpl implements TipoCambioService {
    @Inject
    ExchangeRateRepository rateRepository;

    @Inject
    ExchangeRateMapper mapper;
    @Inject
    Logger logger;

    @ConfigProperty(name = "application.request-limit", defaultValue = "10")
    Integer limitRequests;

    /**
     * Consults the current exchange rate for a given DNI.
     * Enforces a daily request limit per user.
     *
     * @param dni the user's DNI
     * @return ExchangeRateDto containing exchange rate info and quota
     * @throws WebApplicationException if the daily request limit is exceeded
     */
    @Override
    @Transactional
    public ExchangeRateDto consultarTipoCambio(String dni) {
        TipoCambioProxy actualExchangeRate = rateRepository.getExchangeRate();
        LocalDate today = LocalDate.now();
        long count = Consulta.contarConsultasPorDni(dni, today);
        validateRequestPerUser(count);
        logger.info(String.format("Consultas para el dni %s cantidad %s", dni, count));

        Usuario usuario = getUsuario(dni);
        saveConsulta(usuario, today);

        var response = mapper.toConsultaDto(actualExchangeRate);
        response.quota = count;
        return response;
    }

    private static void saveConsulta(Usuario usuario, LocalDate today) {
        Consulta consulta = new Consulta();
        consulta.usuario = usuario;
        consulta.dni = usuario.dni;
        consulta.fechaConsulta = today;
        consulta.timestamp = LocalDateTime.now();
        consulta.persist();
    }

    private Usuario getUsuario(String dni) {
        // Find or create user by DNI
        return Usuario.findByDni(dni).orElseGet(() -> {
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.dni = dni;
            Usuario.persist(nuevoUsuario);
            return nuevoUsuario;
        });
    }

    private void validateRequestPerUser(long count) {
        if (count >= limitRequests) {
            throw new WebApplicationException("Se Excedio el limite de consultas por dia",
                    Response.Status.TOO_MANY_REQUESTS);
        }

    }
}
