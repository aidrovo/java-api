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
import programacion.microservicios.exception.CuentaNotFoundException;

import programacion.microservicios.model.Cuenta;
import programacion.microservicios.model.Cliente;
import programacion.microservicios.repository.CuentaRepository;

@SpringBootTest
public class CuentaServiceTest {

    @Mock
    private CuentaRepository cuentaRepository;

    @InjectMocks
    private CuentaService cuentaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCuenta() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);

        Cuenta cuenta = new Cuenta();
        cuenta.setNumero("478758");
        cuenta.setTipo("Ahorro");
        cuenta.setSaldoInicial(2000.00);
        cuenta.setEstado(true);
        cuenta.setCliente(cliente);

        when(cuentaRepository.save(any(Cuenta.class))).thenReturn(cuenta);

        Cuenta createdCuenta = cuentaService.saveCuenta(cuenta);
        assertNotNull(createdCuenta);
        assertEquals("478758", createdCuenta.getNumero());
    }

    @Test
    void testFindCuentaById() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);

        Cuenta cuenta = new Cuenta();
        cuenta.setId(1L);
        cuenta.setNumero("478758");
        cuenta.setCliente(cliente);

        when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuenta));

        Cuenta foundCuenta = cuentaService.getCuentaById(1L);
        assertNotNull(foundCuenta);
        assertEquals("478758", foundCuenta.getNumero());
    }
    
    @Test
    public void testGetCuentaById_NotFound() {
        Long id = 1L;
        when(cuentaRepository.findById(id)).thenReturn(Optional.empty());
        Exception exception = assertThrows(CuentaNotFoundException.class, () -> {
            cuentaService.getCuentaById(id);
        });
        assertEquals("Cuenta not found with id: " + id, exception.getMessage());
    }
}