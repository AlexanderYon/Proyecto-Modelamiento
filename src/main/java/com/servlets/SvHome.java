/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


/**
 *
 * @author benja
 */
package com.servlets;

import com.controlador.ControladorSistema;
import com.modelo.Equipo;
import com.modelo.Persona;
import com.modelo.Prestamo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "SvHome", urlPatterns = {"/SvHome"})
public class SvHome extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ControladorSistema controlador = ControladorSistema.getInstance();

        // Obtener el número total de equipos
        ArrayList<Equipo> equipos = controlador.getInventario();
        int totalEquipos = equipos.size();

        // Obtener el número total de préstamos activos
        ArrayList<Prestamo> prestamos = controlador.getPrestamos();
        int prestamosActivos = prestamos.size();

        // Calcular el número de equipos disponibles (Total - Prestamos Activos)
        int equiposDisponibles = totalEquipos - prestamosActivos;

        // Obtener el número de usuarios registrados
        ArrayList<Persona> usuarios = controlador.getPersonas();
        int totalUsuarios = usuarios.size();

        // Pasar los datos a la sesión
        HttpSession session = request.getSession();
        session.setAttribute("totalEquipos", totalEquipos);
        session.setAttribute("prestamosActivos", prestamosActivos);
        session.setAttribute("equiposDisponibles", equiposDisponibles);
        session.setAttribute("totalUsuarios", totalUsuarios);

        // Redirigir al JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
