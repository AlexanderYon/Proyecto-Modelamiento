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
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author yonatan
 */
@WebServlet(name = "SvRegistrarEncargado", urlPatterns = {"/SvRegistrarEncargado"})
public class SvRegistrarEncargado extends HttpServlet {

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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
            throws ServletException, IOException {processRequest(request, response);
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
        
        String  rut = request.getParameter("rut"),
                nombres = request.getParameter("nombres"),
                apellidos = request.getParameter("apellidos"),
                correo = request.getParameter("correo"),
                telefono = request.getParameter("telefono"),
                contrasenia = request.getParameter("contrasenia");
        
        System.out.println("Recargando datos ENCARGADO");
        System.out.println("rut" + rut);
        System.out.println("nombre completo: " + nombres + apellidos);
        System.out.println("correo: " + correo);
        System.out.println("telefono: " + telefono);
        System.out.println("contrasenia: " + contrasenia);
        
        try {
            boolean result = ControladorSistema.getInstance().registrarEncargado(rut, nombres + " " + apellidos, correo, telefono, contrasenia);
            if (!result) {
                // Volver al login con un mensaje de error
                request.setAttribute("mensajeError", "El usuario ya está registrado");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
            response.sendRedirect("login.jsp");
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
            // Volver al login con un mensaje de error
            request.setAttribute("mensajeError", "Usuario o contraseña incorrectos.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
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
