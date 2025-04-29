<%@page import="java.util.List"%>
<%@page import="modelos.Usuario"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Usuarios</title>
    <script src="https://kit.fontawesome.com/7792cd81ab.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/header.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/usuarios.css">

</head>
<body>
    
    <jsp:include page="header.jsp"/>
        
    <div class="contenedor">
        <h1>Usuarios Registrados</h1>

        <% 
            String msg = request.getParameter("msg");
            if ("agregado".equals(msg)) {
        %>
            <p class="mensaje exito">✅ Usuario agregado exitosamente.</p>
        <% 
            } else if ("actualizado".equals(msg)) {
        %>
            <p class="mensaje exito">✅ Usuario actualizado exitosamente.</p>
        <% 
            } else if ("eliminado".equals(msg)) {
        %>
            <p class="mensaje error">🗑 Usuario eliminado correctamente.</p>
        <% 
            }
        %>

        <a href="<%= request.getContextPath() %>/vistas/agregarUsuario.jsp" class="enlaces-usuario">➕ Agregar Usuario</a>
        
        <table>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Usuario</th>
                <th>Fecha Nacimiento</th>
                <th>Rol</th>
                <th>Acciones</th>
            </tr>
            
            <% 
                List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
                for (Usuario u : usuarios) {
            %>
                <tr>
                    <td><%= u.getId() %></td>
                    <td><%= u.getName() %></td>
                    <td><%= u.getUsername() %></td>
                    <td><%= u.getBirthDate() %></td>
                    <td><%= u.getRol() %></td>
                    <td>
                        <a href="<%= request.getContextPath() %>/UsuarioServlet?accion=editar&id=<%= u.getId() %>" class="enlaces-usuario" >✏️ Editar</a>
                        <a href="<%= request.getContextPath() %>/UsuarioServlet?accion=eliminar&id=<%= u.getId() %>" onclick="return confirm('¿Seguro que deseas eliminar este usuario?');" class="enlaces-usuario">🗑️ Eliminar</a>
                    </td>
                </tr>
            <% 
                }
            %>
        </table>
    </div>
</body>
</html>
