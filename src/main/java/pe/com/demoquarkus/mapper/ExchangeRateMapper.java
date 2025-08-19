package pe.com.demoquarkus.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import pe.com.demoquarkus.dto.ExchangeRateDto;
import pe.com.demoquarkus.proxy.model.TipoCambioProxy;

@Mapper(componentModel = "cdi",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ExchangeRateMapper {


    ExchangeRateDto toConsultaDto(TipoCambioProxy proxy);
}
