package org.maiccol.service;

import org.maiccol.models.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {


    List<Categoria> listar();
    Optional<Categoria> porId(Long id);

    Optional<Categoria> porId(Integer id);

    //Implementar Activar Y Desactivar
    void guardar(Categoria categoria);
    void eliminar(Long id);


    void eliminar(Integer id);
}
