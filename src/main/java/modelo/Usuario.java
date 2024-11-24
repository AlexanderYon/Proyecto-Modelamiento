package modelo;

import java.time.LocalDate;

public class Usuario extends Persona{
    private Prestamo prestamoActual;
    private EstadoUsuario estadoUsuario;

    public Usuario(String nombre, Rut rut, LocalDate fechaNacimiento, String nroTelefono) {
        super(nombre, rut, fechaNacimiento, nroTelefono);
        prestamoActual = null;
    }

    public void agregarPrestamo(Prestamo prestamo){
        this.prestamoActual = prestamo;
    }

    public void eliminarPrestamo(){
        this.prestamoActual = null;
    }

    public Prestamo getPrestamoActual() { // usar para realzar validaciones
        return prestamoActual;
    }

}
