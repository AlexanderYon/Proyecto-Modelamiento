package com.controlador;

import com.modelo.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author benja
 */
public class ControladorSistema {
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
    
    public boolean registrarEquipo(String id, String marca, String descripcion){
        
        // REEMPLAZAR LUEGO
        for(Equipo equipo:listaEquipos){ // si ya está retorna falso
            if(equipo.getIdEquipo().equalsIgnoreCase(id)){
                return false;
            }
        }
        return listaEquipos.add(new Equipo(id,marca,descripcion));
    }
    
    public boolean eliminarEquipo(String id){
        for(Equipo equipo:listaEquipos){
            if(equipo.getIdEquipo().equalsIgnoreCase(id)){
                return listaEquipos.remove(equipo); // lo encontró y lo borró correctamente
            }
        }
        return false; // falso si no existia equipo en sistema
    }
    public boolean registrarUsuario(String nombre, String rut, String fechaNacimiento, String nroTelefono) throws IllegalAccessException{
        for(Persona persona:listaPersonas){
            if(persona.getRut().equals(Rut.valueOf(rut))){
                return false; // persona ya está en el sistema
            }
        }
        return listaPersonas.add(new Persona(nombre,Rut.valueOf(rut),fechaNacimiento,nroTelefono));
    }
    
    public boolean registrarPrestamo(String nombre, String rut, String idEquipo,LocalTime horaDevolucion) throws IllegalAccessException{
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
            registrarUsuario(nombre,rut,"auxiliar","auxiliar");
        }
        if(equipoEncontrado==null){ // no se encontró equipo
            return false;
        }
        for(Prestamo prestamo:listaPrestamos){
            if(prestamo.getEquipo().getIdEquipo().equalsIgnoreCase(idEquipo)){
                return false; // equipo ya está en prestamo
            }
        }
        Prestamo nuevoPrestamo = new Prestamo(LocalDate.now(),LocalTime.now(),horaDevolucion,equipoEncontrado,personaEncontrada);
        listaPrestamos.add(nuevoPrestamo);
        personaEncontrada.agregarPrestamo(nuevoPrestamo);
        equipoEncontrado.setPrestamo(nuevoPrestamo);
        return true;
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

}
