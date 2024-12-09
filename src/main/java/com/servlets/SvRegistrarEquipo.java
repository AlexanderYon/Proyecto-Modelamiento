/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;

import com.controlador.ControladorBodega;
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

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    // <editor-fold defaultstate="collapsed" descripcion="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
        
        String  id = request.getParameter("id"),
                nombre = request.getParameter("nombre"),
                descripcion = request.getParameter("descripcion");
        
        boolean success = ControladorBodega.registrarEquipo(id, nombre, descripcion); // obtener si el resultado fue exitoso o no
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
