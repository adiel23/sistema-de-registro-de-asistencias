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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelos.Conexion;
import modelos.Permiso;

/**
 *
 * @author arthu
 */
@WebServlet(name = "HistorialPermisosGlobalServlet", urlPatterns = {"/HistorialPermisosGlobalServlet"})
public class HistorialPermisosGlobalServlet extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String docente = request.getParameter("docente");
        String desde = request.getParameter("desde");
        String hasta = request.getParameter("hasta");
        String estado = request.getParameter("estado");

        List<Permiso> permisos = new ArrayList<>();

        try (Connection conn = Conexion.getConnection()) 
        {
            StringBuilder sql = new StringBuilder("SELECT p.*, u.nombre FROM permisos p JOIN usuarios u ON p.id_usuario = u.id WHERE 1=1");

            if (docente != null && !docente.isEmpty()) {
                sql.append(" AND u.nombre LIKE ?");
            }
            if (desde != null && !desde.isEmpty()) {
                sql.append(" AND p.fecha_solicitud >= ?");
            }
            if (hasta != null && !hasta.isEmpty()) {
                sql.append(" AND p.fecha_solicitud <= ?");
            }
            if (estado != null && !estado.isEmpty()) {
                sql.append(" AND p.estado = ?");
            }
            
            sql.append(" ORDER BY p.fecha_solicitud DESC");

            PreparedStatement stmt = conn.prepareStatement(sql.toString());

            int index = 1;
            if (docente != null && !docente.isEmpty()) {
                stmt.setString(index++, "%" + docente + "%");
            }
            if (desde != null && !desde.isEmpty()) {
                stmt.setString(index++, desde);
            }
            if (hasta != null && !hasta.isEmpty()) {
                stmt.setString(index++, hasta);
            }
            if (estado != null && !estado.isEmpty()) {
                stmt.setString(index++, estado);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Permiso permiso = new Permiso();
                // Rellenar los datos del permiso desde el ResultSet
                permiso.setId(rs.getInt("id"));
                permiso.setMotivo(rs.getString("motivo"));
                permiso.setFechaSolicitud(rs.getString("fecha_solicitud"));
                permiso.setFechaInicio(rs.getString("fecha_inicio"));
                permiso.setFechaTermino(rs.getString("fecha_termino"));
                permiso.setEstado(rs.getString("estado"));
                permiso.setNombre(rs.getString("nombre")); // <- esta viene de JOIN con usuarios
                permisos.add(permiso);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("permisos", permisos);
        request.getRequestDispatcher("vistas/historial-permisos-global.jsp").forward(request, response);
    }

}
