<%--
  Created by IntelliJ IDEA.
  User: USUARIO
  Date: 3/6/2025
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.util.*, org.maiccol.models.*" %>
<%
    // Obtener los atributos de la solicitud
    Categoria categorias = (Categoria) request.getAttribute("categorias");
    Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
    Productos producto = (Productos) request.getAttribute("producto"); // Asegúrate de que este atributo esté establecido en el controlador
%>
<html>
<head>
    <title>Formulario Categoria</title>
</head>
<body>
<h1>Formulario Categoria</h1>
<div>
    <form action="<%=request.getContextPath()%>/categoria/form" method="post">
        <div>
            <label for="nombre">Ingrese el nombre de categoria</label>
            <div>
                <input type="hidden" name="id" value="<%= categorias != null ? categorias.getIdCategoria() : "" %>">
                <input type="text" id="nombre" name="nombre" value="<%= categorias != null && categorias.getNombre() != null ? categorias.getNombre() : "" %>">
                <div>
                    <span><%= (errores != null && errores.get("nombre") != null) ? errores.get("nombre") : "" %></span>
                </div>
            </div>
        </div>

        <div>
            <label for="descripcion">Ingrese la descripción</label>
            <div>
                <input type="text" id="descripcion" name="descripcion" value="<%= categorias != null && categorias.getDescripcion() != null ? categorias.getDescripcion() : "" %>">
                <div>
                    <span><%= (errores != null && errores.get("descripcion") != null) ? errores.get("descripcion") : "" %></span>
                </div>
            </div>
        </div>

        <div>
            <input type="hidden" name="idProducto"
                   value="<%= producto != null && producto.getIdProducto() != 0 ? producto.getIdProducto() : "" %>">
        </div>

        <div>
            <input type="submit" value="<%=(categorias != null && categorias.getIdCategoria() != null && categorias.getIdCategoria() > 0) ? "Editar" : "Crear"%>">
        </div>
    </form>
</div>
</body>
</html>
