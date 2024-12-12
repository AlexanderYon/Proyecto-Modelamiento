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
import jakarta.servlet.annotation.MultipartConfig;
import org.json.JSONObject;

/**
 *
 * @author yonatan
 */
@MultipartConfig
@WebServlet(name = "SvRegistrarEquipo", urlPatterns = {"/SvRegistrarEquipo"})
public class SvRegistrarEquipo extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String  id = request.getParameter("id"),
                nombre = request.getParameter("nombre"),
                descripcion = request.getParameter("descripcion");
        
        boolean success = ControladorSistema.getInstance().registrarEquipo(id, nombre, descripcion); // obtener si el resultado fue exitoso o no
        JSONObject jsonResponse = new JSONObject();
        
        if (success) {
            jsonResponse.put("success", true);
            jsonResponse.put("id", id);
            jsonResponse.put("nombre", nombre);
            jsonResponse.put("descripcion", descripcion);
        } else {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "No se pudo registrar el equipo.");
        }
        
        // Configurar la respuesta HTTP
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse.toString());
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
