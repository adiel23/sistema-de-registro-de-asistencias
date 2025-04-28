package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_asistencia";
    private static final String USER = System.getenv("DB_USER");
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    public static Connection getConnection() {
        Connection con = null;
        System.out.println("ejecutando conexion");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Asegúrate de usar el driver correcto
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Error: Driver de MySQL no encontrado.");
           
        } catch (SQLException e) {
            System.err.println("❌ Error al conectar a la base de datos.");
        }
        return con;
    }
}

