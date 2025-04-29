<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Historial de Asistencia Personal</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/header.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/consultar-historial-personal.css">
</head>
<body>
    
<jsp:include page="header.jsp"/>

<h2>Historial de Asistencia Personal</h2>

<!-- Tabla de Asistencias -->
<table border="1" cellpadding="10">
    <thead>
        <tr>
            <th>Fecha</th>
            <th>Hora Entrada</th>
            <th>Hora Salida</th>
        </tr>
    </thead>
    <tbody>
        <%
            List<modelos.Asistencia> asistencias = (List<modelos.Asistencia>) request.getAttribute("asistencias");
            if (asistencias != null) {
                for (modelos.Asistencia asistencia : asistencias) {
        %>
            <tr>
                <td><%= asistencia.getFecha() %></td>
                <td><%= asistencia.getHoraEntrada() %></td>
                <td><%= asistencia.getHoraSalida() %></td>
            </tr>
        <%
                }
            }
        %>
    </tbody>
</table>

</body>
</html>
