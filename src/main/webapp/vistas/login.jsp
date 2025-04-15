<%-- 
    Document   : login
    Created on : 24 mar 2025, 10:27:13
    Author     : arthu
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href='../css/login.css'>
</head>
<body>
    <div id="contenedor-logo">
        <a href="https://www.facebook.com/profile.php?id=100064194878365">
            <img src="../imgs/insam-logo.jpeg" alt="" id="logo">
        </a>
        
        <h2>INSAM</h2>
    </div>

    <div id="contenedor">
        <h2 id="titulo-formulario">SISTEMA DE REGISTRO DE ASISTENCIA</h2>

        <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
            <a href="https://www.facebook.com/profile.php?id=100064194878365">
                <img src="../imgs/insam-logo.jpeg" alt="" id="logo">
            </a>
            <input type="text" id="input-usuario" placeholder="usuario" name='usuario'>
            <input type="text" id="input-contraseña" placeholder="contraseña" name='contrasenia'>
            <button id="boton-iniciar-sesion" type="submit" name="login">Iniciar sesion</button>
        </form>
        
    </div>
</body>
</html>
