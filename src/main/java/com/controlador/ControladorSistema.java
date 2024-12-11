package com.controlador;

import com.modelo.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author benja
 */
public class ControladorSistema implements Serializable {
    private ArrayList<Equipo> listaEquipos;
    private ArrayList<Prestamo> listaPrestamos;
    private ArrayList<Persona> listaPersonas;
    private static ControladorSistema INSTANCE = null;
    private Persona encargado; // El encargado actual del laboratorio
    
    private ControladorSistema(){
        listaEquipos = new ArrayList<>();
        listaPrestamos = new ArrayList<>();
        listaPersonas = new ArrayList<>();
    }
    public static ControladorSistema getInstance(){
        if (INSTANCE==null){
            INSTANCE=new ControladorSistema();
            return INSTANCE;
        }
        return INSTANCE;
    }
    
    public boolean registrarEquipo(String id, String nombre, String descripcion){
        
        // REEMPLAZAR LUEGO
        for(Equipo equipo:listaEquipos){ // si ya está retorna falso
            if(equipo.getIdEquipo().equalsIgnoreCase(id)){
                return false;
            }
        }
        return listaEquipos.add(new Equipo(id, nombre, descripcion));
    }
    
    public boolean eliminarEquipo(String idEquipo){
        for(Equipo equipo:listaEquipos){
            if(equipo.getIdEquipo().equalsIgnoreCase(idEquipo)){
                return listaEquipos.remove(equipo); // lo encontró y lo borró correctamente
            }
        }
        return false; // falso si no existia equipo en sistema
    }
    public boolean registrarUsuario(String nombre, String rut) throws IllegalAccessException{
        for(Persona persona:listaPersonas){
            if(persona.getRut().equals(Rut.valueOf(rut))){
                return false; // persona ya está en el sistema
            }
        }
        return listaPersonas.add(new Persona(nombre,Rut.valueOf(rut)));
    }
    
    public boolean registrarEncargado(String rut, String nombre, String correo, String telefono, String contrasenia) throws IllegalAccessException{
        Rut parsedRut = Rut.valueOf(rut);
        
        if (buscarEncargado(parsedRut) != null){ // el encargado ya esta registrado
            return false;
        }
        
        Encargado encargado = new Encargado(parsedRut, nombre, correo, telefono, contrasenia);
        this.listaPersonas.add(encargado);
        return true;
    }
    
    public boolean registrarPrestamo(String nombre, String rut, String idEquipo,LocalTime horaDevolucion, Encargado encargado) throws IllegalAccessException{
        Usuario personaEncontrada=null;
        Equipo equipoEncontrado=null;
        for(Persona persona:listaPersonas){
            if(persona.getRut().equals(Rut.valueOf(rut))){
                personaEncontrada=(Usuario)persona;
            }
        }
        for(Equipo equipo:listaEquipos){
            if(equipo.getIdEquipo().equalsIgnoreCase(idEquipo)){
                equipoEncontrado=equipo;
            }
        }
        if(personaEncontrada==null){ // no se encontró persona 
            registrarUsuario(nombre,rut);
        }
        if(equipoEncontrado==null){ // no se encontró equipo
            return false;
        }
        for(Prestamo prestamo:listaPrestamos){
            if(prestamo.getEquipo().getIdEquipo().equalsIgnoreCase(idEquipo)){
                return false; // equipo ya está en prestamo
            }
        }
        Prestamo nuevoPrestamo = new Prestamo(LocalDate.now(),LocalTime.now(),horaDevolucion,equipoEncontrado,personaEncontrada,encargado);
        listaPrestamos.add(nuevoPrestamo);
        personaEncontrada.agregarPrestamo(nuevoPrestamo);
        equipoEncontrado.setPrestamo(nuevoPrestamo);
        equipoEncontrado.cambiarEstadoPrestamo();
        return true;
    }
    
    public boolean eliminarPrestamo(String idEquipo) {
        for(Prestamo prestamo:listaPrestamos){
            if(prestamo.getEquipo().getIdEquipo().equalsIgnoreCase(idEquipo)){
                prestamo.getUsuario().eliminarPrestamo();
                prestamo.getEquipo().cambiarEstadoPrestamo();
                return listaPrestamos.remove(prestamo);
            }
        }
        return false; // no habia prestamo con los datos
    }
    
    // Obtener credenciales de un encargado a partir de su Rut
    private String[] getCredencialesDe(Rut rut){
        Encargado perso = buscarEncargado(rut);

        if (perso == null) {
            return null;
        } else {
            return new String[] {
                perso.getRut().toString(), 
                perso.getContrasenia()
            };
        }
    }
    
    public boolean autenticarEncargado(String rut, String contrasenia) {
        try {
            Rut parsedRut = Rut.valueOf(rut);
            Encargado encargado = buscarEncargado(parsedRut);
            
            if (encargado == null){
                return false;
            }
            
            if (encargado.getContrasenia().equals(contrasenia)){
                return true;
            }
            
            return false;
        } catch (IllegalAccessException e) { // el rut dado es incorrecto (no cumple formato o no existe) 
            return false;
        }
    }
    
    public void guardarDatosSistema() throws IOException {
        FileOutputStream fos = new FileOutputStream("Datos.obj");
        ObjectOutputStream datos = new ObjectOutputStream(fos);
        datos.writeObject(this);
        datos.close();
    }
    public void recuperarDatosSistema() throws IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream("Datos.obj");
        ObjectInputStream datos = new ObjectInputStream(fis);
        INSTANCE = (ControladorSistema)datos.readObject();
        datos.close();
    }
    
    
    private Persona buscarPersona(Rut rut){
        for (Persona pers : this.listaPersonas){
            if (pers.getRut().equals(rut)){
                return pers;
            }
        }
        return null;
    }
    
    private Encargado buscarEncargado(Rut rut){
        for (Persona pers : this.listaPersonas){
            if (pers.getRut().equals(rut) && (pers instanceof Encargado)){
                return (Encargado) pers;
            }
        }
        return null; 
    }

}
