/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase DAO para gestionar asistencias en la base de datos.
 * Se asegura que la conexión se maneje correctamente.
 */
public class AsistenciaDAO { 
    
    private static final Logger LOGGER = Logger.getLogger(AsistenciaDAO.class.getName());
    private Conexion conexion;
    private Connection con;

    // Constructor: Inicializa la conexión con control de errores
    public AsistenciaDAO() {
        try {
            this.conexion = new Conexion();
            this.con = conexion.getConnection();
            
            if (this.con == null) {
                LOGGER.log(Level.SEVERE, "Error: No se pudo establecer conexión con la base de datos.");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al intentar conectar a la base de datos.", e);
        }
    }

    /**
     * Registra la entrada de un usuario en la base de datos.
     */
    public boolean registrarEntrada(int idUsuario) {
        if (con == null) {
            LOGGER.log(Level.SEVERE, "No se puede registrar la entrada. Conexión no establecida.");
            return false;
        }

        String sql = "INSERT INTO asistencias (id_usuario, fecha, hora_entrada) VALUES (?, CURDATE(), CURTIME())";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al registrar entrada del usuario: " + idUsuario, e);
        }
        return false;
    }

    /**
     * Registra la salida de un usuario en la base de datos.
     */
    public boolean registrarSalida(int idUsuario) {
        if (con == null) {
            LOGGER.log(Level.SEVERE, "No se puede registrar la salida. Conexión no establecida.");
            return false;
        }

        String sql = "UPDATE asistencias SET hora_salida = CURTIME() WHERE id_usuario = ? AND fecha = CURDATE() AND hora_salida IS NULL";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al registrar salida del usuario: " + idUsuario, e);
        }
        return false;
    }

    /**
     * Obtiene el historial de asistencias de un usuario.
     */
    public List<Asistencia> obtenerHistorial(int idUsuario) {
        List<Asistencia> historial = new ArrayList<>();
        
        if (con == null) {
            LOGGER.log(Level.SEVERE, "No se puede obtener el historial. Conexión no establecida.");
            return historial;
        }

        String sql = "SELECT * FROM asistencias WHERE id_usuario = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Asistencia asistencia = new Asistencia();
                    asistencia.setId(rs.getInt("id"));
                    asistencia.setIdUsuario(rs.getInt("id_usuario"));
                    asistencia.setFecha(rs.getDate("fecha"));
                    asistencia.setHoraEntrada(rs.getTime("hora_entrada"));
                    asistencia.setHoraSalida(rs.getTime("hora_salida"));
                    historial.add(asistencia);
                }
            }
        } catch (SQLException e) { 
            LOGGER.log(Level.SEVERE, "Error al obtener historial de asistencias del usuario: " + idUsuario, e);
        }
        return historial;
    }

    /**
     * Cierra la conexión con la base de datos cuando ya no se necesite.
     */
    public void cerrarConexion() {
        if (con != null) {
            try {
                con.close();
                LOGGER.info("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error al cerrar la conexión.", e);
            }
        }
    }
}
