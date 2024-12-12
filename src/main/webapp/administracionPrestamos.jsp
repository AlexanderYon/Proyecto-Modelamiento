<%-- 
    Document   : administracionPrestamos
    Created on : 27 nov 2024, 17:14:31
    Author     : benja
--%>

<%@page import="com.modelo.Prestamo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin. Prestamos</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap" rel="stylesheet">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: "Poppins", sans-serif;
        }

        body {
            display: flex;
            align-items: flex-start; /* Alineación del contenido desde la parte superior */
            justify-content: center;
            height: 100vh;
            padding: 15px;
            background: #f4f4f4;
            overflow: hidden;
        }

        .d-flex {
            display: flex;
        }

        #sidebar-wrapper {
            height: 100vh;
            width: 250px;
            background-color: #2c3e50;
            position: fixed;
            left: 0;
            top: 0;
            padding-top: 20px;
        }

        #sidebar-wrapper .sidebar-heading {
            color: #fff;
            text-align: center;
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 30px;
        }

        #sidebar-wrapper .list-group-item {
            background-color: #2c3e50;
            color: white;
            border: none;
            font-size: 18px;
            padding: 15px 20px;
        }

        #sidebar-wrapper .list-group-item:hover {
            background-color: #000000;
        }

        #sidebar-wrapper .list-group-item a {
            color: white;
            text-decoration: none;
        }

        #page-content-wrapper {
            margin-left: 250px;
            width: calc(100% - 250px);
            padding: 15px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        h1 {
            color: #000000;
            font-weight: 600;
            font-size: 3rem;
            text-align: center;
            margin-bottom: 30px;
        }

        .table {
            width: 100%;
            margin-top: 20px;
        }

        .table th,
        .table td {
            text-align: center;
            padding: 12px;
        }

        .table th {
            background-color: #000000;
            color: white;
        }

        .table tbody tr:nth-child(odd) {
            background-color: #f9f9f9;
        }

        .btn-delete {
            background-color: red;
            color: white;
            border: none;
            padding: 5px 10px;
            cursor: pointer;
        }

        .btn-delete:hover {
            background-color: #e60000;
        }

        .modal-content {
            background-color: #f6f1ff;
            color: #2c3e50;
        }

        .modal-header {
            background-color: #000000;
            color: white;
        }
    </style>
</head>

<body>
    <!-- Sidebar -->
<div class="bg-dark border-right" id="sidebar-wrapper" style="min-width: 250px;">
    <div class="sidebar-heading text-white">Menú</div>
    <div class="list-group list-group-flush">
        <a href="home.jsp" class="list-group-item list-group-item-action bg-dark text-white">Inicio</a>
        <a href="administracionPrestamos.jsp" class="list-group-item list-group-item-action bg-dark text-white">Administración de Préstamos</a>
        <a href="inventario.jsp" class="list-group-item list-group-item-action bg-dark text-white">Inventario</a>
        <div class="list-group-item bg-dark text-white">
            <a class="text-white text-decoration-none" data-bs-toggle="collapse" href="#reportesSubmenu" role="button" aria-expanded="false" aria-controls="reportesSubmenu">
                Informes
            </a>
            <div class="collapse" id="reportesSubmenu">
                <a href="#" class="list-group-item list-group-item-action bg-secondary text-white">Reporte 1</a>
                <a href="#" class="list-group-item list-group-item-action bg-secondary text-white">Reporte 2</a>
                <a href="#" class="list-group-item list-group-item-action bg-secondary text-white">Reporte 3</a>
            </div>
        </div>
        <a href="#" class="list-group-item list-group-item-action bg-dark text-white">Cerrar sesión</a>
    </div>
