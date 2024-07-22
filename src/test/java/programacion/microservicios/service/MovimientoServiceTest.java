/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacion.microservicios.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import programacion.microservicios.exception.MovimientoNotFoundException;

import programacion.microservicios.model.Movimiento;
import programacion.microservicios.model.Cuenta;
import programacion.microservicios.repository.MovimientoRepository;
import programacion.microservicios.repository.CuentaRepository;

@SpringBootTest
public class MovimientoServiceTest {

    @Mock
    private MovimientoRepository movimientoRepository;

    @Mock
    private CuentaRepository cuentaRepository;

    @InjectMocks
    private MovimientoService movimientoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateMovimiento() {
        Cuenta cuenta = new Cuenta();
        cuenta.setId(1L);

        Movimiento movimiento = new Movimiento();
        movimiento.setTipo("Retiro");
        movimiento.setMonto(575.00);
        movimiento.setCuenta(cuenta);
        movimiento.setFecha(LocalDateTime.now());

        when(cuentaRepository.existsById(1L)).thenReturn(true);
        when(movimientoRepository.save(any(Movimiento.class))).thenReturn(movimiento);

        Movimiento createdMovimiento = movimientoService.saveMovimiento(movimiento);
        assertNotNull(createdMovimiento);
        assertEquals("Retiro", createdMovimiento.getTipo());
    }

    @Test
    void testFindMovimientoById() {
        Cuenta cuenta = new Cuenta();
        cuenta.setId(1L);

        Movimiento movimiento = new Movimiento();
        movimiento.setId(1L);
        movimiento.setTipo("Retiro");
        movimiento.setCuenta(cuenta);
        movimiento.setFecha(LocalDateTime.now());

        when(movimientoRepository.findById(1L)).thenReturn(Optional.of(movimiento));

        Movimiento foundMovimiento = movimientoService.getMovimientoById(1L);
        assertNotNull(foundMovimiento);
        assertEquals("Retiro", foundMovimiento.getTipo());
    }
    
    @Test
    public void testGetMovimientoById_NotFound() {
        Long id = 1L;
        when(movimientoRepository.findById(id)).thenReturn(Optional.empty());
        Exception exception = assertThrows(MovimientoNotFoundException.class, () -> {
            movimientoService.getMovimientoById(id);
        });
        assertEquals("Movimiento not found with id: " + id, exception.getMessage());
    }
}