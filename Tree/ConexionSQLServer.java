
import java.sql.*;

public class ConexionSQLServer {
    public static void main(String[] args) {
        // Datos de conexión
        String url = "jdbc:sqlserver://localhost:1433;databaseName=miBaseDeDatos;encrypt=true;trustServerCertificate=true";
        String usuario = "sa"; // Cambia esto por tu usuario
        String contrasena = "tu_contraseña"; // Cambia esto por tu contraseña

        // Conexión a la base de datos
        try (Connection conexion = DriverManager.getConnection(url, usuario, contrasena)) {
            System.out.println("Conexión exitosa a SQL Server.");

            // Realizar una consulta simple
            leerDatos(conexion);

        } catch (SQLException e) {
            System.out.println("Error de conexión.");
            e.printStackTrace();
        }
    }

    // Método para leer datos de la base de datos
    public static void leerDatos(Connection conexion) throws SQLException {
        String sql = "SELECT id, nombre, email FROM usuarios"; // Modifica esta consulta según tu base de datos
        try (Statement stmt = conexion.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\nUsuarios en la base de datos:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Email: " + email);
            }
        }
    }
}