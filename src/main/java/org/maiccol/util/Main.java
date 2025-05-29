package org.maiccol.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {

    public static void main(String[] args) {
        // Imprime mensaje de inicio
        System.out.println("Conexion de la Tabla Categoria");

        // Utiliza try-with-resources para manejar la conexión automáticamente
        try (Connection conn = Conexion.getConnection()) {
            // Verifica si la conexión está activa y no cerrada
            if (conn != null && !conn.isClosed()) {
                System.out.println("Conectamos correctamente");

                // Define la consulta SQL para seleccionar todas las categorías
                String sql = "SELECT * FROM categoria";

                // Crea un PreparedStatement para ejecutar la consulta de forma segura
                // y un ResultSet para obtener los resultados
                // También usa try-with-resources para cerrar automáticamente estos recursos
                try (PreparedStatement stmt = conn.prepareStatement(sql);
                     ResultSet rs = stmt.executeQuery()) {

                    // Itera sobre cada fila de los resultados hasta llegar a un false es decir que no hay
                    while (rs.next()) {
                        // Obtiene el valor de la columna 'id' como entero
                        int id = rs.getInt("id");
                        // Obtiene el valor de la columna 'nombre' como String
                        String nombre = rs.getString("nombre");
                        // Imprime los datos de la categoría
                        System.out.println("Categoría ID: " + id + ", Nombre: " + nombre);
                    }
                }
            }
            // Captura cualquier error SQL que pueda ocurrir
        } catch (SQLException e) {
            // Imprime mensaje de error
            System.err.println("Existe un error al consultar la base de datos:");
            // Imprime el mensaje específico del error
            System.err.println("Mensaje: " + e.getMessage());
            // Imprime la traza completa del error
            e.printStackTrace();
        }
    }
}