package pe.com.demoquarkus.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ExchangeRateDto {

    public String fecha;
    public double compra;
    public double venta;
    public long quota;
}
