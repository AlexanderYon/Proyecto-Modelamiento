package modelo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Equipo implements Serializable {
    @Id
    @Column(name = "id_equipo")
    private final String idEquipo;

    @Column(nullable = false)
    private final String marca;

    @Column(nullable = false)
    private final String modelo;

    private String descripcion;

    @Enumerated(EnumType.STRING)
    private EstadoEquipo estado;

    @OneToOne(fetch = FetchType.LAZY) // Relación de uno a uno con Prestamo
    private Prestamo prestamo;

    // Constructor vacío (requerido por JPA)
    public Equipo() {
        // Constructor vacío necesario para JPA
        this.idEquipo = null;
        this.marca = null;
        this.modelo = null;
        this.descripcion = null;
        this.estado = null;
        this.prestamo = null;
    }

    // Constructor con parámetros
    public Equipo(String idEquipo, String marca, String modelo, String descripcion, EstadoEquipo estado) {
        this.idEquipo = idEquipo;
        this.marca = marca;
        this.modelo = modelo;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    // Métodos getter y setter
    public String getIdEquipo() {
        return idEquipo;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public EstadoEquipo getEstado() {
        return estado;
    }

    public void setEstado(EstadoEquipo estado) {
        this.estado = estado;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    // Método de cambio de estado
    public void cambiarEstado() {
        this.estado = (estado == EstadoEquipo.UTILIZABLE) ? EstadoEquipo.INUTILIZABLE : EstadoEquipo.UTILIZABLE;
    }
}
