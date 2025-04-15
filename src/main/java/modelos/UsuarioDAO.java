/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author arthu
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {
    public Usuario validarUsuario(String username, String password) {
        Usuario usuario = null;
        System.out.println("Intentando validar usuario: " + username + " con contraseña: " + password);
        try (Connection con = Conexion.getConnection())
        { // Usamos la clase Conexion
            PreparedStatement ps = con.prepareStatement("SELECT * FROM usuarios WHERE usuario =? AND contrasenia=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuario = new Usuario(rs.getInt("id"), rs.getString("usuario"), rs.getString("contrasenia"), rs.getString("rol"));
                System.out.println(usuario);
            }
        } 
        catch (Exception e) {
             System.out.println("Error al validar usuario: " + e.getMessage());
            // Imprime el error en la consola
        }
        return usuario; 
    }
}
