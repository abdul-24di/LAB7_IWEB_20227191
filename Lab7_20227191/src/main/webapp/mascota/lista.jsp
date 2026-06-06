<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.lab7_20227191.beans.Mascota" %>
<%@ page import="com.example.lab7_20227191.beans.Especie" %>
<%@ page import="java.util.ArrayList" %>
<%
    ArrayList<Mascota> listaM = (ArrayList<Mascota>) request.getAttribute("listaMascotas");
    ArrayList<Especie> listaE = (ArrayList<Especie>) request.getAttribute("listaEspecies");
    int especieSeleccionada = (Integer) request.getAttribute("especieSeleccionada");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de Mascotas</title>
</head>
<body>

<h2>Lista de Mascotas</h2>

<!-- enlace para ir al formulario de creación -->
<a href="<%= request.getContextPath() %>/inicio?action=new">Nueva Mascota</a>
<br/><br/>

<!-- filtro por especie -->
<form action="<%= request.getContextPath() %>/inicio" method="GET">
    <input type="hidden" name="action" value="lista">
    <label for="filtrarEspecieId">Filtrar por especie: </label>
    <select name="filtrarEspecieId" id="filtrarEspecieId" onchange="this.form.submit()">
        <option value="0" <%= (especieSeleccionada == 0) ? "selected" : "" %>>Todas las especies</option>
        <% if (listaE != null) { %>
        <% for (Especie e : listaE) { %>
        <option value="<%= e.getIdEspecie() %>"
                <%= (especieSeleccionada == e.getIdEspecie()) ? "selected" : "" %>>
            <%= e.getNombre() %>
        </option>
        <% } %>
        <% } %>
    </select>
</form>
<br/>

<!-- Tabla con borde estándar HTML -->
<table border="1" cellpadding="8" cellspacing="0" style="width: 100%;">
    <thead>
    <tr style="background-color: #cccccc;">
        <th>ID</th>
        <th>Nombre</th>
        <th>Edad</th>
        <th>Peso (kg)</th>
        <th>Especie</th>
        <th>Veterinario</th>
        <th>Dueño</th>
        <th>Acción</th>
    </tr>
    </thead>
    <tbody>
    <% if (listaM != null && !listaM.isEmpty()) { %>
    <% for (Mascota m : listaM) { %>
    <tr>
        <td><%= m.getIdMascota() %></td>
        <td><%= m.getNombre() %></td>
        <td><%= m.getEdad() %></td>
        <td><%= String.format("%.2f", m.getPeso()) %></td>
        <td><%= m.getEspecie().getNombre() %></td>
        <td><%= m.getVeterinario().getNombre() %></td>
        <td><%= m.getDueno().getNombre() %></td>
        <td>
            <!-- Enlace simple de acción de borrado -->
            <a href="<%= request.getContextPath() %>/inicio?action=del&id=<%= m.getIdMascota() %>"
               onclick="return confirm('¿Seguro de eliminar?');">Borrar</a>
        </td>
    </tr>
    <% } %>
    <% } else { %>
    <tr>
        <td colspan="8" style="text-align: center;">No hay mascotas registradas.</td>
    </tr>
    <% } %>
    </tbody>
</table>

</body>
</html>