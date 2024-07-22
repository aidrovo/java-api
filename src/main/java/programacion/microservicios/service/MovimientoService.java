package programacion.microservicios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import programacion.microservicios.model.Movimiento;
import programacion.microservicios.repository.MovimientoRepository;

import java.util.List;
import programacion.microservicios.exception.MovimientoNotFoundException;
import programacion.microservicios.repository.CuentaRepository;

@Service
public class MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    public List<Movimiento> getAllMovimientos() {
        return movimientoRepository.findAll();
    }

    public Movimiento getMovimientoById(Long id) {
        return movimientoRepository.findById(id)
                .orElseThrow(() -> new MovimientoNotFoundException("Movimiento not found with id: " + id));
    }

    @Autowired
    private CuentaRepository cuentaRepository;

    public Movimiento saveMovimiento(Movimiento movimiento) {
        // Verifica que la cuenta no sea nula y esté guardada en la base de datos
        if (movimiento.getCuenta() == null || !cuentaRepository.existsById(movimiento.getCuenta().getId())) {
            throw new IllegalArgumentException("Cuenta inválida o no existente");
        }
        if (movimiento.getTipo() == null || movimiento.getTipo().isEmpty()) {
            throw new IllegalArgumentException("Tipo de movimiento es obligatorio");
        }
        if (movimiento.getMonto() == null) {
            throw new IllegalArgumentException("Monto es obligatorio");
        }
        if (movimiento.getFecha() == null) {
            throw new IllegalArgumentException("Fecha es obligatoria");
        }
        
        return movimientoRepository.save(movimiento);
    }
    
    public Movimiento updateMovimiento(Long id, Movimiento movimientoDetails) {
        Movimiento movimiento = movimientoRepository.findById(id).orElse(null);
        if (movimiento != null) {
            movimiento.setTipo(movimientoDetails.getTipo());
            movimiento.setMonto(movimientoDetails.getMonto());
            movimiento.setFecha(movimientoDetails.getFecha());
            movimiento.setCuenta(movimientoDetails.getCuenta());
            return movimientoRepository.save(movimiento);
        } else {
            return null;
        }
    }

    public void deleteMovimiento(Long id) {
        movimientoRepository.deleteById(id);
    }
}
