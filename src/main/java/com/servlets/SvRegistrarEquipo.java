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
        if(success){
            // Guardar datos después de registrar
                ControladorSistema.getInstance().guardarDatosSistema();
        }
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
