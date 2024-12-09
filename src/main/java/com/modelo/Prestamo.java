package com.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Prestamo {
    private final LocalDate fecha;
    private final LocalTime horaPrestamo, horaDevolucion;
    private final Equipo equipo;
    private final Usuario usuario;

    public Prestamo(LocalDate fecha, LocalTime horaPrestamo, LocalTime horaDevolucion, Equipo equipo, Usuario usuario) {
        this.fecha = fecha;
        this.horaPrestamo = horaPrestamo;
        this.horaDevolucion = horaDevolucion;
        this.equipo = equipo;
        this.usuario = usuario;
        this.usuario.agregarPrestamo(this);
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHoraPrestamo() {
        return horaPrestamo;
    }

    public LocalTime getHoraDevolucion() {
        return horaDevolucion;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
