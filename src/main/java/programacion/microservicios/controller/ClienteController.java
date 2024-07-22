package programacion.microservicios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import programacion.microservicios.exception.EntityNotFoundException;
import programacion.microservicios.model.Cliente;
import programacion.microservicios.service.ClienteService;

import org.springframework.http.HttpStatus;
import programacion.microservicios.exception.InvalidDataException;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Cliente cliente = clienteService.getClienteById(id);
        if (cliente == null) {
            throw new EntityNotFoundException("Cliente no encontrado con id: " + id);
        }
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        // Validar datos antes de crear
        if (cliente.getNombre() == null || cliente.getNombre().isEmpty()) {
            throw new InvalidDataException("Nombre es obligatorio");
        }
        Cliente createdCliente = clienteService.saveCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        Cliente updatedCliente = clienteService.saveCliente(cliente);
        return ResponseEntity.ok(updatedCliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}
