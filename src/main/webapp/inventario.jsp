<%-- 
    Document   : inventario
    Created on : 4 dic 2024, 12:50:55
    Author     : benja
--%>

<%@page import="com.modelo.Equipo"%>
<%@page import="java.util.ArrayList"%>
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
        <a href="SvHome" class="list-group-item list-group-item-action bg-dark text-white">Inicio</a>
        <a href="SvMostrarPrestamos" class="list-group-item list-group-item-action bg-dark text-white">Administración de Préstamos</a>
        <a href="SvMemoriaInventario" class="list-group-item list-group-item-action bg-dark text-white">Inventario</a>
        <a href="SvLogout" class="list-group-item list-group-item-action bg-dark text-white">Cerrar sesión</a>
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
                <!-- Aquí irá la lista de todos los equipos -->
                <!-- Aquí irá la lista de todos los prestamos -->
                <%
                    // Obtener la lista de préstamos desde el request
                    ArrayList<Equipo> equipos = (ArrayList<Equipo>) session.getAttribute("listaEquipos");
                    if (equipos != null) {
                        for (Equipo equipo : equipos) {
                %>
                <tr>
                    <td><%= equipo.getIdEquipo() %></td>
                    <td><%= equipo.getNombre() %></td>
                    <td><%= equipo.getDescripcion() %></td>
                    <td>
                        <!-- Botón Eliminar -->
                        <a href="#" onclick="eliminarEquipo('<%= equipo.getIdEquipo() %>')" class="btn btn-danger btn-sm">Eliminar</a>                
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
                        
                        <form id="formNuevoEquipo" action="SvRegistrarEquipo" method="post">
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
        function eliminarEquipo(idEquipo) {
            if (confirm("¿Estás seguro de que deseas eliminar este equipo?")) {
                fetch('SvEliminarEquipo', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: 'idEquipo=' + encodeURIComponent(idEquipo)
                })
                .then(response => {
                    if (response.ok) {
                        // Redirigir para actualizar la lista de inventarios
                        window.location.href = 'SvMemoriaInventario';
                    } else {
                        alert('Error al eliminar el equipo.');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('Error al eliminar el equipo.');
                });
            }
        }
    </script>

    
</body>
</html>
