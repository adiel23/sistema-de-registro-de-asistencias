<%-- 
    Document   : historial
    Created on : 29 mar 2025, 8:23:11 p. m.
    Author     : MINEDUCYT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.List, modelos.Asistencia, java.text.SimpleDateFormat" %>

<%
    // Obtener la lista de asistencias desde el request
    List<Asistencia> historial = (List<Asistencia>) request.getAttribute("historial");
    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd"); // Para formatear la fecha
%>

<html>
<head>
    <title>Historial de Asistencia</title>
</head>
<body>
    <h2>Historial de Asistencia</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Fecha</th>
            <th>Hora Entrada</th>
            <th>Hora Salida</th>
        </tr>
        
        <% if (historial != null && !historial.isEmpty()) { %>
            <% for (Asistencia a : historial) { %>
                <tr>
                    <td><%= a.getId() %></td>
                    <td><%= (a.getFecha() != null) ? formatoFecha.format(a.getFecha()) : "Sin fecha" %></td>
                    <td><%= (a.getHoraEntrada() != null) ? a.getHoraEntrada() : "Sin hora" %></td>
                    <td><%= (a.getHoraSalida() != null) ? a.getHoraSalida() : "Pendiente" %></td>
                </tr>
            <% } %>
        <% } else { %>
            <tr>
                <td colspan="4">No hay registros de asistencia.</td>
            </tr>
        <% } %>

    </table>
</body>
</html>
