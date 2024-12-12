/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.listeners;
import com.controlador.ControladorSistema;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.io.IOException;

/**
 * Este es el Listener que se encarga de cargar / guardar los datos en memoria de 
 * forma automática al desplegar / cerrar la aplicación. La notacion @WebListener
 * hace que no sea necesario hacer llamados manuales a esta clase
 * @author yonatan
 */

@WebListener
public class DatosSistemaListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            // Guardar los datos al detener la aplicación
            ControladorSistema.getInstance().guardarDatosSistema();
            System.out.println("Datos del sistema guardados correctamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar los datos del sistema: " + e.getMessage());
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            // Llamar al método de recuperación al iniciar la aplicación
            ControladorSistema.getInstance().recuperarDatosSistema();
            System.out.println("Datos del sistema cargados correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar los datos del sistema: " + e.getMessage());
        }
    }
}
