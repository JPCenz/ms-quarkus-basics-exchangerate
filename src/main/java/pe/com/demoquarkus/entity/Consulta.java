package pe.com.demoquarkus.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "consultas", indexes = {
        @Index(name = "idx_consulta_dni", columnList = "dni"),
        @Index(name = "idx_consulta_usuario_id", columnList = "usuario_id"),
        @Index(name = "idx_consulta_fecha", columnList = "fecha_consulta")
})
public class Consulta extends PanacheEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    public Usuario usuario;

    @Column(name = "dni", length = 8, nullable = false)
    public String dni;

    @Column(name = "fecha_consulta", nullable = false)
    public LocalDate fechaConsulta;

    @Column(name = "timestamp", nullable = false)
    public LocalDateTime timestamp;



    public static long contarConsultasPorDni(String dni, LocalDate fecha) {
        return count("dni = ?1 and fechaConsulta = ?2", dni, fecha);
    }

    public static List<Consulta> findByDni(String dni) {
        return find("dni", dni).list();
    }


}
