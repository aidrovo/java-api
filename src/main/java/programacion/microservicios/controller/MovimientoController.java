package programacion.microservicios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import programacion.microservicios.exception.EntityNotFoundException;
import programacion.microservicios.exception.InvalidDataException;
import programacion.microservicios.model.Movimiento;
import programacion.microservicios.service.MovimientoService;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> getMovimientoById(@PathVariable Long id) {
        Movimiento movimiento = movimientoService.getMovimientoById(id);
        if (movimiento == null) {
            throw new EntityNotFoundException("Movimiento no encontrado con id: " + id);
        }
        return ResponseEntity.ok(movimiento);
    }

    @PostMapping
    public ResponseEntity<Movimiento> createMovimiento(@RequestBody Movimiento movimiento) {
        // Validar datos antes de crear
        if (movimiento.getTipo() == null || movimiento.getTipo().isEmpty()) {
            throw new InvalidDataException("Tipo de movimiento es obligatorio");
        }
        Movimiento createdMovimiento = movimientoService.saveMovimiento(movimiento);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMovimiento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movimiento> updateMovimiento(@PathVariable Long id, @RequestBody Movimiento movimiento) {
        movimiento.setId(id);
        Movimiento updatedMovimiento = movimientoService.saveMovimiento(movimiento);
        return ResponseEntity.ok(updatedMovimiento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimiento(@PathVariable Long id) {
        movimientoService.deleteMovimiento(id);
        return ResponseEntity.noContent().build();
    }
}
