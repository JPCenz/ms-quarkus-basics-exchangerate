package pe.com.demoquarkus.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
public class Consulta extends PanacheEntity {

    public String dni;
    public LocalDate fechaConsulta;
    public LocalDateTime timestamp;


    public static long contarConsultasPorDia(String dni, LocalDate fecha) {
        return count("dni = ?1 and fechaConsulta = ?2", dni, fecha);
    }

}
