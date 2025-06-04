package org.maiccol.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class LoginServiceSessionImplement implements LoginService{
    @Override
    public Optional<String> getUserName(HttpServletRequest request) {
        //Obtemos la sesion
        HttpSession session = request.getSession();
        //Convierto los datos de la sesión en un String
        String username = (String)session.getAttribute("username");
        /*4Creo una condición en la cual valido
         * si al obtener el nombre del usuario es distinto de nulo
         * obtengo el username
         * Caso contrario devuelvo la sesión vacia*/
        if (username !=null){
            return Optional.of(username);
        }
        return Optional.empty();
    }
}
