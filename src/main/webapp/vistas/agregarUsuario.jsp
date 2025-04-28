<!--agregarUsuario.jsp-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Agregar Usuario</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/header.css">
     <link rel="stylesheet" href="<%= request.getContextPath() %>/css/agregarUsuario.css">
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div class="contenedor">
        <h1>Agregar Nuevo Usuario</h1>

        <%
            String contextPath = request.getContextPath();
        %>

        <form action="<%= contextPath %>/UsuarioServlet" method="post">
            <input type="hidden" name="accion" value="agregar" />

            <label>Nombre:</label>
            <input type="text" name="nombre" required>

            <label>Usuario:</label>
            <input type="text" name="usuario" required>

            <label>Contraseña:</label>
            <input type="password" name="contrasenia" required>

            <label>Fecha de Nacimiento:</label>
            <input type="date" name="fechaNacimiento" required>

            <label>Rol:</label>
            <select name="rol" required>
                <option value="admin">Admin</option>
                <option value="docente">Docente</option>
            </select>

            <div class="botones">
                <input type="submit" value="Guardar">
                <a class="cancelar" href="<%= contextPath %>/UsuarioServlet?accion=listar">Cancelar</a>
            </div>
        </form>
    </div>
</body>
</html>

