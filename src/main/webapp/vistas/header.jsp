<header>
    <div id="logo-container">
        <h2>INSAM</h2>
    </div>

    <nav id='nav'>
            <a href="<%= request.getContextPath()%>/vistas/home.jsp" class="nav-link">Inicio</a>
            
            <div id="leaves-dropdown" class="dropdown">
                <p id='leaves-dropdown-toggle' class="dropdown-toggle">Permisos</p>
                
                <div id="leaves-dropdown-menu" class='dropdown-menu'>
                    <% 
                        String rol = (String) session.getAttribute("rol");

                        System.out.println("este es mi rol" + rol);

                        if ("admin".equals(rol)) { %>
                            <a href="<%= request.getContextPath()%>/MostrarPermisosPendientesServlet" class="dropdown-link">Aprobar permisos</a>
                            <br>
                        <% }
                    %>

                    <a href='<%= request.getContextPath()%>/vistas/solicitar-permiso.jsp' class='dropdown-link'>Solicitar permiso</a>
                    <br>
                    
                    <a href='<%= request.getContextPath()%>/HistorialPermisosPersonalServlet' class='dropdown-link'>Mi historial</a>
                </div>
            </div>
                    
            <% if ("admin".equals(rol)) { %>
                <a href="" class="nav-link">Manage Users</a>
            <% } %>
            
            <% if ("admin".equals(rol)) { %>
                <div id="attedance-history-dropdown" class="dropdown">
                    <p id="attendance-history-dropdown-toggle" class="dropdown-toggle">Historial de Asistencia</p>

                    <div id="attendance-history-dropdown-menu" class='dropdown-menu'>
                        <a href="" class="dropdown-link">Personal</a> <br>
                        <a href="" class='dropdown-link'>Usuarios</a> <br>
                    </div>
                </div>
            <% } else { %>
                    <a href="" class="nav-link">Historial de Asistencia</a>
            <% } %>
            
            <a class="nav-link" href="<%= request.getContextPath()%>/LogoutServlet"><i class="fa-solid fa-right-from-bracket"></i></a>
            
        </nav>
        
</header>