/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contoladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;  
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.AsistenciaDAO;
import java.io.IOException;
import java.util.List;
import modelos.Asistencia;

/**
 *
 * @author MINEDUCYT
 */
public class AsistenciaServlet {
private AsistenciaDAO asistenciaDAO = new AsistenciaDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        String accion = request.getParameter("accion");

        boolean resultado = false;
        if ("entrada".equals(accion)) {
            resultado = asistenciaDAO.registrarEntrada(idUsuario);
        } else if ("salida".equals(accion)) {
            resultado = asistenciaDAO.registrarSalida(idUsuario);
        }

        if (resultado) {
            response.sendRedirect("historial.jsp?idUsuario=" + idUsuario);
        } else {
            response.getWriter().println("Error al registrar asistencia.");
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        List<Asistencia> historial = asistenciaDAO.obtenerHistorial(idUsuario);
        request.setAttribute("historial", historial);
        request.getRequestDispatcher("historial.jsp").forward(request, response);
    }
}
