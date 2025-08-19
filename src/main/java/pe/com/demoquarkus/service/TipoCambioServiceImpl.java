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

    @Override
    @Transactional
    public ExchangeRateDto consultarTipoCambio(String dni) {
        TipoCambioProxy actualExchangeRate = rateRepository.getExchangeRate();
        LocalDate today = LocalDate.now();
        long count = Consulta.contarConsultasPorDia(dni, today);
        validateRequestPerUser(count);
        logger.info(String.format("Consultas para el dni %s cantidad %s", dni, count));

        Consulta consulta = new Consulta();
        consulta.dni = dni;
        consulta.fechaConsulta = today;
        consulta.timestamp = LocalDateTime.now();
        Consulta.persist(consulta);
        var response = mapper.toConsultaDto(actualExchangeRate);
        response.quota = count;
        return response;
    }

    private void validateRequestPerUser(long count) {
        if (count >= limitRequests) {
            throw new WebApplicationException("Se Excedio el limite de consultas por dia",
                    Response.Status.TOO_MANY_REQUESTS);
        }

    }
}
