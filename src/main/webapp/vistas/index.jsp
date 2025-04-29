<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registro de Asistencias</title>
    <link rel="stylesheet" href="../css/styles.css"> <!-- Correcto para salir de vistas -->
</head>
<body>

    <jsp:include page="header.jsp" />

    <h2>Registro de Asistencias</h2>

    <ul>
        <!-- Historial Personal -->
        <li>
            <a href="${pageContext.request.contextPath}/../AsistenciaServlet?action=historialPersonal&id_usuario=1">Historial Personal</a>
        </li>

        <!-- Historial Global -->
        <li>
            <a href="../AsistenciaServlet?action=historialPersonal&id_usuario=1">Historial Personal</a>
        </li>
    </ul>

</body>
</html>

