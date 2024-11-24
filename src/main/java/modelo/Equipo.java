package modelo;

public class Equipo {
    // El id de equipo puede ser lo que la organización decida;
    // puede ser un número de identificación, un nombre, un número de serie, código de producto, etc.
    private final String idEquipo, marca, modelo;
    private String descripcion;


    private EstadoEquipo estado;
    private Prestamo prestamo;

    public Equipo(String idEquipo, String marca, String modelo, String descripcion) {
        this.idEquipo = idEquipo;
        this.marca = marca;
        this.modelo = modelo;
        this.descripcion = descripcion;
        this.estado = EstadoEquipo.UTILIZABLE; // todos los equipos se crean utilizables por defecto
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getIdEquipo() {
        return idEquipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public EstadoEquipo getEstado() {
        return estado;
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
