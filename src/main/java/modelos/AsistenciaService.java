package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio que maneja la lógica de las asistencias
 * @author MINEDUCYT
 */
public class AsistenciaService {
    
    // Método que obtiene el historial de asistencias, refactorizado para evitar duplicación
    private List<Asistencia> obtenerHistorial(String query, Long idUsuario) {
        List<Asistencia> asistencias = new ArrayList<>();
        
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            if (idUsuario != null) {
                stmt.setLong(1, idUsuario);
            }
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Asistencia asistencia = new Asistencia();
                asistencia.setId(rs.getLong("id"));
                asistencia.setIdUsuario(rs.getLong("id_usuario"));
                asistencia.setFecha(rs.getDate("fecha"));
                asistencia.setHoraEntrada(rs.getString("hora_entrada"));
                asistencia.setHoraSalida(rs.getString("hora_salida"));
                asistencias.add(asistencia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return asistencias;
    }
    
    // Obtener el historial de asistencias de un usuario específico
    public List<Asistencia> obtenerHistorialPorUsuario(Long idUsuario) {
        String query = "SELECT * FROM asistencias WHERE id_usuario = ?";
        return obtenerHistorial(query, idUsuario);
    }

    // Obtener el historial global de todas las asistencias
    public List<Asistencia> obtenerHistorialGlobal() {
        String query = "SELECT * FROM asistencias";
        return obtenerHistorial(query, null);
    }
}
