<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, modelos.Permiso" %>
<html>
<head>
    <title>Permisos</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/header.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/aprobar-permisos.css">
    <script src="https://kit.fontawesome.com/7792cd81ab.js" crossorigin="anonymous"></script>
</head>
<body>
    <jsp:include page="header.jsp" />
    <h2 id="title">Lista de Permisos</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
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
                            <button class="aprobar-permiso-btn" id="aprobar-<%= p.getId() %>" onclick="actualizarEstado(<%= p.getId() %>, 'aprobado')" <%= p.getEstado().equals("pendiente") ? "" : "disabled" %> >Aprobar</button>
                            <button class="rechazar-permiso-btn" id="rechazar-<%= p.getId() %>" onclick="actualizarEstado(<%= p.getId() %>, 'rechazado')" <%= p.getEstado().equals("pendiente") ? "" : "disabled" %>>Rechazar</button>
                        </td>
                    </tr>
                <% }
            } %>
            
    </table>
            
    <div id="view-history-container">
        <i class="fa-solid fa-eye" id="view-history-button"></i>
    </div>
    
    <script src="<%= request.getContextPath()%>/js/home.js"> </script>
    <script src='<%= request.getContextPath()%>/js/aprobar-permisos.js'></script>
</body>
</html>

