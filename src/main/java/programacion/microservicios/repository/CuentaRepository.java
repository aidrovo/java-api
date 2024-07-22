package programacion.microservicios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import programacion.microservicios.model.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
}
