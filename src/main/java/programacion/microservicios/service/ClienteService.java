package programacion.microservicios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import programacion.microservicios.model.Cliente;
import programacion.microservicios.repository.ClienteRepository;

import java.util.List;
import programacion.microservicios.exception.ClienteNotFoundException;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente not found with id: " + id));
    }

    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(Long id, Cliente clienteDetails) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente != null) {
            cliente.setNombre(clienteDetails.getNombre());
            cliente.setDireccion(clienteDetails.getDireccion());
            cliente.setTelefono(clienteDetails.getTelefono());
            cliente.setContraseña(clienteDetails.getContraseña());
            cliente.setEstado(clienteDetails.isEstado());
            return clienteRepository.save(cliente);
        } else {
            return null;
        }
    }

    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
