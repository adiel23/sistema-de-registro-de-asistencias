<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelos.Usuario" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <title>Editar Usuario</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/header.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/editarUsuario.css">
</head>
<body>

    <jsp:include page="header.jsp"/>

    <div class="contenedor">
        <h1>Editar Usuario</h1>

        <% 
            Usuario usuario = (Usuario) request.getAttribute("usuario"); // Obtenemos el usuario desde el request
            if (usuario == null) { 
                // Si no hay usuario, mostramos un mensaje de error.
        %>
            <p>Error: No se encontró el usuario para editar.</p>
        <% 
            } else { // Si el usuario existe, mostramos el formulario
        %>

            <form action="<%= request.getContextPath() %>/UsuarioServlet" method="post">
                <input type="hidden" name="accion" value="actualizar" />
                <input type="hidden" name="id" value="<%= usuario.getId() %>" />

                <label>Nombre:</label>
                <input type="text" name="nombre" value="<%= usuario.getName() %>" required>

                <label>Usuario:</label>
                <input type="text" name="usuario" value="<%= usuario.getUsername() %>" required>

                <label>Contraseña:</label>
                <input type="password" name="contrasenia" value="<%= usuario.getPassword() %>" required>

                <label>Fecha de Nacimiento:</label>
                <input type="date" name="fechaNacimiento" value="<%= usuario.getBirthDate() %>" required>

                <label>Rol:</label>
                <select name="rol" required>
                    <option value="admin" <%= "admin".equals(usuario.getRol()) ? "selected" : "" %>>Admin</option>
                    <option value="docente" <%= "docente".equals(usuario.getRol()) ? "selected" : "" %>>Docente</option>
                </select>

                <div class="botones">
                    <input type="submit" value="Actualizar">
                    <a class="cancelar" href="<%= request.getContextPath() %>/UsuarioServlet?accion=listar">Cancelar</a>
                </div>
            </form>

        <% 
            }
        %>

    </div>
</body>
</html>
