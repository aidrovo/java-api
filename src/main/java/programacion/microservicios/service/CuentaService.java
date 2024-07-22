package programacion.microservicios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import programacion.microservicios.model.Cuenta;
import programacion.microservicios.repository.CuentaRepository;

import java.util.List;
import programacion.microservicios.exception.CuentaNotFoundException;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    public List<Cuenta> getAllCuentas() {
        return cuentaRepository.findAll();
    }

    public Cuenta getCuentaById(Long id) {
        return cuentaRepository.findById(id)
                .orElseThrow(() -> new CuentaNotFoundException("Cuenta not found with id: " + id));
    }

    public Cuenta saveCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    public Cuenta updateCuenta(Long id, Cuenta cuentaDetails) {
        Cuenta cuenta = cuentaRepository.findById(id).orElse(null);
        if (cuenta != null) {
            cuenta.setNumero(cuentaDetails.getNumero());
            cuenta.setTipo(cuentaDetails.getTipo());
            cuenta.setSaldoInicial(cuentaDetails.getSaldoInicial());
            cuenta.setEstado(cuentaDetails.isEstado());
            cuenta.setCliente(cuentaDetails.getCliente());
            return cuentaRepository.save(cuenta);
        } else {
            return null;
        }
    }

    public void deleteCuenta(Long id) {
        cuentaRepository.deleteById(id);
    }
}
