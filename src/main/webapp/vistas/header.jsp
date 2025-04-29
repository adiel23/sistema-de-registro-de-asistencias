<header>
    <div id="logo-container">
        <h2>INSAM</h2>
    </div>

    <nav id='nav'>
        <a href="vistas/home.jsp" class="nav-link">Inicio</a> <!-- corregido -->

        <div id="leaves-dropdown" class="dropdown">
            <p id='leaves-dropdown-toggle' class="dropdown-toggle">Permisos</p>

            <div id="leaves-dropdown-menu" class='dropdown-menu'>
                <% 
                    String rol = (String) session.getAttribute("rol");

                    if ("admin".equals(rol)) { %>
                        <a href="AprobarPermisosServlet" class="dropdown-link">Aprobar permisos</a> <!-- correcto -->
                        <br>
                    <% }
                %>

                <a href="vistas/solicitar-permiso.jsp" class='dropdown-link'>Solicitar permiso</a> <!-- corregido -->
                <br>

                <a href="VerHistorialPermisosServlet" class='dropdown-link'>Ver historial</a> <!-- correcto -->
            </div>
        </div>

        <% if ("admin".equals(rol)) { %>
            <a href="ManageUsersServlet" class="nav-link">Gestionar Usuarios</a> <!-- correcto -->
        <% } %>

        <% if ("admin".equals(rol)) { %>
            <div id="attendance-history-dropdown" class="dropdown">
                <p id="attendance-history-dropdown-toggle" class="dropdown-toggle">Historial de Asistencia</p>

                <div id="attendance-history-dropdown-menu" class='dropdown-menu'>
                    <a href="../AsistenciaServlet?action=historialPersonal&id_usuario=1" class="dropdown-link">Personal</a> <!-- correcto -->
                    <br>
                    <a href="../AsistenciaServlet?action=historialGlobal" classw='dropdown-link'>Usuarios</a> <!-- correcto -->
                    <br>
                </div>
            </div>
        <% } else { %>
            <a href="../AsistenciaServlet?action=historialPersonal&id_usuario=1" class="nav-link">Historial de Asistencia</a> <!-- correcto -->
        <% } %>
    </nav>
</header>
