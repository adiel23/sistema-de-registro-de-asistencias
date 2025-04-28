<%@page import="modelos.Permiso"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <script src="https://kit.fontawesome.com/7792cd81ab.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href='<%= request.getContextPath() %>/css/header.css'>
    <link rel="stylesheet" href='<%= request.getContextPath() %>/css/historial-permisos-global.css'>
</head>
<body>
    <jsp:include page="header.jsp" />
    
    <div id="container">
        <form method="get" action="HistorialPermisosGlobalServlet">
            <input type="text" name="docente" placeholder="Buscar por docente">

            <label>Desde:</label>
            <input type="date" name="desde">

            <label>Hasta:</label>
            <input type="date" name="hasta">

            <select name="estado">
                <option value="">Todos</option>
                <option value="pendiente">Pendiente</option>
                <option value="aprobado">Aprobado</option>
                <option value="rechazado">Rechazado</option>
            </select>

            <input type="submit" value="Filtrar">
        </form>
        
        <h2>Historial de Permisos</h2>
    
        <table border='1'>
            <thead>
                <tr>
                    <th>Docente</th>
                    <th>Motivo</th>
                    <th>Estado</th>
                    <th>Fecha solicitud</th>
                    <th>Rango</th>
                </tr>
            </thead>
            <tbody>
            <%
                List<Permiso> permisos = (List<Permiso>) request.getAttribute("permisos");
                if (permisos == null || permisos.isEmpty()) {
            %>
                <tr><td colspan="5">No se encontraron permisos.</td></tr>
            <%
                } else {
                    for (Permiso p : permisos) {
            %>
                <tr>
                    <td><%= p.getNombre() %></td>
                    <td><%= p.getMotivo() %></td>
                    <td><%= p.getEstado() %></td>
                    <td><%= p.getFechaSolicitud() %></td>
                    <td><%= p.getFechaInicio() %> a <%= p.getFechaTermino() %></td>
                </tr>
            <%
                    }
                }
            %>
            </tbody>
        </table>
    </div>
            
    <script src="<%= request.getContextPath()%>/js/home.js"> </script>
    
</body>
</html>
