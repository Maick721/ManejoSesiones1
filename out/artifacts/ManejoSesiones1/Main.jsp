<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="org.maiccol.service.ProductoServiceImplement"%>
<%@page import="org.maiccol.models.Productos"%>

<%
    // Crear una instancia del servicio de productos
    ProductoServiceImplement productoService = new ProductoServiceImplement();

    // Obtener la lista de productos llamando al método listar() del servicio
    List<Productos> productos = productoService.listar();
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registro Compra - Venta</title>
    <link rel="stylesheet" href="css/Main.css"> <!-- Enlaza nuestro archivo CSS -->
</head>
<body>

<div class="header">Registro de Compra y Venta</div>
<div class="nav">
    <a href="#">Productos</a>
    <a href="#">Categoría</a>
    <a href="#">Forma</a>
    <a href="#">Precio</a>
    <button><a href="index.html">Login</a></button>
</div>

<!-- Tabla de Productos -->
<table>
    <thead>
    <tr>
        <th>ID PRODUCTO</th>
        <th>NOMBRE</th>
        <th>CATEGORÍA</th>
        <th>PRECIO</th>
        <th>ACCIONES</th>
    </tr>
    </thead>
    <tbody>
    <%
        // Hacemos un for each para recorrer cada producto en la lista
        for (Productos p : productos) {
    %>
    <tr>
        <td><%= p.getId() %></td>
        <td><%= p.getNombre() %></td>
        <td><%= p.getTipo() %></td>
        <td>$<%= String.format("%.2f", p.getPrecio()) %></td>
        <td class="actions">
            <!-- Formulario para Editar -->
            <form action="editarProducto" method="post" style="display:inline;">
                <input type="hidden" name="id" value="<%= p.getId() %>">
                <input type="submit" value="Editar">
            </form>

            <!-- Formulario para Eliminar -->
            <form action="eliminarProducto" method="post" style="display:inline;">
                <input type="hidden" name="id" value="<%= p.getId() %>">
                <input type="submit" value="Eliminar">
            </form>
        </td>
    </tr>
    <%
        } // Fin del bucle for
    %>
    </tbody>
</table>

</body>
</html>