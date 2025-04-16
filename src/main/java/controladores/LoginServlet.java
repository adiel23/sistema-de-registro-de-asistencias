/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import modelos.Conexion;
import modelos.Usuario;
import modelos.UsuarioDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        String username = request.getParameter("usuario");
        String password = request.getParameter("contrasenia");

        Connection con = Conexion.getConnection();
        UsuarioDAO usuarioDAO = new UsuarioDAO(con);
        Usuario usuario = usuarioDAO.validarUsuario(username, password);
        
        if (usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("id", usuario.getId());
            session.setAttribute("rol", usuario.getRol());
            
            response.sendRedirect("vistas/home.jsp");
        } else {
            response.sendRedirect("vistas/login.jsp?error=1");
        }
    }
}
