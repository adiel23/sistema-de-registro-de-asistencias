package contoladores;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "LogoutServlet", urlPatterns = {"/LogoutServlet"})
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener la sesión actual, sin crear una nueva
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate(); // Invalida la sesión
        }

        // Redirige al login (o a la página principal)
        response.sendRedirect(request.getContextPath() + "/vistas/login.jsp");
    }

}
