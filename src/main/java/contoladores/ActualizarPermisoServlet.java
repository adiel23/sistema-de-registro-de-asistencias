/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package contoladores;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import modelos.Conexion;

/**
 *
 * @author arthu
 */
@WebServlet(name = "ActualizarPermisoServlet", urlPatterns = {"/ActualizarPermisoServlet"})
public class ActualizarPermisoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
        
        int id = Integer.parseInt(request.getParameter("id"));
        String nuevoEstado = request.getParameter("estado");

        try (Connection conn = Conexion.getConnection()) {
            String sql = "UPDATE permisos SET estado = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nuevoEstado);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.getWriter().write("ok");
    
    }
}
