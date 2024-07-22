package programacion.microservicios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import programacion.microservicios.exception.EntityNotFoundException;
import programacion.microservicios.model.Cuenta;
import programacion.microservicios.service.CuentaService;

import org.springframework.http.HttpStatus;
import programacion.microservicios.exception.InvalidDataException;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> getCuentaById(@PathVariable Long id) {
        Cuenta cuenta = cuentaService.getCuentaById(id);
        if (cuenta == null) {
            throw new EntityNotFoundException("Cuenta no encontrada con id: " + id);
        }
        return ResponseEntity.ok(cuenta);
    }

    @PostMapping
    public ResponseEntity<Cuenta> createCuenta(@RequestBody Cuenta cuenta) {
        // Validar datos antes de crear
        if (cuenta.getNumero().isEmpty() || cuenta.getNumero() == null) {
            throw new InvalidDataException("Número de cuenta es obligatorio");
        }
        Cuenta createdCuenta = cuentaService.saveCuenta(cuenta);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCuenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> updateCuenta(@PathVariable Long id, @RequestBody Cuenta cuenta) {
        cuenta.setId(id);
        Cuenta updatedCuenta = cuentaService.saveCuenta(cuenta);
        return ResponseEntity.ok(updatedCuenta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuenta(@PathVariable Long id) {
        cuentaService.deleteCuenta(id);
        return ResponseEntity.noContent().build();
    }
}
