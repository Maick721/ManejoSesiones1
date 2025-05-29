<%--
  Created by IntelliJ IDEA.
  User: Maiccol
  Date: 28/5/2025
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: Maiccol
  Date: 28/5/2025
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*, org.maiccol.models.*" %>

<%
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
    Optional<String> username = (Optional<String>) request.getAttribute("username");
%>
<html>
<head>
    <title>Listado Categoria</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<%
    if(username.isPresent()){%>

<div class="welcome"> Bienvenido <%= username.get()%>, Que deseas agregar</div>

<div><p><a href="${pageContext.request.contextPath}/categoria/form">Ingrese el producto</a></p></div>
<%}%>

<h1>Listado Categoria</h1>
<table>
    <thead>
    <th>Id Categoria</th>
    <th>Nombre</th>
    <th> Descipción</th>
    <th>Condición</th>
    <th>Acciones</th>
    </thead>
    <%
        for (Categoria cat : categorias) {%>
    <tbody>
    <td><%= cat.getIdCategoria()%></td>
    <td><%= cat.getNombre()%></td>
    <td><%= cat.getDescripcion()%></td>
    <td><%= cat.getCondicion()%></td>
    <td><a href="">Editar</a></td>
    <td><a href="">Activar o Desactivar</a></td>
    </tbody>
    <% } %>

</table>

</body>
</html>

