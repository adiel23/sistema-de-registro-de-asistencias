--Usuarios.jsp--

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Usuarios</title>
</head>
<body>
    <h1>Usuarios Registrados</h1>

    <a href="agregarUsuario.jsp">➕ Agregar Usuario</a>
    <table border="1" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Usuario</th>
            <th>Fecha Nacimiento</th>
            <th>Rol</th>
            <th>Acciones</th>
        </tr>
        <c:forEach var="u" items="${usuarios}">
            <tr>
                <td>${u.id}</td>
                <td>${u.nombre}</td>
                <td>${u.usuario}</td>
                <td>${u.fechaNacimiento}</td>
                <td>${u.rol}</td>
                <td>
                    <a href="UsuarioServlet?accion=editar&id=${u.id}">✏️ Editar</a> |
                    <a href="UsuarioServlet?accion=eliminar&id=${u.id}" onclick="return confirm('¿Seguro que deseas eliminar este usuario?');">🗑️ Eliminar</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
