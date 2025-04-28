<%-- 
    Document   : solicitar-permiso
    Created on : 11 abr 2025, 7:32:24 a. m.
    Author     : leidy
--%>
<%@page import="modelos.Conexion"%>
<%@ page import="java.sql.*, java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Solicitar Permiso</title>
    <script src="https://kit.fontawesome.com/7792cd81ab.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/css/header.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/solicitar-permiso.css">

</head>
<body>

    <%
    Integer idUsuario = (Integer) session.getAttribute("id");
    if (idUsuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String mensaje = "";
    String motivo = request.getParameter("motivo");
    String fechaInicio = request.getParameter("fechaInicio");
    String fechaTermino = request.getParameter("fechaTermino");

    if (motivo != null && fechaInicio != null && fechaTermino != null) {
        try {
            Connection conn = Conexion.getConnection();

            String sql = "INSERT INTO permisos (id_usuario, fecha_solicitud, motivo, fecha_inicio, fecha_termino) VALUES (?, NOW(), ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            stmt.setString(2, motivo);
            stmt.setString(3, fechaInicio);
            stmt.setString(4, fechaTermino);

            int resultado = stmt.executeUpdate();
            if (resultado > 0) {
                mensaje = "<div class='mensaje exito'>" +
                          "✅ Solicitud enviada correctamente.<br>" +
                          "<a href='home.jsp' class='boton-inicio'>Ir al Inicio</a>" +
                          "</div>";
            } else {
                mensaje = "<p class='mensaje error'>❌ No se pudo guardar la solicitud.</p>";
            }

            conn.close();
        } catch (Exception e) {
            mensaje = "<p class='mensaje error'>Error: " + e.getMessage() + "</p>";
        }
    }
%>

    <jsp:include page="header.jsp"/>

    <div class="container">
    <div class="form-box">
        <h2>Solicitar Permiso</h2>
        <form method="post">
            <label for="motivo">Motivo:</label>
            <input type="text" id="motivo" name="motivo" required>

            <label for="fechaInicio">Fecha de inicio:</label>
            <input type="date" id="fechaInicio" name="fechaInicio" required>

            <label for="fechaTermino">Fecha de término:</label>
            <input type="date" id="fechaTermino" name="fechaTermino" required>

            <button type="submit">Enviar Solicitud</button>
        </form>
        <%= mensaje %>
    </div>
</div>
    
    <script src="../js/home.js"> </script>

</body>
</html>