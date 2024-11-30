package controlador;

import modelo.BaseDeDatos;
import modelo.Persona;
import modelo.Rut;
import modelo.Usuario;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.HashMap;
import java.util.List;

public class ControladorPersonas {
    private final static ControladorPersonas INSTANCE = new ControladorPersonas();
    private Persona encargado; // El encargado actual del laboratorio
    private final HashMap<Rut, Persona> personas = new HashMap<>();

    public static void registrarUsuario(String nombre, String rutString, String fechaNacimiento, String nroTelefono) {
        try {
            Rut rut = Rut.valueOf(rutString);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-mm-yyyy");
            LocalDate fecha = LocalDate.parse(fechaNacimiento, formatter);

            /*Usuario nuevoUsuario = new Usuario(nombre, rut, fecha, nroTelefono);

            BaseDeDatos.insertarUsuario(nuevoUsuario); TIRA ERROR*/

        } catch (IllegalAccessException e) {
            System.err.println("Error al crear el RUT: " + rutString);
            e.printStackTrace();
        }
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = BaseDeDatos.obtenerUsuarios();
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
        return usuarios;
    }
}
