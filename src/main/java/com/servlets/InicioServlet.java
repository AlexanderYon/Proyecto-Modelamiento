/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;

import com.controlador.ControladorSistema;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author benja
 */
@WebServlet(name = "InicioServlet", urlPatterns = {"/InicioServlet"},loadOnStartup = 1)
public class InicioServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            // Recuperar los datos del sistema al inicio
            ControladorSistema.getInstance().recuperarDatosSistema();
            System.out.println("Datos del sistema recuperados correctamente al inicio.");
        } catch (Exception e) {
            System.err.println("Error al recuperar los datos del sistema: " + e.getMessage());
        }
    }
    @Override
    public void destroy() {
        try {
            // Guardar los datos automáticamente al cerrar la aplicación
            ControladorSistema.getInstance().guardarDatosSistema();
            System.out.println("Datos del sistema guardados correctamente al cerrar la aplicación.");
        } catch (IOException e) {
            System.err.println("Error al guardar los datos del sistema: " + e.getMessage());
        }
        super.destroy();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirigir a SvMostrarPrestamos para cargar la lista de préstamos
        response.sendRedirect("SvMostrarPrestamos");
    }
    

}
