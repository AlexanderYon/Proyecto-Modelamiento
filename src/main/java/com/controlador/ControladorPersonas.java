package com.controlador;

import com.modelo.Persona;
import com.modelo.Rut;

import java.util.HashMap;

public class ControladorPersonas {
    private final static ControladorPersonas INSTANCE = new ControladorPersonas();
    private Persona encargado; // El encargado actual del laboratorio
    private final HashMap<Rut, Persona> personas = new HashMap<>();

    public static void registrarUsuario(String nombre, String rut, String fechaNacimiento, String nroTelefono){

    }
}
