package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        String sqlUsuarios = "CREATE TABLE IF NOT EXISTS usuarios (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL, " +
                "edad INTEGER NOT NULL)";

        String sqlEquipos = "CREATE TABLE IF NOT EXISTS equipos (" +
                "idEquipo TEXT PRIMARY KEY, " +
                "marca TEXT NOT NULL, " +
                "modelo TEXT NOT NULL, " +
                "descripcion TEXT, " +
                "estado TEXT NOT NULL, " +
                "prestamo TEXT)"; // Puede ser null si no está prestado

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sqlUsuarios);
            stmt.execute(sqlEquipos);
            System.out.println("Tablas 'usuarios' y 'equipos' creadas o ya existen.");
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
                int edad = rs.getInt("edad");
               // usuarios.add(new Usuario(nombre, edad));
            }
        } catch (Exception e) {
            System.err.println("Error al obtener usuarios.");
            e.printStackTrace();
        }
        return usuarios;
    }

    public static boolean insertarEquipo(Equipo equipo) {
        String sql = "INSERT INTO equipos (idEquipo, marca, modelo, descripcion, estado) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = conectar();
             var pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, equipo.getIdEquipo());
            pstmt.setString(2, equipo.getMarca());
            pstmt.setString(3, equipo.getModelo());
            pstmt.setString(4, equipo.getDescripcion());
            pstmt.setString(5, equipo.getEstado().toString()); // Guarda el estado como string (UTILIZABLE, INUTILIZABLE)
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Error al insertar equipo.");
            e.printStackTrace();
            return false;
        }
    }

    public static List<Equipo> obtenerEquipos() {
        List<Equipo> equipos = new ArrayList<>();
        String sql = "SELECT * FROM equipos";

        try (Connection conn = conectar();
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String idEquipo = rs.getString("idEquipo");
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                String descripcion = rs.getString("descripcion");
                String estado = rs.getString("estado");
                equipos.add(new Equipo(idEquipo, marca, modelo, descripcion, EstadoEquipo.valueOf(estado)));
            }
        } catch (Exception e) {
            System.err.println("Error al obtener equipos.");
            e.printStackTrace();
        }
        return equipos;
    }

    public static boolean actualizarEstadoEquipo(Equipo equipo) {
        String sql = "UPDATE equipos SET estado = ? WHERE idEquipo = ?";

        try (Connection conn = conectar();
             var pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, equipo.getEstado().toString());
            pstmt.setString(2, equipo.getIdEquipo());
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Error al actualizar el estado del equipo.");
            e.printStackTrace();
            return false;
        }
    }

    public static boolean actualizarPrestamoEquipo(Equipo equipo) {
        String sql = "UPDATE equipos SET prestamo = ? WHERE idEquipo = ?";

        try (Connection conn = conectar();
             var pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, equipo.getPrestamo() != null ? equipo.getPrestamo().toString() : null);
            pstmt.setString(2, equipo.getIdEquipo());
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Error al actualizar el préstamo del equipo.");
            e.printStackTrace();
            return false;
        }
    }

    public static Equipo obtenerEquipoPorId(String idEquipo) {
        String sql = "SELECT * FROM equipos WHERE idEquipo = ?";
        Equipo equipo = null;

        try (Connection conn = conectar();
             var pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, idEquipo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String marca = rs.getString("marca");
                    String modelo = rs.getString("modelo");
                    String descripcion = rs.getString("descripcion");
                    String estado = rs.getString("estado");
                    equipo = new Equipo(idEquipo, marca, modelo, descripcion, EstadoEquipo.valueOf(estado));
                }
            }
        } catch (Exception e) {
            System.err.println("Error al obtener el equipo.");
            e.printStackTrace();
        }
        return equipo;
    }
}
