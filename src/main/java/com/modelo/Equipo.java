package com.modelo;

import java.io.Serializable;

public class Equipo implements Serializable {
    // El id de equipo puede ser lo que la organización decida;
    // puede ser un número de identificación, un nombre, un número de serie, código de producto, etc.
    private final String idEquipo, marca;
    private String descripcion;


    private EstadoEquipo estado;
    private Prestamo prestamo;
    private boolean estaPrestado;

    public Equipo(String idEquipo, String marca, String descripcion) {
        this.idEquipo = idEquipo;
        this.marca = marca;
        this.descripcion = descripcion;
        this.estado = EstadoEquipo.UTILIZABLE; // todos los equipos se crean utilizables por defecto
        estaPrestado=false; //los equipos ingresan sin prestamo activo
    }

    public String getMarca() {
        return marca;
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
