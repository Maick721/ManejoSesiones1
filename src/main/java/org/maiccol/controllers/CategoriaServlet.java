package org.maiccol.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.maiccol.models.Categoria;
import org.maiccol.services.CategoriaService;
import org.maiccol.services.CategoriaServiceJbdcImplement;
import org.maiccol.services.LoginService;
import org.maiccol.services.LoginServiceSessionImplement;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;


@WebServlet("/categoria")
public class CategoriaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Creamos la conexion
        Connection conn = (Connection) req.getAttribute("conn");
        //Creamos el nuevo objeto de Categorias
        CategoriaService service= new CategoriaServiceJbdcImplement(conn);
        List<Categoria> categorias = service.listar();

        //Obtengo el username
        LoginService auth= new LoginServiceSessionImplement();
        Optional<String> userName= auth.getUserName(req);

        //Seteamos los parámetros
        req.setAttribute("categorias", categorias);
        req.setAttribute("username", userName);
        //redireccionamos a la vista de categoria
        getServletContext().getRequestDispatcher("/categoriaListar.jsp").forward(req, resp);


    }
}
