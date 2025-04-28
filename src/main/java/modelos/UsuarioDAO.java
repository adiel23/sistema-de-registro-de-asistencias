package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.*;

public class UsuarioDAO {
    private final Connection con;

    public UsuarioDAO(Connection con) {
        this.con = con;
    }

    // LISTAR todos los usuarios
   // Método de tu rama actual
    public List<Usuario> listarUsuarios() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
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

    // Método que venía de master
    public Usuario validarUsuario(String username, String password) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE usuario =? AND contrasenia=?";
        System.out.println("Intentando validar usuario: " + username + " con contraseña: " + password);
        
        try (PreparedStatement ps = con.prepareStatement(sql))
        { // Usamos la clase Conexion
            
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setUsername(rs.getString("usuario"));
                usuario.setPassword(rs.getString("contrasenia"));
                usuario.setRol(rs.getString("rol"));
                System.out.println(usuario);
            }
        } 
        catch (Exception e) {
             System.out.println("Error al validar usuario: " + e.getMessage());
            // Imprime el error en la consola
        }
        return usuario; 
    }

    public Usuario obtenerPorCredenciales(String usuario, String contrasenia) throws SQLException {
    String sql = "SELECT * FROM usuarios WHERE usuario = ? AND contrasenia = ?";
    try (PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setString(1, usuario);
        stmt.setString(2, contrasenia);
        try (ResultSet rs = stmt.executeQuery()) {
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
    }
    return null;
}

    
    // OBTENER un usuario por ID
    public Usuario obtenerUsuario(int id) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        Usuario u = new Usuario();

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    u.setId(rs.getInt("id"));
                    u.setName(rs.getString("nombre"));
                    u.setUsername(rs.getString("usuario"));
                    u.setPassword(rs.getString("contrasenia"));
                    u.setBirthDate(rs.getDate("fecha_nacimiento"));
                    u.setRol(rs.getString("rol"));
                }
            }
        }

        return u;
    }

    // AGREGAR nuevo usuario
    public void agregarUsuario(Usuario u) throws SQLException {
        String sql = "INSERT INTO usuarios (nombre, usuario, contrasenia, fecha_nacimiento, rol) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getUsername());
            stmt.setString(3, u.getPassword());
            stmt.setDate(4, u.getBirthDate());
            stmt.setString(5, u.getRol());
            stmt.executeUpdate();
        }
    }

    // ACTUALIZAR usuario
    public void actualizarUsuario(Usuario u) throws SQLException {
        String sql = "UPDATE usuarios SET nombre=?, usuario=?, contrasenia=?, fecha_nacimiento=?, rol=? WHERE id=?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getUsername());
            stmt.setString(3, u.getPassword());
            stmt.setDate(4, u.getBirthDate());
            stmt.setString(5, u.getRol());
            stmt.setInt(6, u.getId());
            stmt.executeUpdate();
        }
    }

    // ELIMINAR usuario
    public void eliminarUsuario(int id) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
