<%-- 
    Document   : devolverEquipo
    Created on : 27 nov 2024, 14:33:33
    Author     : benja
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Devolver Equipo</title>
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
            align-items: flex-start; 
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
            background-color: #8032fe;
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

        .row {
            display: flex;
            justify-content: center;
            gap: 30px;
        }

        .form-control {
            width: 100%;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            margin-top: 30px;
        }

        th,
        td {
            text-align: center;
            padding: 10px;
            border: 1px solid #ddd;
        }

        th {
            background-color: #8032fe;
            color: #fff;
        }
    </style>
</head>

<body>
    <div id="wrapper" class="d-flex">
        <div class="bg-dark border-right" id="sidebar-wrapper" style="min-width: 250px;">
            <div class="sidebar-heading text-white">Menú</div>
            <div class="list-group list-group-flush">
                <a href="home.jsp" class="list-group-item list-group-item-action bg-dark text-white">Inicio</a>
                <div class="list-group-item bg-dark text-white">
                    <a class="text-white text-decoration-none" data-bs-toggle="collapse" href="#prestamosSubmenu" role="button" aria-expanded="false" aria-controls="prestamosSubmenu">
                        Prestamos de Equipos
                    </a>
                    <div class="collapse" id="prestamosSubmenu">
                        <a href="nuevoPrestamo.jsp" class="list-group-item list-group-item-action bg-secondary text-white">Prestar Equipo</a>
                        <a href="devolverEquipo.jsp" class="list-group-item list-group-item-action bg-secondary text-white">Devolver Equipo</a>
                        <a href="verPrestamos.jsp" class="list-group-item list-group-item-action bg-secondary text-white">Ver Prestamos Actuales</a>
                    </div>
                </div>
                <a href="inventario.jsp" class="list-group-item list-group-item-action bg-dark text-white">Inventario</a>
                <div class="list-group-item bg-dark text-white">
                    <a class="text-white text-decoration-none" data-bs-toggle="collapse" href="#reportesSubmenu" role="button" aria-expanded="false" aria-controls="reportesSubmenu">
                        Informes
                    </a>
                    <div class="collapse" id="reportesSubmenu">
                        <a href="reporte1.jsp" class="list-group-item list-group-item-action bg-secondary text-white">Reporte 1</a>
                        <a href="reporte2.jsp" class="list-group-item list-group-item-action bg-secondary text-white">Reporte 2</a>
                        <a href="reporte3.jsp" class="list-group-item list-group-item-action bg-secondary text-white">Reporte 3</a>
                    </div>
                </div>
                <a href="cerrarSesion.jsp" class="list-group-item list-group-item-action bg-dark text-white">Cerrar sesión</a>
            </div>
        </div>

        <!-- Page Content -->
        <div id="page-content-wrapper">
            <h1>Devolver Equipo</h1>

            <!-- Formulario para buscar equipo -->
            <div class="container">
                <form action="#" method="get">
                    <div class="mb-3">
                        <label for="equipoNumero" class="form-label">Número del Equipo</label>
                        <input type="text" class="form-control" id="equipoNumero" name="equipoNumero" placeholder="Ingrese el número del equipo" required>
                    </div>
                    <button type="submit" class="btn btn-secondary">Buscar</button>
                </form>
            </div>

            <!-- Tabla con la información del equipo (se muestra después de la búsqueda) -->
            <div id="equipo-info" class="mt-5" style="display: none;">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Número de Equipo</th>
                            <th>Nombre Usuario</th>
                            <th>RUT</th>
                            <th>Hora de Uso</th>
                            <th>Acción</th>
                        </tr>
                    </thead>
                    <tbody id="equipoTableBody">
                        <!-- Aquí se llenará la tabla con los datos del equipo -->
                    </tbody>
                </table>
            </div>

        </div>
    </div>
    <script>
        // Función para simular la búsqueda y mostrar los datos del equipo
        document.querySelector('form').addEventListener('submit', function (e) {
            e.preventDefault(); // Prevenir el envío del formulario

            var equipoNumero = document.getElementById('equipoNumero').value;

            // Simulación de datos del equipo (esto se reemplazaría por una llamada al backend)
            var equipoData = {
                numero: equipoNumero,
                nombre: "Juan Pérez",
                rut: "12345678-9",
                horaUso: "10:30 AM"
            };

            if (equipoNumero) {
                // Mostrar la tabla con la información del equipo
                document.getElementById('equipo-info').style.display = 'block';
                var tableBody = document.getElementById('equipoTableBody');
                tableBody.innerHTML = ''; // Limpiar la tabla antes de agregar la fila

                var row = document.createElement('tr');
                row.innerHTML = `
                    <td>${equipoData.numero}</td>
                    <td>${equipoData.nombre}</td>
                    <td>${equipoData.rut}</td>
                    <td>${equipoData.horaUso}</td>
                    <td><button class="btn btn-success">Marcar como Devuelto</button></td>
                `;
                tableBody.appendChild(row);
            }
        });
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>



