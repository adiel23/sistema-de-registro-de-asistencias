package controladores;

import jakarta.servlet.RequestDispatcher;
import modelos.AsistenciaService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import modelos.Asistencia;

@WebServlet(name = "AsistenciaServlet", urlPatterns = {"/AsistenciaServlet"})
public class AsistenciaServlet extends HttpServlet {

    private AsistenciaService asistenciaService;

    @Override
    public void init() throws ServletException {
        asistenciaService = new AsistenciaService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("historialPersonal".equals(action)) {
            try {
                Long idUsuario = Long.parseLong(request.getParameter("id_usuario"));
                List<Asistencia> asistencias = asistenciaService.obtenerHistorialPorUsuario(idUsuario);
                request.setAttribute("asistencias", asistencias);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/historialPersonal.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e) {
                response.sendRedirect("vistas/home.jsp");
            }

        } else if ("historialGlobal".equals(action)) {
            List<Asistencia> asistencias = asistenciaService.obtenerHistorialGlobal();
            request.setAttribute("asistencias", asistencias);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/historialGlobal.jsp");
            dispatcher.forward(request, response);

        } else if ("solicitarPermiso".equals(action)) {
            response.sendRedirect("vistas/solicitar-permiso.jsp");

        } else {
            response.sendRedirect("vistas/home.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("aprobarPermiso".equals(action)) {
            response.sendRedirect("AsistenciaServlet?action=historialGlobal");
        }
    }
}
