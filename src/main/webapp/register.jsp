<%-- 
    Document   : register
    Created on : 23 nov 2024, 19:55:42
    Author     : benja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Registro de Usuario</title>
  <link rel="stylesheet" href="style.css" />
  <!-- Font Awesome CDN link for icons -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" />
</head>
<body>
  <div class="wrapper">
    <div class="title"><span>Registro de Usuario</span></div>
    <form action="SvRegistrarEncargado" method="post">
        
        <!-- Rut -->
      <div class="row">
        <i class="fas fa-id-card"></i>
        <input type="text" name="rut" placeholder="RUT (ej. 12345678-9)" required />
      </div>
        
        <!-- Nombres -->
      <div class="row">
        <i class="fas fa-user"></i>
        <input type="text" name="nombres" placeholder="Nombres" required />
      </div>
        
        <!-- Apellidos -->
      <div class="row">
        <i class="fas fa-user"></i>
        <input type="text" name="apellidos" placeholder="Apellidos" required />
      </div>
        
        <!-- Correo -->
      <div class="row">
        <i class="fas fa-envelope"></i>
        <input type="email" name="correo" placeholder="Correo Electrónico" required />
      </div>
        
        <!-- Teléfono -->
      <div class="row">
        <i class="fas fa-phone"></i>
        <input type="text" name="telefono" placeholder="Teléfono" required />
      </div>
        
        <!-- Contraseña -->
      <div class="row">
        <i class="fas fa-lock"></i>
        <input type="password" name="contrasenia" placeholder="Contraseña" required />
      </div>
        
        <!-- Botón  -->
      <div class="row button">
        <input type="submit" value="Registrarse" />
      </div>
      <div class="signup-link">
        <a href="login.jsp">Iniciar Sesión</a>
      </div>
    </form>
  </div>
</body>
</html>
