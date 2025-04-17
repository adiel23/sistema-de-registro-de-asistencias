<%-- 
    Document   : registroAsistencia
    Created on : 29 mar 2025, 8:22:22 p. m.
    Author     : MINEDUCYT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Registro de Asistencia</title>
</head>
<body>
    <h2>Registrar Asistencia</h2>
    <form action="AsistenciaServlet" method="post">
        <label for="idUsuario">ID Usuario:</label>
        <input type="number" name="idUsuario" required>
        
        <button type="submit" name="accion" value="entrada">Registrar Entrada</button>
        <button type="submit" name="accion" value="salida">Registrar Salida</button>
    </form>
</body>
</html>
