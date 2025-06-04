package org.maiccol.services;

import org.maiccol.models.Productos;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Productos> listar();

    Optional<Productos> porId(Long id);

    void guardar(Productos producto);

    void eliminar(Long id);
}
