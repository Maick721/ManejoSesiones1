
package org.maiccol.service;

import org.maiccol.models.Productos; // Importa la clase Producto del paquete models
import java.util.List; // Importa la interfaz List para manejar listas


public interface ProductoService { // Declara la interfaz ProductoService
    List<Productos> listar(); // Método abstracto que retorna una lista de productos

    void guardar(Productos producto);

    void eliminar(Long id);

    Productos porId(Long id);
}

