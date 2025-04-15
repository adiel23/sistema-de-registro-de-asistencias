/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

const nav = document.getElementById("nav");

nav.addEventListener("click", (event) => {
    const element = event.target;
    console.log(element)
    if (element.id === "leaves-dropdown-toggle") {
        const dropdown = element.nextElementSibling;
        console.log(dropdown);
        dropdown.classList.toggle("active");
    } else if (element.id === "attendance-history-dropdown-toggle"){
        const dropdown = element.nextElementSibling;
        console.log(dropdown);
        dropdown.classList.toggle("active");
    }
});

// vamos a agregar el fetch 

function marcarEntrada() {
    fetch('/proyecto/MarcarEntradaServlet', {
        method: 'POST'
    })
    .then(response => {
        if (response.ok) {
            alert("Entrada marcada exitosamente ✅");
            location.reload(); // O actualizás solo una parte del DOM si querés
        } else {
            alert("Error al marcar entrada ❌");
        }
    })
    .catch(error => {
        console.error("Error:", error);
        alert("Algo salió mal");
    });
}

function marcarSalida() {
    fetch('/proyecto/MarcarSalidaServlet', {
        method: 'POST'
    })
    .then(response => {
        if (response.ok) {
            alert("Salida marcada exitosamente ✅");
            location.reload(); // O actualizás solo una parte del DOM si querés
        } else {
            alert("Error al marcar salida ❌");
        }
    })
    .catch(error => {
        console.error("Error:", error);
        alert("Algo salió mal");
    });
}



