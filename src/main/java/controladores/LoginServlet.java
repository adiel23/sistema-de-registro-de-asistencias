//LoginServlet.java
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import modelos.Conexion;
import modelos.Usuario;
import modelos.UsuarioDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuarioInput = request.getParameter("usuario");
        String contraseniaInput = request.getParameter("contrasenia");

        try (Connection con = Conexion.getConnection()) {
            UsuarioDAO usuarioDAO = new UsuarioDAO(con);
            Usuario usuario = usuarioDAO.obtenerPorCredenciales(usuarioInput, contraseniaInput);

            if (usuario != null) {
                // Guardar sesión
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuario);

                // Redirigir según rol
                if ("admin".equals(usuario.getRol())) {
                    response.sendRedirect("admin/dashboard.jsp");
                } else if ("docente".equals(usuario.getRol())) {
                    response.sendRedirect("docente/dashboard.jsp");
                } else {
                        
                    response.sendRedirect("login.jsp?error=rol");
                }
            } else {
                response.sendRedirect("login.jsp?error=credenciales");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=servidor");
        }
    }
}
