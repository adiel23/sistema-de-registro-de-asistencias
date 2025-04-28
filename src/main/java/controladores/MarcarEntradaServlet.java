package controladores;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.Date; 
import java.sql.PreparedStatement;
import modelos.Conexion;

@WebServlet(name = "MarcarEntradaServlet", urlPatterns = {"/MarcarEntradaServlet"})
public class MarcarEntradaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("id");

        if (userId == null) {
            response.sendRedirect("vistas/login.jsp");
            return;
        }

        Date now = new Date();
        java.sql.Time horaActual = new java.sql.Time(now.getTime());
        java.sql.Date fechaHoy = new java.sql.Date(now.getTime());

        try (Connection conn = Conexion.getConnection()) {
            String update = "UPDATE asistencias SET hora_entrada = ? WHERE id_usuario = ? AND fecha = ?";
            PreparedStatement stmt = conn.prepareStatement(update);
            stmt.setTime(1, horaActual);
            stmt.setInt(2, userId);
            stmt.setDate(3, fechaHoy);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("vistas/home.jsp");
    }
}
