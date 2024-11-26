<!DOCTYPE html>
<html>
<head>
  <title>Lista de Usuarios</title>
</head>
<body>
<h1>Usuarios Registrados</h1>
<ul>
  <%
    java.util.List<modelo.Usuario> usuarios = modelo.BaseDeDatos.obtenerUsuarios();
    for (modelo.Usuario usuario : usuarios) {
  %>
  <li><%= usuario.getNombre() %> (Edad: <%= usuario.getEdad() %>)</li>
  <% } %>
</ul>
</body>
</html>
