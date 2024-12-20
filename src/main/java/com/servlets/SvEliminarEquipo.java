/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;

import com.controlador.ControladorSistema;
import jakarta.json.JsonObject;
import jakarta.json.stream.JsonParser;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.stream.Collectors;
import org.json.JSONObject;

/**
 *
 * @author yonatan
 */
@WebServlet(name = "SvEliminarEquipo", urlPatterns = {"/SvEliminarEquipo"})
public class SvEliminarEquipo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String id = request.getParameter("id"); // ID del equipo
        boolean eliminado = ControladorSistema.getInstance().eliminarEquipo(id); // Lógica para eliminar el equipo
        response.sendRedirect("SvMemoriaInventario");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String id = request.getParameter("idEquipo"); // ID del equipo
        boolean eliminado = ControladorSistema.getInstance().eliminarEquipo(id); // Lógica para eliminar el equipo
        response.sendRedirect("SvMemoriaInventario");

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
