package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.*;

public class UsuarioDAO {
    private Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Usuario> listarUsuarios() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("nombre"));
                u.setUsername(rs.getString("usuario"));
                u.setPassword(rs.getString("contrasenia"));
                u.setBirthDate(rs.getDate("fecha_nacimiento"));
                u.setRol(rs.getString("rol"));
                lista.add(u);
            }
        }
        return lista;
    }

    public Usuario obtenerUsuario(int id) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("nombre"));
                u.setUsername(rs.getString("usuario"));
                u.setPassword(rs.getString("contrasenia"));
                u.setBirthDate(rs.getDate("fecha_nacimiento"));
                u.setRol(rs.getString("rol"));
                return u;
            }
        }
        return null;
    }
    
    public void agregarUsuario(Usuario u) throws SQLException {
        String sql = "INSERT INTO usuarios (nombre, usuario, contrasenia, fecha_nacimiento, rol) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getUsername());
            stmt.setString(3, u.getPassword());
            stmt.setDate(4, new java.sql.Date(u.getBirthDate().getTime()));
            stmt.setString(5, u.getRol());
            stmt.executeUpdate();
        }
    }

    public void actualizarUsuario(Usuario u) throws SQLException {
        String sql = "UPDATE usuarios SET nombre=?, usuario=?, contrasenia=?, fecha_nacimiento=?, rol=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getUsername());
            stmt.setString(3, u.getPassword());
            stmt.setDate(4, new java.sql.Date(u.getBirthDate().getTime()));
            stmt.setString(5, u.getRol());
            stmt.setInt(6, u.getId());
            stmt.executeUpdate();
        }
    }

    public void eliminarUsuario(int id) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
   public Usuario validarUsuario(String usuario, String contrasenia) {
    String sql = "SELECT * FROM usuarios WHERE usuario = ? AND contrasenia = ?";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setString(1, usuario);
        stmt.setString(2, contrasenia);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Usuario u = new Usuario();
            u.setId(rs.getInt("id"));
            u.setName(rs.getString("nombre"));
            u.setUsername(rs.getString("usuario"));
            u.setPassword(rs.getString("contrasenia"));
            u.setBirthDate(rs.getDate("fecha_nacimiento"));
            u.setRol(rs.getString("rol"));
            return u;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

    
}
