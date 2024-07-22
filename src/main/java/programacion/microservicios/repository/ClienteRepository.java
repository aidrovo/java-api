package programacion.microservicios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import programacion.microservicios.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
