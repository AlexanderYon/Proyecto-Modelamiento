/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;

import com.controlador.ControladorSistema;
import com.modelo.*;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author benja
 */
@WebServlet(name = "SvMostrarPrestamos", urlPatterns = {"/SvMostrarPrestamos"})
public class SvMostrarPrestamos extends HttpServlet {

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener la lista de préstamos
        ArrayList<Prestamo> prestamos = ControladorSistema.getInstance().getPrestamos(); // Este método debe retornar la lista actualizada de préstamos
        
        // Agregar la lista de préstamos al request
        request.setAttribute("listaPrestamos", prestamos);
        
        // Redirigir al JSP para mostrar los datos
        RequestDispatcher dispatcher = request.getRequestDispatcher("administracionPrestamos.jsp");
        dispatcher.forward(request, response); // Pasar el request al JSP para mostrar la lista
    }

}
