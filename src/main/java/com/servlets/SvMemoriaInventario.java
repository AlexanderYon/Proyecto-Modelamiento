/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;

import com.controlador.ControladorSistema;
import com.modelo.Equipo;
import com.modelo.Prestamo;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Este servlet está hecho para manejar los datos que se encuentran actualmente 
 * en la memoria principal y que aún no han sido gaurdados.
 * @author yonatan
 */
@WebServlet(name = "SvMemoriaInventario", urlPatterns = {"/SvMemoriaInventario"})
public class SvMemoriaInventario extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener la lista de préstamos
        ArrayList<Equipo> equipos = ControladorSistema.getInstance().getInventario(); // Este método debe retornar la lista actualizada de préstamos
        
        HttpSession session = request.getSession(); 
        session.setAttribute("listaEquipos", equipos);
        
        // Redirigir al JSP para mostrar los datos
        RequestDispatcher dispatcher = request.getRequestDispatcher("inventario.jsp");
        dispatcher.forward(request, response); // Pasar el request al JSP para mostrar la lista
    }
}
