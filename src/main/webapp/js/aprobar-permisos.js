/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function actualizarEstado(id, estado) {
            fetch('ActualizarPermisoServlet', {
              method: 'POST',
              headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
              body: 'id=' + id + '&estado=' + estado
            })
            .then(response => response.text())
            .then(data => {
              if (data === 'ok') {
                // Ocultar o eliminar la fila de la tabla
                const fila = document.getElementById('permiso-' + id);
                fila.remove(); // O podrías usar fila.style.display = 'none';
              } else {
                alert('Error al actualizar el estado');
              }
            })
            .catch(err => console.error('Error en la solicitud AJAX:', err));
        }

const viewHistoryBtn = document.getElementById("view-history-button");

viewHistoryBtn.addEventListener("click", () => {
    window.location.href = "/proyecto/HistorialPermisosGlobalServlet";
});

