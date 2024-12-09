<%-- 
    Document   : inventario
    Created on : 4 dic 2024, 12:50:55
    Author     : benja
--%>

<%-- 
    Document   : gestionEquipos
    Created on : 4 dic 2024, 15:00:00
    Author     : benja
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inventario</title>
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
        <h1>Inventario de Equipos</h1>

        <!-- Tabla de Equipos -->
        <table class="table table-bordered w-100">
            <thead>
                <tr>
                    <th>ID de Equipo</th>
                    <th>Nombre</th>
                    <th>Descripción</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <!-- Datos de ejemplo -->
                <tr>
                    <td>1</td>
                    <td>Notebook HP</td>
                    <td>16GB RAM, SSD 512GB</td>
                    <td>
                        <button class="btn btn-delete" onclick="eliminarEquipo(this)">Eliminar</button>
                    </td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Mouse Logitech</td>
                    <td>Inalámbrico</td>
                    <td>
                        <button class="btn btn-delete" onclick="eliminarEquipo(this)">Eliminar</button>
                    </td>
                </tr>
                <!-- Fin de datos de ejemplo -->
            </tbody>
        </table>

        <!-- Botón para añadir equipo -->
        <button class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#nuevoEquipoModal">Añadir Nuevo Equipo</button>

        <!-- Modal para añadir equipo -->
        <div class="modal fade" id="nuevoEquipoModal" tabindex="-1" aria-labelledby="nuevoEquipoModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="nuevoEquipoModalLabel">Añadir Nuevo Equipo</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        
                        <!-- Formulario para registrar nuevo equipo -->
                        
                        <form id="formNuevoEquipo" onsubmit="agregarEquipo(event)" action="SvRegistrarEquipo" method="post">
                            <div class="mb-3">
                                <label for="idEquipo" class="form-label">ID de Equipo</label>
                                <input type="text" class="form-control" id="idEquipo" name="id" placeholder="Ingresa el ID" required>
                            </div>
                            <div class="mb-3">
                                <label for="nombreEquipo" class="form-label">Nombre</label>
                                <input type="text" class="form-control" id="nombreEquipo" name="nombre" placeholder="Ingresa el nombre" required>
                            </div>
                            <div class="mb-3">
                                <label for="descripcion" class="form-label">Descripción</label>
                                <textarea class="form-control" id="descripcion" name="descripcion" rows="3" placeholder="Ingresa la descripción" required></textarea>
                            </div>
                            <button type="submit" class="btn btn-secondary">Añadir</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        // Función para eliminar equipo
        function eliminarEquipo(btn) {
            if (confirm("¿Estás seguro de que deseas eliminar este equipo?")) {
                // Obtener la fila correspondiente al botón presionado
                const row = btn.parentNode.parentNode;

                // Obtener el ID del equipo desde la fila
                const equipoId = row.querySelector("td:first-child").textContent; // Suponemos que el ID está en la primera columna

                console.log("Equipo id: " + equipoId);

                // Crear un objeto JSON con el ID del equipo
                const data = JSON.stringify({ id: equipoId });

                // Enviar la solicitud POST al servlet
                fetch('/SvEliminarEquipo', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: data
                })
                .then(response => response.json())
                .then(result => {
                    // Verificar si la eliminación fue exitosa
                    if (result.success) {
                        // Si la eliminación fue exitosa, eliminar la fila de la tabla
                        row.parentNode.removeChild(row);
                    } else {
                        alert("Hubo un problema al eliminar el equipo.");
                    }
                })
                .catch(error => {
                    console.error('Error al eliminar el equipo:', error);
                    alert("Hubo un error al comunicarse con el servidor.");
                });
            }
}

    </script>
    <script>
        // función para agregar equipo a la lista gráfica
        function agregarEquipo(event) {
            event.preventDefault(); // Prevenir recarga de la página

            const form = document.getElementById("formNuevoEquipo");

            // Crear un objeto FormData para capturar los datos del formulario
            const formData = new FormData(form);

            // Enviar datos al servlet mediante fetch
            fetch("SvRegistrarEquipo", {
                method: "POST",
                body: formData,
            })
            .then(response => response.json()) // Suponemos que el servlet devuelve JSON
            .then(data => {
                // console.log(data) // < ------- DEBUG : ESTO FUNCIONA BIEN
                
                if (data.success) {
                    // Crear una nueva fila en la tabla con los datos recibidos
                    const tableBody = document.querySelector("table tbody");
                    
                    // DEBUG: AQUÍ HAY ALGO RARO
                    // console.log(tableBody)
                    
                    const newRow = document.createElement("tr");
                    
                    // Asignar los datos de la respuesta a las celdas
                    const cellId = document.createElement("td");
                    cellId.textContent = data.id;
                    newRow.appendChild(cellId);

                    const cellName = document.createElement("td");
                    cellName.textContent = data.nombre;
                    newRow.appendChild(cellName);

                    const cellDescription = document.createElement("td");
                    cellDescription.textContent = data.descripcion;
                    newRow.appendChild(cellDescription);

                    // Crear la celda para el botón eliminar
                    const cellActions = document.createElement("td");
                    const deleteButton = document.createElement("button");
                    deleteButton.classList.add("btn", "btn-delete");
                    deleteButton.textContent = "Eliminar";
                    deleteButton.onclick = function() { eliminarEquipo(deleteButton); };
                    cellActions.appendChild(deleteButton);
                    newRow.appendChild(cellActions);

                    tableBody.appendChild(newRow);

                    // Limpiar el formulario y cerrar el modal
                    form.reset();
                    const modal = bootstrap.Modal.getInstance(document.getElementById("nuevoEquipoModal"));
                    modal.hide();
                } else {
                    alert("Error al añadir el equipo: " + data.message);
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
