package com.modelo;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Encargado extends Persona{
    private LocalTime horaEntrada, horaSalida;
    private String descripcionCargo;
    private String contrasenia;
    private ArrayList<Prestamo> prestamos;

    public Encargado(String nombre, Rut rut, LocalDate fechaNacimiento, String nroTelefono, String contrasenia) {
        super(nombre, rut, fechaNacimiento, nroTelefono);
        this.prestamos = new ArrayList<>();
        this.contrasenia = contrasenia;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getDescripcionCargo() {
        return descripcionCargo;
    }

    public void setDescripcionCargo(String descripcionCargo) {
        this.descripcionCargo = descripcionCargo;
    }
    
    public void agregarPrestamo(Prestamo prestamo){
        this.prestamos.add(prestamo);
    }
    
    public void setContrasenia(String contrasenia){
        this.contrasenia = contrasenia;
    }

    public String getContrasenia() {
        return contrasenia;
    }
}
