<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <link rel="stylesheet" href="../css/home.css">
</head>
<body>
    
    <header>
        <div id="logo-container">
            <h2>INSAM</h2>
        </div>

        <nav>
            <a href="home.jsp" class="nav-link">Inicio</a>
            <a href="historial.jsp" class="nav-link">Historial</a>
            <div id="dropdown-permisos">
                <p id='dropdown-toggle-permisos'>Permisos</p>
                <div id="dropdown-menu-permisos">
                    <a href=''class='permisos-dropdown-link'>Solicitar permiso</a>
                    <br>
                    <a href=''class='permisos-dropdown-link'>Ver historial</a>
                </div>
            </div>
        </nav>
        
    </header>
    
    <button id="marcar-entrada">Marcar entrada</button>
    <button id='marcar-salida'>Marcar salida</button>
    
    <script src="../js/home.js"> </script>

</body>
</html>