</div>

    <div id="page-content-wrapper">
        <h1>Administración de Prestamos</h1>

        <!-- Tabla de Préstamos -->
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Id Equipo</th>
                    <th>RUT</th>
                    <th>Nombre Usuario</th>
                    <th>Hora Límite</th>
                    <th>Motivo</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <!-- Aquí irá la lista de todos los prestamos -->
                <%
                    // Obtener la lista de préstamos desde el request
                    ArrayList<Prestamo> prestamos = (ArrayList<Prestamo>) request.getAttribute("listaPrestamos");
                    if (prestamos != null) {
                        for (Prestamo prestamo : prestamos) {
                %>
                <tr>
                    <td><%= prestamo.getEquipo().getIdEquipo() %></td>
                    <td><%= prestamo.getUsuario().getRut() %></td>
                    <td><%= prestamo.getUsuario().getNombre() %></td>
                    <td><%= prestamo.getHoraDevolucion() %></td>
                    <td><%= prestamo.getMotivo() %></td>
                    <td>
                        <!-- Botón Eliminar -->
                        <a href="SvEliminarPrestamo?rut=<%= prestamo.getUsuario().getRut() %>&idEquipo=<%= prestamo.getEquipo().getIdEquipo() %>"
                           class="btn btn-danger btn-sm">Eliminar</a>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="6" class="text-center">No hay préstamos registrados.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <!-- Botón para abrir el Modal -->
        <button class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#nuevoPrestamoModal">Añadir Nuevo Préstamo</button>

        <!-- Modal de Nuevo Préstamo -->
        <div class="modal fade" id="nuevoPrestamoModal" tabindex="-1" aria-labelledby="nuevoPrestamoModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="nuevoPrestamoModalLabel">Nuevo Préstamo</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        
                        <!-- Formulario para registrar un nuevo prestamo -->
                        
                        <form id="formNuevoPrestamo" onsubmit="agregarPrestamo(event)" action="SvRegistrarPrestamo" method="post">
                            
                            <!-- RUT Usuario -->
                            <div class="mb-3">
                                <label for="rut" class="form-label">RUT</label>
                                <input type="text" class="form-control" id="rut" name="rutUsuario" placeholder="Ingresa el RUT" required>
                            </div>
                            
                            <!-- Nombre Usuario -->
                            <div class="mb-3">
                                <label for="nombre" class="form-label">Nombre Completo</label>
                                <input type="text" class="form-control" id="nombre" name="nombreUsuario" placeholder="Ingresa el nombre completo" required>
                            </div>
                            
                            <!-- Id Equipo -->
                            <div class="mb-3">
                                <label for="id" class="form-label">Id Equipo</label>
                                <input type="text" class="form-control" id="id" name="idEquipo" placeholder="Ingresa el Id del equipo" required>
                            </div>

                            <!-- Hora Estimada -->
                            <div class="mb-3">
                                <label for="horaEstimada" class="form-label">Hora Estimada de Uso</label>
                                <input type="time" class="form-control" id="horaEstimada" name="horaEstimada" required>
                            </div>

                            <!-- Motivo -->
                            <div class="mb-3">
                                <label for="motivo" class="form-label">Motivo del Préstamo</label>
                                <textarea class="form-control" id="motivo" rows="3" name="motivo" placeholder="Describe el motivo del préstamo" required></textarea>
                            </div>
                            
                            <!-- Botón de confirmación -->
                            <div class="d-grid gap-2">
                                <button type="submit" class="btn btn-secondary">Registrar Préstamo</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function agregarPrestamo(event){
            event.preventDefault(); // Prevenir recarga de la página

            const form = document.getElementById("formNuevoPrestamo");

            // Crear un objeto FormData para capturar los datos del formulario
            const formData = new FormData(form);
            
            // Enviar datos al servlet mediante fetch
            fetch("SvRegistrarPrestamo", {
                method: "POST",
                body: formData,
            })
            .then(response => response.json()) // Suponemos que el servlet devuelve JSON
            .then(data => {
                
                if (data.success) {
                    // Crear una nueva fila en la tabla con los datos recibidos
                    const tableBody = document.querySelector("table tbody");
                    
                    const newRow = document.createElement("tr");
                    
                    const cellId = document.createElement("td");
                    cellId.textContent = data.idEquipo;
                    newRow.appendChild(cellId);

                    // Asignar los datos de la respuesta a las celdas
                    const cellRut = document.createElement("td");
                    cellRut.textContent = data.rutUsuario;
                    newRow.appendChild(cellRut);

                    const cellNombre = document.createElement("td");
                    cellNombre.textContent = data.nombreUsuario;
                    newRow.appendChild(cellNombre);

                    const cellHoraEstimada = document.createElement("td");
                    cellHoraEstimada.textContent = data.horaEstimada;
                    newRow.appendChild(cellHoraEstimada);
                    
                    const cellMotivo = document.createElement("td");
                    cellMotivo.textContent = data.motivo;
                    newRow.appendChild(cellMotivo);


                    // < --------- Botón Eliminar --------> 
                    
                    
                    // Crear la celda para el botón eliminar
                    const cellActions = document.createElement("td");

                    // Crear el formulario
                    const form = document.createElement("form");
                    form.method = "POST";
                    form.action = "SvEliminarPrestamo";

                    // Crear un campo oculto para enviar el ID del equipo
                    const hiddenInput = document.createElement("input");
                    hiddenInput.type = "hidden";
                    hiddenInput.name = "id";
                    hiddenInput.value = data.id; // Usamos el ID del equipo recibido
                    form.appendChild(hiddenInput);

                    // Crear el botón de enviar
                    const deleteButton = document.createElement("button");
                    deleteButton.type = "submit"; // Botón de tipo submit
                    deleteButton.classList.add("btn", "btn-delete");
                    deleteButton.textContent = "Eliminar";
                    
                    // Agregar confirmación al botón
                    deleteButton.onclick = function () {
                        
                        // Confirmar la eliminación
                        if (confirm("¿Seguro que deseas eliminar este préstamo?")) {
                            // Eliminar la fila de la tabla
                            var row = this.parentNode.parentNode.parentNode;
                            row.parentNode.removeChild(row);
                        }
                    };
           
                    // Añadir el formulario a la celda
                    form.appendChild(deleteButton);
                    cellActions.appendChild(form);
                    newRow.appendChild(cellActions);

                    tableBody.appendChild(newRow);

                    // Limpiar el formulario y cerrar el modal
                    form.reset();
                    const modal = bootstrap.Modal.getInstance(document.getElementById("nuevoPrestamoModal"));
                    modal.hide();
                } else {
                    alert("Error al añadir el préstamo: " + data.message);
                }
            })
            .catch(error => {
                console.error("Error:", error);
                alert("Ocurrió un error al procesar la solicitud.");
            });
        }
        
    </script>

</body>

</html>
