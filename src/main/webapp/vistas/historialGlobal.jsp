<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Historial Global de Asistencias</title>
</head>
<body>

<h2>Historial Global de Asistencias</h2>

<!-- Tabla de Asistencias -->
<table border="1" cellpadding="10">
    <thead>
        <tr>
            <th>Nombre</th>
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
                <td><%= asistencia.getNombreUsuario() %></td>
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
