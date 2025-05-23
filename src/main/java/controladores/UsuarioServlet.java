//UsuarioServlet.java
package controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import modelos.Usuario;
import modelos.UsuarioDAO;
import modelos.Conexion;

@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion") != null ? request.getParameter("accion") : "listar";
        UsuarioDAO dao = new UsuarioDAO(Conexion.getConnection());

        try {
            switch (accion) {
                case "listar":
                    List<Usuario> usuarios = dao.listarUsuarios();
                    System.out.println("Usuarios encontrados: " + usuarios.size()); 
                    request.setAttribute("usuarios", usuarios);
                    request.getRequestDispatcher("/vistas/usuarios.jsp").forward(request, response);
                    break;

                      case "editar":
                    int idEditar = Integer.parseInt(request.getParameter("id"));
                    Usuario usuarioEditar = dao.obtenerUsuario(idEditar);
                    request.setAttribute("usuario", usuarioEditar);
                    request.getRequestDispatcher("/vistas/editarUsuario.jsp").forward(request, response);
                    break;

                case "eliminar":
                    int idEliminar = Integer.parseInt(request.getParameter("id"));
                    dao.eliminarUsuario(idEliminar);
                    response.sendRedirect("UsuarioServlet?accion=listar&msg=eliminado");
                    break;

                default:
                    response.sendRedirect("UsuarioServlet?accion=listar");
                    break;
            }
        } catch (Exception e) {
            throw new ServletException("Error en UsuarioServlet (GET): " + e.getMessage(), e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        UsuarioDAO dao = new UsuarioDAO(Conexion.getConnection());

        try {
            Usuario usuario = new Usuario();
            usuario.setName(request.getParameter("nombre"));
            usuario.setUsername(request.getParameter("usuario"));
            usuario.setPassword(request.getParameter("contrasenia"));
            usuario.setRol(request.getParameter("rol"));
            usuario.setBirthDate(java.sql.Date.valueOf(request.getParameter("fechaNacimiento")));

            // Validación básica
            if (usuario.getName() == null || usuario.getUsername() == null || usuario.getPassword() == null) {
                request.setAttribute("error", "Todos los campos son obligatorios");
                request.getRequestDispatcher("/vistas/agregarUsuario.jsp").forward(request, response);
                return;
            }

            if ("agregar".equals(accion)) {
                dao.agregarUsuario(usuario);
                response.sendRedirect("UsuarioServlet?accion=listar&msg=agregado");

            } else if ("actualizar".equals(accion)) {
                usuario.setId(Integer.parseInt(request.getParameter("id")));
                dao.actualizarUsuario(usuario);
                response.sendRedirect("UsuarioServlet?accion=listar&msg=actualizado");
            }
        } catch (Exception e) {
            throw new ServletException("Error en UsuarioServlet (POST): " + e.getMessage(), e);
        }
    }
}
