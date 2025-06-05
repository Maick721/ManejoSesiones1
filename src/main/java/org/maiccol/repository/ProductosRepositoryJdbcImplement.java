package org.maiccol.repository;

import org.maiccol.models.Productos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Implementación del repositorio para la entidad Productos utilizando JDBC
public class ProductosRepositoryJdbcImplement implements Repository<Productos> {

    // Variable para mantener la conexión a la base de datos
    private Connection conn;

    // Constructor que recibe la conexión activa y la asigna al atributo de clase
    public ProductosRepositoryJdbcImplement(Connection connection) {
        this.conn = connection;
    }

    // Método para listar todos los productos activos (condicion = 1)
    @Override
    public List<Productos> listar() throws SQLException {
        List<Productos> productos = new ArrayList<>(); // Lista donde se almacenarán los productos

        // Se crea un Statement y se ejecuta una consulta que une articulo con categoria
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT a.*, c.nombre as nombre_categoria " +
                             "FROM articulo a " +
                             "INNER JOIN categoria c ON a.idcategoria = c.idcategoria " +
                             "WHERE a.condicion = 1")) {

            // Se recorre el ResultSet y se transforma cada fila en un objeto Productos
            while (rs.next()) {
                Productos p = getProductos(rs);  // Convierte la fila actual en un objeto Productos
                productos.add(p);                // Agrega el objeto a la lista
            }
        }
        return productos;
    }

    // Método para buscar un producto por su ID, solo si está activo (condicion = 1)
    @Override
    public Productos porId(Long id) throws SQLException {
        Productos producto = null;

        // Se prepara la consulta SQL con un parámetro (?)
        try (PreparedStatement stmt = conn.prepareStatement(
                "SELECT a.*, c.nombre as nombre_categoria " +
                        "FROM articulo a " +
                        "INNER JOIN categoria c ON a.idcategoria = c.idcategoria " +
                        "WHERE a.idarticulo = ? AND a.condicion = 1")) {

            stmt.setLong(1, id); // Se establece el valor del parámetro id
            // Se ejecuta la consulta y si hay resultados, se obtiene el producto
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = getProductos(rs); // Se transforma el resultado en un objeto Productos
                }
            }
        }
        return producto;
    }

    // Método para guardar un producto nuevo o actualizar uno existente
    @Override
    public void guardar(Productos producto) throws SQLException {
        String sql;
        boolean esActualizacion = producto.getIdProducto() > 0; // Si el ID es mayor a 0, es actualización

        // Se define la consulta SQL según si es un nuevo producto o una actualización
        if (esActualizacion) {
            sql = "UPDATE articulo SET idCategoria = ?, codigo = ?, nombre = ?, stock = ?, descripcion = ?, imagen = ? WHERE idArticulo = ?";
        } else {
            sql = "INSERT INTO articulo(idCategoria, codigo, nombre, stock, descripcion, imagen, condicion) VALUES (?, ?, ?, ?, ?, ?, 1)";
        }

        // Se prepara y ejecuta la sentencia SQL
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, producto.getIdCategoria());
            stmt.setString(2, producto.getCodigo());
            stmt.setString(3, producto.getNombre());
            stmt.setLong(4, producto.getStock());
            stmt.setString(5, producto.getDescripcion());
            stmt.setString(6, producto.getImagen());

            // Si es actualización, también se establece el ID del producto
            if (esActualizacion) {
                stmt.setLong(7, producto.getIdProducto());
            }

            stmt.executeUpdate(); // Ejecuta la inserción o actualización
        }
    }

    // Método para eliminar lógicamente un producto (condicion = 0)
    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "UPDATE articulo SET condicion = 0 WHERE idArticulo = ?";

        // Se prepara la sentencia y se establece el parámetro ID
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate(); // Se ejecuta el cambio de estado (eliminación lógica)
        }
    }

    // Método auxiliar que convierte una fila del ResultSet en un objeto Productos
    private static Productos getProductos(ResultSet rs) throws SQLException {
        Productos p = new Productos();
        p.setIdProducto(rs.getLong("idArticulo"));
        p.setIdCategoria(rs.getLong("idCategoria"));
        p.setCodigo(rs.getString("codigo"));
        p.setNombre(rs.getString("nombre"));
        p.setStock(rs.getLong("stock"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setImagen(rs.getString("imagen"));
        p.setCondicion(rs.getInt("condicion") == 1); // true si condicion == 1
        p.setNombreCategoria(rs.getString("nombre_categoria")); // Nombre de la categoría desde el JOIN
        return p;
    }
}
