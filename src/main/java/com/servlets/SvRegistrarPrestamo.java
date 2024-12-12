/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;
import com.controlador.ControladorSistema;
import com.modelo.Prestamo;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import org.json.JSONObject;

/**
 *
 * @author yonatan
 */
@MultipartConfig
@WebServlet(name = "SvRegistrarPrestamo", urlPatterns = {"/SvRegistrarPrestamo"})
public class SvRegistrarPrestamo extends HttpServlet {
    private ArrayList<Prestamo> listaPrestamos;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    
        // Recuperar parámetros del formulario
        String nombre = request.getParameter("nombreUsuario");
        String rut = request.getParameter("rutUsuario");
        String idEquipo = request.getParameter("idEquipo");
        String horaDevolucion = request.getParameter("horaEstmiada");
        String motivo = request.getParameter("motivo");

        try {
            // Registrar el préstamo en el sistema
            boolean registrado = ControladorSistema.getInstance()
                .registrarPrestamo(nombre, rut, idEquipo, horaDevolucion, motivo);

            if (registrado) {
                // Guardar datos después de registrar
                ControladorSistema.getInstance().guardarDatosSistema();
            }
            response.sendRedirect("SvMostrarPrestamos");
        } catch (Exception e) {
            throw new ServletException("Error al registrar el préstamo", e);
    }
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
    /*@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String  rutUsuario = request.getParameter("rutUsuario"),
                nombreUsuario = request.getParameter("nombreUsuario"),
                horaEstimada = request.getParameter("horaEstimada"),
                motivo = request.getParameter("motivo"),
                idEquipo = request.getParameter("idEquipo");
        
        //System.out.println("DATOS DE PRESTAMO NUEVO");
        //System.out.println("rut: " + rutUsuario);
        //System.out.println("nombre: " + nombreUsuario);
        //System.out.println("id equipo: " + idEquipo);
        //System.out.println("hora: " + horaEstimada);
        //System.out.println("motivo: " + motivo);
        
        boolean success = ControladorSistema.getInstance().eliminarEquipo(idEquipo);
        JSONObject jsonResponse = new JSONObject();
        
        if (success) {
            jsonResponse.put("success", true);
            jsonResponse.put("rutUsuario", rutUsuario);
            jsonResponse.put("nombreUsuario", nombreUsuario);
            jsonResponse.put("idEquipo", idEquipo);
            jsonResponse.put("horaEstimada", horaEstimada);
            jsonResponse.put("motivo", motivo);
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
    /*@Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>*/

}
