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
    
    // LISTA DE EQUIPOS
    private ArrayList<Equipo> listaEquipos;
    public ArrayList<Equipo> getInventario(){
        return listaEquipos;
    }
    
    // LISTA DE PRÉSTAMOS
    private ArrayList<Prestamo> listaPrestamos;
    public ArrayList<Prestamo> getPrestamos(){
        return listaPrestamos;
    }
    
    // LISTA DE PERSONAS
    private ArrayList<Persona> listaPersonas;
    public ArrayList<Persona> getPersonas(){
        return listaPersonas;
    }
    
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
        if (existeEquipo(id)){
            return false;
        }
        return listaEquipos.add(new Equipo(id, nombre, descripcion));
    }
    
    public boolean eliminarEquipo(String idEquipo){
        if (! existeEquipo(idEquipo)){
            return false;
        }
        listaEquipos.removeIf(e -> e.getIdEquipo().equals(idEquipo));
        return true;
    }
    public boolean registrarUsuario(String nombre, String rut) throws IllegalAccessException{
        if(existePersona(Rut.valueOf(rut))){
            return false;
        }
        return listaPersonas.add(new Persona(nombre,Rut.valueOf(rut)));
    }
    
    public boolean registrarEncargado(String rut, String nombre, String correo, String telefono, String contrasenia) throws IllegalAccessException{
        Rut parsedRut = Rut.valueOf(rut);
        
        if (existeEncargado(parsedRut)){ // el encargado ya esta registrado
            return false;
        }
        
        Encargado encargado = new Encargado(parsedRut, nombre, correo, telefono, contrasenia);
        this.listaPersonas.add(encargado);
        return true;
    }
    
    public boolean registrarPrestamo(String nombre, String rut, String idEquipo, LocalTime horaDevolucion, Encargado encargado) throws IllegalAccessException{
        Usuario usuario = buscarUsuario(Rut.valueOf(rut));
        if (usuario == null){
            return false; // no se encontró al usuario
        }
        
        Equipo equipo = buscarEquipo(idEquipo);
        if (equipo == null){
            return false;
        }
        
        if (existePrestamo(idEquipo)){
            return false;
        }
        listaPrestamos.add( 
                new Prestamo(
                        LocalDate.now(), LocalTime.now(), horaDevolucion, equipo, usuario, encargado
                )
        );
        return true;
    }
    
    public boolean eliminarPrestamo(String idEquipo) {
        Prestamo prestamo = buscarPrestamo(idEquipo);
        
        if (prestamo == null){ // No se encontró el préstamo
            return false;
        }
        return listaPrestamos.remove(prestamo);
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
            
            if (encargado == null){ // el encargado no está registrado en el sistema 
                return false;
            }
            
            if (encargado.getContrasenia().equals(contrasenia)){ // las contraseñas no coinciden
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
    
    
    // =======================     SECCIÓN BÚSQUEDAS     ==================== // 

    
    private boolean existePersona(Rut rut){
        return buscarPersona(rut) != null;
    }
    
    private boolean existeEncargado(Rut rut){
        return buscarEncargado(rut) != null;
    }
    
    private boolean existeUsuario(Rut rut){
        return buscarUsuario(rut) != null;
    }
    
    private boolean existeEquipo(String idEquipo){
        return buscarEquipo(idEquipo) != null;
    }
    
    private boolean existePrestamo(String idEquipo){
        return buscarPrestamo(idEquipo) != null;
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
        Persona pers = buscarPersona(rut);
        if (pers == null || !(pers instanceof Encargado)){
            return null;
        }
        return (Encargado) pers;
    }
    
    private Usuario buscarUsuario(Rut rut){
        Persona pers = buscarPersona(rut);
        if (pers == null || !(pers instanceof Usuario)){
            return null;
        }
        return (Usuario) pers;
    }
    
    private Equipo buscarEquipo(String id){
        for (Equipo e : listaEquipos){
            if (e.getIdEquipo().equals(id)){
                return e;
            }
        }
        return null;
    }
    
    private Prestamo buscarPrestamo(String idEquipo){ // buscar un préstamo por el id del equipo prestado
        for (Prestamo p : listaPrestamos){
            if (p.getEquipo().getIdEquipo().equals(idEquipo)){
                return p;
            }
        }
        return null;
    }
}
