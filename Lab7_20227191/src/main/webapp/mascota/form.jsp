<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.lab7_20227191.beans.Especie" %>
<%@ page import="com.example.lab7_20227191.beans.Veterinario" %>
<%@ page import="com.example.lab7_20227191.beans.Dueno" %>
<%@ page import="java.util.ArrayList" %>
<%
  ArrayList<Especie> listaE = (ArrayList<Especie>) request.getAttribute("listaEspecies");
  ArrayList<Veterinario> listaV = (ArrayList<Veterinario>) request.getAttribute("listaVeterinarios");
  ArrayList<Dueno> listaD = (ArrayList<Dueno>) request.getAttribute("listaDuenos");
%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Registrar Mascota</title>
</head>
<body>

<h2>Nueva Mascota</h2>

<form action="<%= request.getContextPath() %>/inicio" method="POST">
  <input type="hidden" name="action" value="guardar">
  <input type="hidden" name="isEdit" value="false">

  <label for="nombre">Nombre:</label><br/>
  <input type="text" id="nombre" name="nombre" required placeholder="Nombre de la mascota">
  <br/><br/>

  <label for="edad">Edad:</label><br/>
  <input type="number" id="edad" name="edad" required min="0">
  <br/><br/>

  <label for="peso">Peso (kg):</label><br/>
  <input type="number" step="0.01" id="peso" name="peso" required min="0">
  <br/><br/>

  <!-- ComboBox Especie -->
  <label for="especieId">Especie:</label><br/>
  <select name="especieId" id="especieId" required>
    <option value="">-- Seleccione especie --</option>
    <% if (listaE != null) { %>
    <% for (Especie esp : listaE) { %>
    <option value="<%= esp.getIdEspecie() %>"><%= esp.getNombre() %></option>
    <% } %>
    <% } %>
  </select>
  <br/><br/>

  <!-- ComboBox Veterinario -->
  <label for="veterinarioId">Veterinario:</label><br/>
  <select name="veterinarioId" id="veterinarioId" required>
    <option value="sin-veterinario">-- Sin veterinario asignado --</option>
    <% if (listaV != null) { %>
    <% for (Veterinario vet : listaV) { %>
    <option value="<%= vet.getIdVeterinario() %>">
      <%= vet.getNombre() %> - <%= vet.getEspecialidad() %>
    </option>
    <% } %>
    <% } %>
  </select>
  <br/><br/>

  <!-- ComboBox dinámico: Dueño -->
  <label for="duenoId">Dueño:</label><br/>
  <select name="duenoId" id="duenoId" required>
    <option value="">-- Seleccione dueño --</option>
    <% if (listaD != null) { %>
    <% for (Dueno due : listaD) { %>
    <option value="<%= due.getIdDueno() %>"><%= due.getNombre() %></option>
    <% } %>
    <% } %>
  </select>
  <br/><br/>

  <a href="<%= request.getContextPath() %>/inicio">Cancelar</a>
  <button type="submit">Guardar</button>
</form>

</body>
</html>