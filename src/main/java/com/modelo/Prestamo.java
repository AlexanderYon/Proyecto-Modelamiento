package com.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Prestamo implements Serializable{
    private final LocalDate fecha;
    private final LocalTime horaPrestamo, horaDevolucion;
    private final Equipo equipo;
    private final Usuario usuario;
    private final Encargado encragado;

    public Prestamo(LocalDate fecha, LocalTime horaPrestamo, LocalTime horaDevolucion, Equipo equipo, Usuario usuario, Encargado encargado) {
        this.fecha = fecha;
        this.horaPrestamo = horaPrestamo;
        this.horaDevolucion = horaDevolucion;
        this.equipo = equipo;
        this.usuario = usuario;
        this.usuario.agregarPrestamo(this);
        this.encragado = encargado;
        this.encragado.agregarPrestamo(this);
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
