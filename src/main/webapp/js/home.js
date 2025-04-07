/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

const dropdownTogglePermisos = document.getElementById("dropdown-toggle-permisos");

const dropdownMenuPermisos = document.getElementById("dropdown-menu-permisos");

dropdownTogglePermisos.addEventListener("click", () => {
    dropdownMenuPermisos.classList.toggle("active");
});

