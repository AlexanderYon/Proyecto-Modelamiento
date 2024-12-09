package com.controlador;

import com.modelo.Equipo;

import java.util.HashMap;

public class ControladorBodega {
    public static boolean registrarEquipo(String id, String nombre, String descripcion){
        
        // REEMPLAZAR LUEGO
        System.out.println("Imprimiendo datos recibidos para registro de equipo");
        System.out.println(id + " - " + nombre + " - " + descripcion);
        return true; // Se envía true si salio todo bien. Si hay algun error se envía una excepción
    }
    
    public static boolean eliminarEquipo(String id){
        System.out.println("Imprimiendo datos recibidos para registro de equipo");
        System.out.println(id);
        return true;
    }
}
