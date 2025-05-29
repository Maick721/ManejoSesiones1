package org.maiccol.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.maiccol.service.LoginService;
import org.maiccol.service.LoginServiceSessionImplement;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Creamos el objeto de tipo sesión
        LoginService auth= new LoginServiceSessionImplement();
        Optional<String> userNameOptional= auth.getUserName(req);
        if(userNameOptional.isPresent()){
            //Se obtiene la sesión
            HttpSession session= req.getSession();
            //Cerramos la sesión
            session.invalidate();
        }

        resp.sendRedirect(req.getContextPath()+"/login.html");
    }
}