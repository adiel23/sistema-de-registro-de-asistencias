<%-- 
    Document   : consultahistorial
    Created on : 3 abr 2025, 9:14:24 a. m.
    Author     : MINEDUCYT
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Consulta de Historial</title>
</head>
<body>
    <h2>Consulta de Historial de Asistencia</h2>
    <form action="historial.jsp" method="get">
        <label for="idUsuario">Ingrese su ID de usuario:</label>
        <input type="number" name="idUsuario" required>
        <button type="submit">Consultar</button>
    </form>
</body>
</html>

