package contoladores;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Conexion;
import modelos.Permiso;

@WebServlet(name = "HistorialPermisosPersonalServlet", urlPatterns = {"/HistorialPermisosPersonalServlet"})
public class HistorialPermisosPersonalServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String desde = request.getParameter("desde");
        String hasta = request.getParameter("hasta");
        String estado = request.getParameter("estado");
        
        HttpSession session = request.getSession();
        
        int userId = (Integer) session.getAttribute("id");
        String userName = (String) session.getAttribute("name");
        
        List<Permiso> permisos = new ArrayList<>();
        
        try (Connection conn = Conexion.getConnection()) {
            StringBuilder sql = new StringBuilder("select * from permisos where id_usuario = ?");

            if (desde != null && !desde.isEmpty()) {
                sql.append(" AND fecha_solicitud >= ?");
            }
            if (hasta != null && !hasta.isEmpty()) {
                sql.append(" AND fecha_solicitud <= ?");
            }
            if (estado != null && !estado.isEmpty()) {
                sql.append(" AND estado = ?");
            }
            
            sql.append(" ORDER BY fecha_solicitud DESC");

            PreparedStatement stmt = conn.prepareStatement(sql.toString());
            
            int index = 1;
            
            stmt.setInt(index++, userId);
            
            if (desde != null && !desde.isEmpty()) {
                stmt.setString(index++, desde);
            }
            if (hasta != null && !hasta.isEmpty()) {
                stmt.setString(index++, hasta);
            }
            if (estado != null && !estado.isEmpty()) {
                stmt.setString(index++, estado);
            }
            
            ResultSet rs =  stmt.executeQuery();
            
            while (rs.next()) {
                Permiso permiso = new Permiso();
                // Rellenar los datos del permiso desde el ResultSet
                permiso.setId(rs.getInt("id"));
                permiso.setMotivo(rs.getString("motivo"));
                permiso.setFechaSolicitud(rs.getString("fecha_solicitud"));
                permiso.setFechaInicio(rs.getString("fecha_inicio"));
                permiso.setFechaTermino(rs.getString("fecha_termino"));
                permiso.setEstado(rs.getString("estado"));
                permiso.setNombre(userName); 
                permisos.add(permiso);
            }
            
            System.out.println(permisos);
                    
        } catch (SQLException ex) {
            Logger.getLogger(HistorialPermisosPersonalServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("permisos", permisos);
        request.getRequestDispatcher("vistas/historial-permisos-personal.jsp").forward(request, response);
        
    }

}
