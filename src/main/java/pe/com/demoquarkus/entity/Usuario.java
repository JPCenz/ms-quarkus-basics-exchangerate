package pe.com.demoquarkus.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Optional;

@Entity
@Table(name = "usuarios")
public class Usuario extends PanacheEntity {

    @Column(nullable = false, unique = true, length = 8)
    public String dni;

    @Column(length = 100)
    public String name;


    public static Optional<Usuario> findByDni(String dni) {
        return find("dni",dni).firstResultOptional();
    }
}
