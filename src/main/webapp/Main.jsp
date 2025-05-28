<%--
  Created by IntelliJ IDEA.
  User: Maiccol Zurita
  Date: 27/5/2025
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<!-- 1. ENCABEZADO JSP Y CONFIGURACIONES INICIALES -->
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>           <!-- Importamos listas de Java -->
<%@page import="org.maiccol.service.ProductoServiceImplement"%> <!-- Nuestra clase de servicio -->
<%@page import="org.maiccol.models.Productos"%> <!-- Nuestra clase modelo Productos -->

<!-- 2. CÓDIGO JAVA PARA OBTENER LOS PRODUCTOS -->
<%
    // Crear una instancia del servicio de productos
    ProductoServiceImplement productoService = new ProductoServiceImplement();

    // Obtener la lista de productos llamando al método listar() del servicio
    List<Productos> productos = productoService.listar();
    // Esto sería como pedirle a un asistente: "Oye, tráeme todos los productos que tengas registrados"
%>

<!DOCTYPE html>
<html>
<head>
    <title>Registro Compra - Venta</title>
    <link rel="stylesheet" href="css/Main.css">
</head>
<body>
<div class="header">Registro Compra - Venta</div>
<div class="nav">
    <a href="#">Productos</a>
    <a href="#">Categoria</a>
    <a href="#">Forma</a>
    <a href="#">Precio</a>
</div>
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
    <tr>
        <td>1</td>
        <td>laptop</td>
        <td>computacion</td>
        <td>$523,21</td>
        <td class="actions">
            <button class="edit">Editar</button>
            <button class="delete">Eliminar</button>
        </td>
    </tr>
    <tr>
        <td>2</td>
        <td>Mouse</td>
        <td>inalambrico</td>
        <td>$15,25</td>
        <td class="actions">
            <button class="edit">Editar</button>
            <button class="delete">Eliminar</button>
        </td>
    </tr>
    <tr>
        <td>3</td>
        <td>Impresora</td>
        <td>tinta continua</td>
        <td>$256,25</td>
        <td class="actions">
            <button class="edit">Editar</button>
            <button class="delete">Eliminar</button>
        </td>
    </tr>
    </tbody>
</table>
</body>
</html>