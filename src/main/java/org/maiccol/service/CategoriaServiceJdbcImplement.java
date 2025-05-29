package org.maiccol.service;

import org.maiccol.models.Categoria;
import org.maiccol.repository.CategoriaRepositoryJdbcImplement;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CategoriaServiceJdbcImplement implements CategoriaService {

    private CategoriaRepositoryJdbcImplement repositoryJdbc;

    public CategoriaServiceJdbcImplement(Connection conn) {
        this.repositoryJdbc = new CategoriaRepositoryJdbcImplement(conn);
    }

    @Override
    public List<Categoria> listar() {
        try {
            return repositoryJdbc.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Categoria> porId(Long id) {
        return Optional.empty(); // Este método no se usa, puedes eliminarlo si no es necesario.
    }

    @Override
    public Optional<Categoria> porId(Integer id) {
        try {
            return Optional.ofNullable(repositoryJdbc.porId(id));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void guardar(Categoria categoria) {
        try {
            // Verifica si el campo "idcategoria" tiene un valor válido
            if (categoria.getIdCategoria() == null || categoria.getIdCategoria() <= 0) {
                // Si no tiene un valor válido, deja que se genere automáticamente en la base de datos
                repositoryJdbc.guardar(categoria);
            } else {
                // Si tiene un valor válido, guarda la categoría con ese valor
                repositoryJdbc.guardar(categoria);
            }
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }


    @Override
    public void eliminar(Long id) {

    }

    @Override
    public void eliminar(Integer id) { // Cambiado a Integer
        try {
            repositoryJdbc.eliminar(id);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
