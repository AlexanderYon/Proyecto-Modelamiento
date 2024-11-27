package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.sql.Date;

public class BaseDeDatos {
    private static final String URL = "jdbc:sqlite:mi_base_de_datos.db";
    private static final String DRIVER = "org.sqlite.JDBC";

    public static Connection conectar() {
        Connection conexion = null;
        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL);
            System.out.println("Conexión establecida a la base de datos.");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el controlador JDBC.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error: No se pudo conectar a la base de datos.");
            e.printStackTrace();
        }
        return conexion;
    }

    public static void inicializarBaseDeDatos() {
        String sql = "CREATE TABLE IF NOT EXISTS usuarios (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL, " +
                "edad INTEGER NOT NULL)";

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabla 'usuarios' creada o ya existe.");
        } catch (Exception e) {
            System.err.println("Error al inicializar la base de datos.");
            e.printStackTrace();
        }
    }
    public static boolean insertarUsuario(String nombre, int edad) {
        String sql = "INSERT INTO usuarios (nombre, edad) VALUES (?, ?)";

        try (Connection conn = conectar();
             var pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, edad);
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Error al insertar usuario.");
            e.printStackTrace();
            return false;
        }
    }

    public static List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = conectar();
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery(sql)) {
            while (rs.next()) {

                String nombre = rs.getString("nombre");
                String rutString = rs.getString("rut");
                Date sqlDate = rs.getDate("fechaNacimiento");
                LocalDate fechaNacimiento = (sqlDate != null) ? sqlDate.toLocalDate() : null;
                String nroTelefono = rs.getString("nroTelefono");

                try {
                    Rut rut = Rut.valueOf(rutString);
                    usuarios.add(new Usuario(nombre, rut, fechaNacimiento, nroTelefono));
                } catch (IllegalAccessException e) {
                    System.err.println("Error al convertir el RUT: " + rutString);
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.err.println("Error al obtener usuarios.");
            e.printStackTrace();
        }
        return usuarios;


    }

}