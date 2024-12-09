package com.modelo;
import java.time.LocalDate;
import java.time.LocalTime;

public class Encargado extends Persona{
    private LocalTime horaEntrada, horaSalida;
    private String descripcionCargo;

    public Encargado(String nombre, Rut rut, LocalDate fechaNacimiento, String nroTelefono) {
        super(nombre, rut, fechaNacimiento, nroTelefono);
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
}
