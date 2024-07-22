package programacion.microservicios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import programacion.microservicios.model.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
}
