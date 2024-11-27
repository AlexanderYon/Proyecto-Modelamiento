package modelo;

public class Equipo {
    private final String idEquipo, marca, modelo;
    private String descripcion;
    private EstadoEquipo estado;
    private Prestamo prestamo;

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

    public void cambiarEstado() {
        this.estado = (estado == EstadoEquipo.UTILIZABLE) ? EstadoEquipo.INUTILIZABLE : EstadoEquipo.UTILIZABLE;
    }
}
