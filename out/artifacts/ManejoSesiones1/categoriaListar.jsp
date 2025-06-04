<%--
  Created by IntelliJ IDEA.
  User: ADMIN-ITQ
  Date: 28/5/2025
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.util.*, org.maiccol.models.*" %>
<%@ page import="org.maiccol.models.Categoria" %>
<%
  List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
  Optional<String> username = (Optional<String>) request.getAttribute("username");
%>
<html>
<head>
  <title>Listado Categoria</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/categoriaListar.css">
</head>
<body>

<h1>Listado Categoria</h1>
<%
  if (username.isPresent()) {%>
<div class="welcome">Hola, <%=username.get()%> bienvenido</div>
<div><a href="${pageContext.request.contextPath}/categoria/form">Añadir Categorias</a></div>
<a class="back-link" href="index.html">Volver al Inicio</a>
<%}%>

<table>
  <thead>
  <tr>
    <th>ID CATEGORIA</th>
    <th>NOMBRE</th>
    <th>DESCRIPCIÓN</th>
    <th>CONDICIÓN</th>
    <th>ACCIÓN</th>
  </tr>
  </thead>
  <tbody>
  <%
    for (Categoria cate : categorias) {%>
  <tr>
    <td><%=cate.getIdCategoria()%></td>
    <td><%=cate.getNombre()%></td>
    <td><%=cate.getDescripcion()%></td>
    <td><%=cate.getCondicion()%></td>
    <%if(username.isPresent()){%>
    <td class="actions">
      <a href="${pageContext.request.contextPath}/categoria/form?id=<%=cate.getIdCategoria()%>">Editar</a>
      <a href="#">Eliminar</a>
    </td>
    <%}%>
  </tr>
  <% }%>
  </tbody>
</table>

</body>
</html>
