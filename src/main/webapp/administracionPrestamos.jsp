<%-- 
    Document   : administracionPrestamos
    Created on : 27 nov 2024, 17:14:31
    Author     : benja
--%>

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
                    <th>Número de Equipo</th>
                    <th>Nombre de Usuario</th>
                    <th>RUT</th>
                    <th>Hora de Uso</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <!-- Datos de Préstamos - Ejemplo estático -->
                <tr>
                    <td>001</td>
                    <td>Juan Pérez</td>
                    <td>12345678-9</td>
                    <td>10:00</td>
                    <td><button class="btn-delete" onclick="eliminarPrestamo(this)">Eliminar</button></td>
                </tr>
                <tr>
                    <td>002</td>
                    <td>Maria Gómez</td>
                    <td>98765432-1</td>
                    <td>11:30</td>
                    <td><button class="btn-delete" onclick="eliminarPrestamo(this)">Eliminar</button></td>
                </tr>
                <!-- Fin de datos de ejemplo -->
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
                        <form>
                            <div class="mb-3">
                                <label for="nombre" class="form-label">Nombre Completo</label>
                                <input type="text" class="form-control" id="nombre" placeholder="Ingresa el nombre completo" required>
                            </div>

                            <div class="mb-3">
                                <label for="rut" class="form-label">RUT</label>
                                <input type="text" class="form-control" id="rut" placeholder="Ingresa el RUT" required>
                            </div>

                            <div class="mb-3">
                                <label for="horaEstimada" class="form-label">Hora Estimada de Uso</label>
                                <input type="time" class="form-control" id="horaEstimada" required>
                            </div>

                            <div class="mb-3">
                                <label for="motivo" class="form-label">Motivo del Préstamo</label>
                                <textarea class="form-control" id="motivo" rows="3" placeholder="Describe el motivo del préstamo" required></textarea>
                            </div>

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
        // Función para eliminar préstamo JAVASCRIPT, BORRAR NOMAS
        function eliminarPrestamo(btn) {
            // Confirmar la eliminación
            if (confirm("¿Seguro que deseas eliminar este préstamo?")) {
                // Eliminar la fila de la tabla
                var row = btn.parentNode.parentNode;
                row.parentNode.removeChild(row);
            }
        }
    </script>

</body>

</html>
