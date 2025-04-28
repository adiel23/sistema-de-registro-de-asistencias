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
import java.util.ArrayList;
import java.util.List;
import modelos.Conexion;
import modelos.Permiso;

@WebServlet(name = "MostrarPermisosPendientesServlet", urlPatterns = {"/MostrarPermisosPendientesServlet"})
public class MostrarPermisosPendientesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Permiso> listaPermisos = new ArrayList<>();

        try (Connection conn = Conexion.getConnection()) {
            
            String sql = """
                        SELECT p.id, p.fecha_solicitud, p.motivo, p.fecha_inicio, p.fecha_termino, p.estado,
                        u.nombre
                        FROM permisos p
                        JOIN usuarios u ON p.id_usuario = u.id where p.estado = 'pendiente' ORDER BY p.fecha_solicitud DESC """;
         
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Permiso p = new Permiso();
                p.setId(rs.getInt("id"));
                p.setFechaSolicitud(rs.getString("fecha_solicitud"));
                p.setMotivo(rs.getString("motivo"));
                p.setFechaInicio(rs.getString("fecha_inicio"));
                p.setFechaTermino(rs.getString("fecha_termino"));
                p.setEstado(rs.getString("estado"));
                p.setNombre(rs.getString("nombre"));
                
                listaPermisos.add(p);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("permisos", listaPermisos);
        request.getRequestDispatcher("/vistas/aprobar-permisos.jsp").forward(request, response);
    }
}


