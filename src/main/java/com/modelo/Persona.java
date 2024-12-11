package com.modelo;

import java.time.LocalDate;
import java.util.HashSet;

public class Persona {
    private final String nombre;
    private final Rut rut;
    //private final LocalDate fechaNacimiento;
    //private String nroTelefono; // no es final porque puede cambiar
    private final HashSet<Rol> roles;

    public Persona(String nombre, Rut rut) { // crear una persona sin roles inicialmente
        this.nombre = nombre;
        this.rut = rut;
        //this.fechaNacimiento = fechaNacimiento;
        //this.nroTelefono = nroTelefono;
        this.roles = new HashSet<>(2); // Sólo se aceptan dos roles como máximo: ESTUDIANTE y/o PROFESOR
    }

    public String getNombre() {
        return nombre;
    }

    public Rut getRut() {
        return rut;
    }

    public HashSet<Rol> getRoles() {
        return roles;
    }

    public void setRol(Rol rol) {
        this.roles.add(rol);
    }

    public boolean esProfesor(){
        return this.roles.contains(Rol.PROFESOR);
    }

    public boolean esEstudiante(){
        return this.roles.contains(Rol.ESTUDIANTE);
    }
}
