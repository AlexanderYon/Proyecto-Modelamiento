package com.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Prestamo implements Serializable{
    private final String fecha;
    private final String horaPrestamo, horaDevolucion;
    private final Equipo equipo;
    private final Usuario usuario;
    private final String motivo;
    private final Encargado encargado;

    public Prestamo(String fecha, String horaPrestamo, String horaDevolucion, Equipo equipo, Usuario usuario, String motivo, Encargado encargado) {
        this.fecha = fecha;
        this.horaPrestamo = horaPrestamo;
        this.horaDevolucion = horaDevolucion;
        this.equipo = equipo;
        this.equipo.setPrestamo(this);
        this.equipo.cambiarEstadoPrestamo();
        this.usuario = usuario;
        this.usuario.agregarPrestamo(this);
        this.encargado = encargado;
        this.encargado.agregarPrestamo(this);
        this.motivo=motivo;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHoraPrestamo() {
        return horaPrestamo;
    }

    public String getHoraDevolucion() {
        return horaDevolucion;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getMotivo() {
        return motivo;
    }
    
}
