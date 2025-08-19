package pe.com.demoquarkus.dto;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ConsultaDto {

    public String dni;
    public LocalDate fechaConsulta;
    public LocalDateTime timestamp;
}

