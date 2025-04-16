<%@page import="java.sql.Time"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="modelos.Conexion"%>
<%@ page import="java.sql.Connection, java.sql.PreparedStatement, java.sql.ResultSet, java.text.SimpleDateFormat, java.util.Date" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>
    <link rel="stylesheet" href="../css/home.css">
    <link rel="stylesheet" href="../css/header.css">
</head>
<body>
    
    <jsp:include page="header.jsp"/>
    
    <%
        int userId = (Integer) session.getAttribute("id");
        Date fechaHoy = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaStr = sdf.format(fechaHoy);

        Connection conn = Conexion.getConnection();

        // 1. Verificar si ya hay registro de asistencia hoy
        String selectQuery = "SELECT * FROM asistencias WHERE id_usuario = ? AND fecha = ?";
        PreparedStatement selectStmt = conn.prepareStatement(selectQuery);
        selectStmt.setInt(1, userId);
        selectStmt.setString(2, fechaStr);
        ResultSet rs = selectStmt.executeQuery();

        if (!rs.next()) {
            // 2. Si no hay registro, lo creamos automáticamente
            String insertQuery = "INSERT INTO asistencias (id_usuario, fecha) VALUES (?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
            insertStmt.setInt(1, userId);
            insertStmt.setString(2, fechaStr);
            insertStmt.executeUpdate();

        // Mostrar botón de marcar entrada
    %>
        <button onclick="marcarEntrada()" id='marcar-entrada'>Marcar Entrada</button>
    <% } else {
        Time horaEntrada = rs.getTime("hora_entrada");
        Time horaSalida = rs.getTime("hora_salida");

        if (horaEntrada == null) { %>
            <button onclick="marcarEntrada()" id="marcar-entrada">Marcar Entrada</button>
    <% } else if (horaSalida == null) { %>
            <button onclick="marcarSalida()" id='marcar-salida'>Marcar Salida</button>
    <% } else { %>
        <p id="message">Ya marcaste entrada y salida hoy.</p>
    <% }
    }
        conn.close();
    %>
    
    <script src="../js/home.js"> </script>

</body>
</html>
