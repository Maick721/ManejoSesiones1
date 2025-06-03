package org.maiccol.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.maiccol.models.Categoria;
import org.maiccol.service.CategoriaService;
import org.maiccol.service.CategoriaServiceJdbcImplement;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/categoria/form")
public class CategoriaForm extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Traemos la conexión a la base de datos

        Connection conn = (Connection) req.getAttribute("conn");
        CategoriaService service = new CategoriaServiceJdbcImplement(conn);
        Integer id;
        // Validamos que el campo ingresado sea un número
        try {
            // En la variable id guardamos lo que estamos mapeando por el método get idCategoria
            id = Integer.parseInt(req.getParameter("idCategoria"));
        } catch (NumberFormatException e) {
            id = 0;
        }

        // Creamos un objeto Categoria vacío
        Categoria categoria = new Categoria();
        // Verificamos si el id > 0
        if (id > 0) {
            // Creamos una variable de tipo optional para obtener la categoría por id
            Optional<Categoria> optionalCategoria = service.porId(id);
            // Si la variable optional está presente, obtenemos todos los valores
            if (optionalCategoria.isPresent()) {
                categoria = optionalCategoria.get();
            }
        }
        // Establecemos los atributos en el alcance de request
        req.setAttribute("categorias", categoria);
        getServletContext().getRequestDispatcher("/formularioCategoria.jsp").forward(req, resp);
    }

    // Sobreescribimos el método doPost
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        CategoriaService service = new CategoriaServiceJdbcImplement(conn);
        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");
        // Obtenemos el idCategoria
        Integer idCategoria;
        try {
            idCategoria = Integer.parseInt(req.getParameter("idCategoria"));
        } catch (NumberFormatException e) {
            idCategoria = null; // Si no se proporciona un idCategoria, dejamos que se genere automáticamente
        }

        Categoria categoria = new Categoria();
        categoria.setIdCategoria(idCategoria);
        categoria.setNombre(nombre);
        categoria.setDescripcion(descripcion);
        service.guardar(categoria);
        // Redireccionamos al listado para no ejecutar el método doPost
        resp.sendRedirect(req.getContextPath() + "/categoria");
    }
}
