package com.modelo;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Encargado extends Persona{
    //private LocalTime horaEntrada, horaSalida;
    //private String descripcionCargo;
    private String correo, telefono, contrasenia;
    private ArrayList<Prestamo> prestamos;

    public Encargado(Rut rut, String nombre, String correo, String nroTelefono, String contrasenia) {
        super(nombre, rut);
        this.prestamos = new ArrayList<>();
        this.contrasenia = contrasenia;
        this.correo = correo;
        this.telefono = nroTelefono;
    }

    //public LocalTime getHoraEntrada() {
    //    return horaEntrada;
    //}

    //public void setHoraEntrada(LocalTime horaEntrada) {
    //    this.horaEntrada = horaEntrada;
    //}

    //public LocalTime getHoraSalida() {
    //    return horaSalida;
    //}

    //public void setHoraSalida(LocalTime horaSalida) {
    //    this.horaSalida = horaSalida;
    //}

    //public String getDescripcionCargo() {
    //    return descripcionCargo;
    //}

    //public void setDescripcionCargo(String descripcionCargo) {
    //    this.descripcionCargo = descripcionCargo;
    //}

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
