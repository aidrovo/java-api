package programacion.microservicios.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimiento")
public class Movimiento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "cuenta_id", nullable = false)  // Asegúrate de que la columna no permita valores nulos
    private Cuenta cuenta;
    
    @Column(name = "tipo", nullable = false)
    private String tipo;
    
    @Column(name = "monto", nullable = false)
    private Double monto;
    
    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
}
