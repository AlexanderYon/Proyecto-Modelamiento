package modelo;

import java.time.LocalDate;
import java.util.HashSet;

public class Persona {
    private final String nombre;
    private final Rut rut;
    private final LocalDate fechaNacimiento;
    private String nroTelefono; // no es final porque puede cambiar
    private final HashSet<Rol> roles;

    public Persona(String nombre, Rut rut, LocalDate fechaNacimiento, String nroTelefono) { // crear una persona sin roles inicialmente
        this.nombre = nombre;
        this.rut = rut;
        this.fechaNacimiento = fechaNacimiento;
        this.nroTelefono = nroTelefono;
        this.roles = new HashSet<>(2); // Sólo se aceptan dos roles como máximo: ESTUDIANTE y/o PROFESOR
    }

    public String getNombre() {
        return nombre;
    }

    public Rut getRut() {
        return rut;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getNroTelefono() {
        return nroTelefono;
    }

    public void setNroTelefono(String nroTelefono) { // Usar por si la persona necesita cambiar de número de contacto
        this.nroTelefono = nroTelefono;
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
