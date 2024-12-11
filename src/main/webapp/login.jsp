<%-- 
    Document   : login
    Created on : 23 nov 2024, 19:55:06
    Author     : benja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Inicio de Sesión</title>
        <link rel="stylesheet" href="style.css" />
        <!-- Font Awesome CDN link for icons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" />
    </head>
    <body>
        <div class="wrapper">
            <div class="title"><span>Inicio de Sesión</span></div>
            
            <!-- Formulario para iniciar sesión -->
            <form action="SvLogin" method="post">
                
                <!-- Rut -->
                <div class="row">
                    <i class="fas fa-id-card"></i>
                    <input type="text" name="rut" placeholder="RUT (ej. 12.345.678-9)" required />
                </div>
                
                <!-- Contraseña -->
                <div class="row">
                    <i class="fas fa-lock"></i>
                    <input type="password" name="contraseña" placeholder="Contraseña" required />
                </div>
                
                <!-- Botón de confirmación -->
                <div class="pass"><a href="#"></a></div>
                <div class="row button">
                    <input type="submit" value="Login" />
                </div>
                <div class="signup-link">
                    <a href="register.jsp">Registrarse</a>
                </div>
            </form>
        </div>
    </body>
</html>
