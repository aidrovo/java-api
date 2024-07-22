/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacion.microservicios.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import programacion.microservicios.exception.ClienteNotFoundException;

import programacion.microservicios.model.Cliente;
import programacion.microservicios.repository.ClienteRepository;

@SpringBootTest
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Jose Lema");
        cliente.setDireccion("Otavalo sn y principal");
        cliente.setTelefono("098254785");
        cliente.setContraseña("1234");
        cliente.setEstado(true);

        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente createdCliente = clienteService.saveCliente(cliente);
        assertNotNull(createdCliente);
        assertEquals("Jose Lema", createdCliente.getNombre());
    }

    @Test
    void testFindClienteById() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Jose Lema");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Cliente foundCliente = clienteService.getClienteById(1L);
        assertNotNull(foundCliente);
        assertEquals("Jose Lema", foundCliente.getNombre());
    }
    
    @Test
    public void testGetClienteById_NotFound() {
        Long id = 1L;
        when(clienteRepository.findById(id)).thenReturn(Optional.empty());
        Exception exception = assertThrows(ClienteNotFoundException.class, () -> {
            clienteService.getClienteById(id);
        });
        assertEquals("Cliente not found with id: " + id, exception.getMessage());
    }
}