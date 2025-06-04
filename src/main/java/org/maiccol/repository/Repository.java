package org.maiccol.repository;

import java.sql.SQLException;
import java.util.List;

/*
 * <T> Es un parámetro generico que permite que la interfaz sea utilizada
 * como se desee o cualquieo tipo de objeto(entidad) que se desee manejar*/
public interface Repository <T>{

    List<T> listar() throws SQLException;
    T porId(Long id) throws SQLException;
    void guardar(T t) throws SQLException;
    void eliminar(Long id) throws SQLException;
}
