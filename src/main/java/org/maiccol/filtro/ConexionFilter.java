package org.maiccol.filtro;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.maiccol.services.ServiceJdbcException;
import org.maiccol.util.Conexion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


public class ConexionFilter implements Filter {
    //La clase Filter siempre va a estar orientada a las peticiones request

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //Obtenemos la conexión a la base de datos
        try(Connection conn = Conexion.getConnection()) {
            //Implemento un if para saber si esta activado el autocommit
            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            try{
                //Seteamos los parametros de la conexión
                request.setAttribute("conn", conn);
                //Filtramos la conexión
                chain.doFilter(request, response);
                conn.commit();

            }catch(SQLException | ServiceJdbcException e){
                //La consulta no se ejecuta y vuelvbe a su estado anterior de la consulta
                conn.rollback();
                ((HttpServletResponse)response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                e.printStackTrace();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
