/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.util.Calendar;
import modelos.Conexion;

/**
 *
 * @author arthu
 */
@WebServlet(name = "MarcarSalidaServlet", urlPatterns = {"/MarcarSalidaServlet"})
public class MarcarSalidaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("id");

        if (userId == null) {
            response.sendRedirect("vistas/login.jsp");
            return;
        }

        // Obtener la hora y fecha actuales
        java.util.Date now = Calendar.getInstance().getTime();
        Time horaActual = new Time(now.getTime());
        Date fechaHoy = new Date(now.getTime());

        try {
            // Conexión a la base de datos (ajustá tus datos)
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = Conexion.getConnection();

            // Actualizar la hora de salida
            String update = "UPDATE asistencias SET hora_salida = ? WHERE id_usuario = ? AND fecha = ?";
            PreparedStatement stmt = conn.prepareStatement(update);
            stmt.setTime(1, horaActual);
            stmt.setInt(2, userId);
            stmt.setDate(3, fechaHoy);

            stmt.executeUpdate();

            stmt.close();
            conn.close();

            // Volver a home.jsp
            response.sendRedirect("vistas/home.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error al marcar salida: " + e.getMessage());
        }
    }

}
