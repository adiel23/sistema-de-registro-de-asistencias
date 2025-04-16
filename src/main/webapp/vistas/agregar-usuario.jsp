--agregarUsuario.jsp--

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Agregar Usuario</title>
</head>
<body>
    <h1>Agregar Nuevo Usuario</h1>
    <form action="UsuarioServlet" method="post">
        <input type="hidden" name="accion" value="agregar" />
        <label>Nombre:</label><br>
        <input type="text" name="nombre" required><br>
        
        

        <label>Usuario:</label><br>
        <input type="text" name="usuario" required><br>

        <label>Contraseña:</label><br>
        <input type="password" name="contrasenia" required><br>

        <label>Fecha de Nacimiento:</label><br>
        <input type="date" name="fechaNacimiento" required><br>

        <label>Rol:</label><br>
        <select name="rol" required>
            <option value="admin">Admin</option>
            <option value="docente">Docente</option>
        </select><br><br>

        <input type="submit" value="Guardar">
        <a href="UsuarioServlet?accion=listar">Cancelar</a>
    </form>
</body>
</html>

