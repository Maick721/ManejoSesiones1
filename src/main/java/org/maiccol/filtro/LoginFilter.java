package org.maiccol.filtro;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.maiccol.services.LoginService;
import org.maiccol.services.LoginServiceSessionImplement;

import java.io.IOException;
import java.util.Optional;
@WebFilter({"/productos","/agregar-carro","/categoria"})
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LoginService service = new LoginServiceSessionImplement();
        Optional<String> username = service.getUserName((HttpServletRequest) request);
        //Realizo una condicional para ver si esta pesente el nombre del usuario
        if (username.isPresent()){
            chain.doFilter(request, response);
        }else{
            ((HttpServletResponse)response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos no estas autorizado para ingresar a esta página");
        }
    }
}
