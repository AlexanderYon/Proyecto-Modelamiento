/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.controlador;

import com.modelo.Equipo;
import com.modelo.Persona;
import com.modelo.Rut;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import com.modelo.Prestamo;

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
    
    public boolean registrarEquipo(String id, String nombre, String descripcion){
        
        // PARA DEBUGEAR
        System.out.println("Imprimiendo datos recibidos para registro de equipo");
        System.out.println(id + " - " + nombre + " - " + descripcion);
        return true;
    }
    
    public boolean eliminarEquipo(String id){
        System.out.println("Imprimiendo datos recibidos para registro de equipo");
        System.out.println(id);
        return true;
    }
    
    public boolean registrarPrestamo(String rutUsuario, String nombreUsuario, String idEquipo, String fecha, String motivo){
        return true;
    }
    
    public boolean eliminarPrestamo(String idEquipo){
        return true;
    }
    
    public boolean registrarUsuario(String nombre, String rut, String fechaNacimiento, String nroTelefono){
       return true;
    }
    
    public boolean eliminarUusario(String rut){ // no estoy tan seguro de si esto sea útil ya
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
