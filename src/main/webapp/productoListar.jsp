<%--
  Created by IntelliJ IDEA.
  User: Maiccol Zurtia
  Date: 3/6/2025
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.util.*, org.maiccol.models.*" %>
<%@ page import="org.maiccol.models.Productos" %>
<%
  List<Productos> productos = (List<Productos>) request.getAttribute("productos");
  Optional<String> username = (Optional<String>) request.getAttribute("username");
%>
<html>
<head>
  <title>Listado Productos</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/productoListar.css">
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 20px;
      background-color: #f4f4f4;
    }

    h1 {
      color: #333;
    }

    table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 20px;
      background-color: #fff;
    }

    th, td {
      padding: 10px;
      text-align: left;
      border: 1px solid #ddd;
    }

    th {
      background-color: #4CAF50;
      color: white;
    }

    tr:nth-child(even) {
      background-color: #f2f2f2;
    }

    tr:hover {
      background-color: #ddd;
    }

    img {
      max-width: 50px;
      max-height: 50px;
    }

    .welcome {
      color: blue;
      margin-bottom: 10px;
    }

    .actions a {
      margin-right: 10px;
      color: #007BFF;
      text-decoration: none;
    }

    .actions a:hover {
      text-decoration: underline;
    }

    .back-link {
      display: inline-block;
      margin-top: 20px;
      color: #007BFF;
      text-decoration: none;
    }

    .back-link:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>

<h1>Listado Producto</h1>
<%
  if (username.isPresent()) { %>
<div class="welcome">Hola, <%= username.get() %> bienvenido</div>
<div><a href="<%= request.getContextPath() %>/productos/form">Añadir Producto</a></div>
<a class="back-link" href="index.html">Volver al Inicio</a>
<% } %>

<table>
  <thead>
  <tr>
    <th>ID Producto</th>
    <th>ID Categoria</th>
    <th>Código</th>
    <th>Nombre</th>
    <th>Stock</th>
    <th>Descripción</th>
    <th>Imagen</th>
    <th>Condición</th>
    <% if (username.isPresent()) { %>
    <th>Acción</th>
    <% } %>
  </tr>
  </thead>
  <tbody>
  <%
    for (Productos prod : productos) { %>
  <tr>
    <td><%= prod.getIdProducto() %></td>
    <td><%= prod.getIdCategoria() %></td>
    <td><%= prod.getCodigo() %></td>
    <td><%= prod.getNombre() %></td>
    <td><%= prod.getStock() %></td>
    <td><%= prod.getDescripcion() %></td>
    <td>
      <% if(prod.getImagen() != null && !prod.getImagen().isEmpty()) { %>
      <img src="<%= prod.getImagen() %>" alt="Imagen"/>
      <% } else { %>
      Sin imagen
      <% } %>
    </td>
    <td><%= prod.isCondicion() ? "Activo" : "Inactivo" %></td>
    <% if (username.isPresent()) { %>
    <td class="actions">
      <a href="<%= request.getContextPath() %>/productos/form?id=<%= prod.getIdProducto() %>">Editar</a>
      <a href="#">Eliminar</a>
    </td>
    <% } %>
  </tr>
  <%
    }
  %>
  </tbody>
</table>

</body>
</html>
