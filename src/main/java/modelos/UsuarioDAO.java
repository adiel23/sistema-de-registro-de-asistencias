//UsuarioDAO.java

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.sql.*;
import java.util.*;

public class UsuarioDAO {
    private final Connection con;

    public UsuarioDAO(Connection con) {
        this.con = con;
    }

    // LISTAR todos los usuarios
    public List<Usuario> listarUsuarios() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setUsuario(rs.getString("usuario"));
                u.setContrasenia(rs.getString("contrasenia"));
                u.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                u.setRol(rs.getString("rol"));
                lista.add(u);
            }
        }

        return lista;
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
                u.setNombre(rs.getString("nombre"));
                u.setUsuario(rs.getString("usuario"));
                u.setContrasenia(rs.getString("contrasenia"));
                u.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
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
                    u.setNombre(rs.getString("nombre"));
                    u.setUsuario(rs.getString("usuario"));
                    u.setContrasenia(rs.getString("contrasenia"));
                    u.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
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
            stmt.setString(1, u.getNombre());
            stmt.setString(2, u.getUsuario());
            stmt.setString(3, u.getContrasenia());
            stmt.setDate(4, u.getFechaNacimiento());
            stmt.setString(5, u.getRol());
            stmt.executeUpdate();
        }
    }

    // ACTUALIZAR usuario
    public void actualizarUsuario(Usuario u) throws SQLException {
        String sql = "UPDATE usuarios SET nombre=?, usuario=?, contrasenia=?, fecha_nacimiento=?, rol=? WHERE id=?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, u.getNombre());
            stmt.setString(2, u.getUsuario());
            stmt.setString(3, u.getContrasenia());
            stmt.setDate(4, u.getFechaNacimiento());
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
