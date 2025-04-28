--editarUsuario.jsp--

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Usuario</title>
</head>
<body>
    <h1>Editar Usuario</h1>
    <form action="UsuarioServlet" method="post">
        <input type="hidden" name="accion" value="actualizar" />
        <input type="hidden" name="id" value="${usuario.id}" />

        <label>Nombre:</label><br>
        <input type="text" name="nombre" value="${usuario.nombre}" required><br>

        <label>Usuario:</label><br>
        <input type="text" name="usuario" value="${usuario.usuario}" required><br>

        <label>Contraseña:</label><br>
        <input type="password" name="contrasenia" value="${usuario.contrasenia}" required><br>

        <label>Fecha de Nacimiento:</label><br>
        <input type="date" name="fechaNacimiento" value="${usuario.fechaNacimiento}" required><br>

        <label>Rol:</label><br>
        <select name="rol" required>
            <option value="admin" ${usuario.rol == 'admin' ? 'selected' : ''}>Admin</option>
            <option value="docente" ${usuario.rol == 'docente' ? 'selected' : ''}>Docente</option>
        </select><br><br>

        <input type="submit" value="Actualizar">
        <a href="UsuarioServlet?accion=listar">Cancelar</a>
    </form>
</body>
</html>
