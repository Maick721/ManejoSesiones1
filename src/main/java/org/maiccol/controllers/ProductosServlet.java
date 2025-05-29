package org.maiccol.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.maiccol.models.Productos;
import org.maiccol.service.LoginService;
import org.maiccol.service.LoginServiceSessionImplement;
import org.maiccol.service.ProductoService;
import org.maiccol.service.ProductoServiceImplement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet("/productos")
public class ProductosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ProductoService service = new ProductoServiceImplement();
        LoginService auth = new LoginServiceSessionImplement();
        Optional<String> usernameOptional = auth.getUserName(req);
        boolean logeo = usernameOptional.isPresent();

        String accion = req.getParameter("accion");
        String idParam = req.getParameter("id");
        Long id = null;

        if (idParam != null) {
            try {
                id = Long.parseLong(idParam);
            } catch (NumberFormatException e) {
                id = null;
            }
        }

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        if ("editar".equalsIgnoreCase(accion) && id != null) {
            // Mostrar formulario para editar producto
            Productos producto = service.porId(id);
            if (producto == null) {
                resp.sendRedirect(req.getContextPath() + "/productos");
                return;
            }
            mostrarFormulario(out, producto, logeo, usernameOptional);
            return;
        } else if ("nuevo".equalsIgnoreCase(accion)) {
            // Formulario para nuevo producto
            mostrarFormulario(out, new Productos(), logeo, usernameOptional);
            return;
        } else if ("eliminar".equalsIgnoreCase(accion) && id != null) {
            service.eliminar(id);
            resp.sendRedirect(req.getContextPath() + "/productos");
            return;
        }

        // Por defecto, mostrar listado
        List<Productos> productos = service.listar();
        mostrarListado(out, productos, logeo, usernameOptional);
    }

    private void mostrarListado(PrintWriter out, List<Productos> productos, boolean logeo, Optional<String> usernameOptional) {
        out.println("<html><head><meta charset='utf-8'><title>Productos</title></head><body>");
        out.println("<h1>Lista de Productos</h1>");
        if (logeo) {
            out.println("<p>Hola " + usernameOptional.get() + "</p>");
        }
        out.println("<a href='productos?accion=nuevo'>Agregar Nuevo Producto</a><br><br>");
        out.println("<table border='1'>");
        out.println("<tr><th>ID</th><th>Nombre</th><th>Categoría</th>");
        if (logeo) out.println("<th>Precio</th>");
        out.println("<th>Acciones</th></tr>");
        for (Productos p : productos) {
            out.println("<tr>");
            out.println("<td>" + p.getId() + "</td>");
            out.println("<td>" + p.getNombre() + "</td>");
            out.println("<td>" + p.getTipo() + "</td>");
            if (logeo) out.println("<td>" + p.getPrecio() + "</td>");
            out.println("<td>");
            out.println("<a href='productos?accion=editar&id=" + p.getId() + "'>Editar</a> | ");
            out.println("<a href='productos?accion=eliminar&id=" + p.getId() + "' onclick='return confirm(\"Confirmar eliminación?\")'>Eliminar</a>");
            out.println("</td>");
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("</body></html>");
    }

    private void mostrarFormulario(PrintWriter out, Productos producto, boolean logeo, Optional<String> usernameOptional) {
        out.println("<html><head><meta charset='utf-8'><title>Formulario Producto</title></head><body>");
        if (logeo) {
            out.println("<p>Hola " + usernameOptional.get() + "</p>");
        }
        out.println("<h1>" + (producto.getId() == null ? "Nuevo Producto" : "Editar Producto") + "</h1>");
        out.println("<form method='post' action='productos'>");
        if (producto.getId() != null) {
            out.println("<input type='hidden' name='idProducto' value='" + producto.getId() + "'/>");
        }
        out.println("Nombre:<br>");
        out.println("<input type='text' name='nombre' value='" + (producto.getNombre() != null ? producto.getNombre() : "") + "' required><br>");
        out.println("Categoría:<br>");
        out.println("<input type='text' name='categoria' value='" + (producto.getTipo() != null ? producto.getTipo() : "") + "' required><br>");
        out.println("Precio:<br>");
        String precioValue = "";
        out.println("<input type='number' step='0.01' name='precio' value='" + precioValue + "' required><br><br>");
        out.println("<button type='submit'>Guardar</button>");
        out.println("</form>");
        out.println("<br><a href='productos'>Volver a la lista</a>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ProductoService service = new ProductoServiceImplement();

        String idParam = req.getParameter("idProducto");
        String nombre = req.getParameter("nombre");
        String categoria = req.getParameter("categoria");
        String precioParam = req.getParameter("precio");

        Long id = null;
        Double precio = null;

        try {
            if (idParam != null && !idParam.isEmpty()) {
                id = Long.parseLong(idParam);
            }
            if (precioParam != null && !precioParam.isEmpty()) {
                precio = Double.parseDouble(precioParam);
            }
        } catch (NumberFormatException e) {
            // Ignorar errores de parseo
        }

        Productos producto = new Productos();
        producto.setId(id);
        producto.setNombre(nombre);
        producto.setTipo(categoria);
        producto.setPrecio(precio);

        service.guardar(producto);

        resp.sendRedirect(req.getContextPath() + "/productos");
    }
}
