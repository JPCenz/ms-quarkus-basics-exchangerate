package pe.com.demoquarkus.proxy.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TipoCambioProxy {

    public String fecha;
    public double compra;
    public double venta;

}
