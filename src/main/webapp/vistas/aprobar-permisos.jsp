<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, modelos.Permiso" %>
<html>
<head>
    <title>Permisos</title>
    <link rel="stylesheet" href="/proyecto/css/aprobar-permisos.css">
    <link rel="stylesheet" href="/proyecto/css/header.css">
</head>
<body>
    <jsp:include page="header.jsp" />
    <h2 id="title">Lista de Permisos</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>ID Usuario</th>
            <th>Fecha Solicitud</th>
            <th>Motivo</th>
            <th>Fecha Inicio</th>
            <th>Fecha Término</th>
            <th>Estado</th>
            <th>Acciones</th>
        </tr>
        <%
            List<Permiso> permisos = (List<Permiso>) request.getAttribute("permisos");
            
            if (permisos == null || permisos.isEmpty()){ %>
                <tr>
                    <td colspan="8">No hay permisos pendientes.</td>
                </tr>
            <% } else {
                for (Permiso p : permisos) { %>
                    <tr id="permiso-<%= p.getId()%>">

                        <td><%= p.getId() %></td>
                        <td><%= p.getNombre() %></td>
                        <td><%= p.getFechaSolicitud() %></td>
                        <td><%= p.getMotivo() %></td>
                        <td><%= p.getFechaInicio() %></td>
                        <td><%= p.getFechaTermino() %></td>
                        <td id="estado-<%= p.getId() %>"><%= p.getEstado() %></td>
                        <td>
                            <button id="aprobar-<%= p.getId() %>" onclick="actualizarEstado(<%= p.getId() %>, 'aprobado')" <%= p.getEstado().equals("pendiente") ? "" : "disabled" %>>Aprobar</button>
                            <button id="rechazar-<%= p.getId() %>" onclick="actualizarEstado(<%= p.getId() %>, 'rechazado')" <%= p.getEstado().equals("pendiente") ? "" : "disabled" %>>Rechazar</button>
                        </td>
                    </tr>
                <% }
            } %>
            
    </table>
    
    <script src="/proyecto/js/home.js"> </script>
    <script>
        function actualizarEstado(id, estado) {
            fetch('ActualizarPermisoServlet', {
              method: 'POST',
              headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
              body: 'id=' + id + '&estado=' + estado
            })
            .then(response => response.text())
            .then(data => {
              if (data === 'ok') {
                // Ocultar o eliminar la fila de la tabla
                const fila = document.getElementById('permiso-' + id);
                fila.remove(); // O podrías usar fila.style.display = 'none';
              } else {
                alert('Error al actualizar el estado');
              }
            })
            .catch(err => console.error('Error en la solicitud AJAX:', err));
        }
    </script>
</body>
</html>

