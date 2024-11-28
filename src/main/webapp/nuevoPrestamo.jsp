<%-- 
    Document   : nuevoPrestamo
    Created on : 27 nov 2024, 14:11:32
    Author     : benja
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nuevo Préstamo</title>
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

        .card {
            width: 300px;
            border-radius: 10px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            margin-bottom: 30px;
            border: 2px solid #8032fe;
        }

        .card-header {
            background-color: #8032fe;
            color: #fff;
            text-align: center;
            font-weight: 600;
        }

        .card-body {
            padding: 20px;
            text-align: center;
            background-color: #f6f1ff;
            color: #2c3e50;
        }

        .card-title {
            font-size: 2rem;
            font-weight: 500;
            margin-bottom: 10px;
        }

        .card-text {
            font-size: 1.1rem;
        }
    </style>
</head>

<body>
    <!-- Sidebar -->
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
                    <a href="#" class="list-group-item list-group-item-action bg-secondary text-white">Ver Prestamos Actuales</a>
                </div>
            </div>
            <a href="#" class="list-group-item list-group-item-action bg-dark text-white">Inventario</a>
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

    <div class="container">
        <h1>Registrar Nuevo Préstamo</h1>

        <form action="RegistrarPrestamo" method="POST">
            <div class="mb-3">
                <label for="nombre" class="form-label">Nombre Completo</label>
                <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingresa el nombre completo" required>
            </div>

            <div class="mb-3">
                <label for="rut" class="form-label">RUT</label>
                <input type="text" class="form-control" id="rut" name="rut" placeholder="Ingresa el RUT" required>
            </div>

            <div class="mb-3">
                <label for="horaEstimada" class="form-label">Hora Estimada de Uso</label>
                <input type="time" class="form-control" id="horaEstimada" name="horaEstimada" required>
            </div>

            <div class="mb-3">
                <label for="motivo" class="form-label">Motivo del Préstamo</label>
                <textarea class="form-control" id="motivo" name="motivo" rows="3" placeholder="Describe el motivo del préstamo" required></textarea>
            </div>

            <div class="d-grid gap-2">
                <button type="submit" class="btn btn-secondary">Registrar Préstamo</button>
            </div>
        </form>
    </div>  
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>


