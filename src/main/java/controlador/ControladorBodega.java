package controlador;

import modelo.BaseDeDatos;
import modelo.Equipo;
import modelo.EstadoEquipo;
import modelo.Prestamo;

import java.util.HashMap;
import java.util.List;

public class ControladorBodega {
    private static final ControladorBodega INSTANCE = new ControladorBodega();
    private HashMap<String, Equipo> equipos = new HashMap<>();

    private ControladorBodega() {
        cargarEquiposDesdeBaseDeDatos();
    }

    public static ControladorBodega getInstance() {
        return INSTANCE;
    }


    private void cargarEquiposDesdeBaseDeDatos() {
        List<Equipo> listaEquipos = BaseDeDatos.obtenerEquipos();
        for (Equipo equipo : listaEquipos) {
            equipos.put(equipo.getIdEquipo(), equipo);
        }
    }

    public void agregarEquipo(String idEquipo, String marca, String modelo, String descripcion, EstadoEquipo estado) {
        Equipo nuevoEquipo = new Equipo(idEquipo, marca, modelo, descripcion, estado);
        boolean exito = BaseDeDatos.insertarEquipo(nuevoEquipo); // Insertar en la base de datos
        if (exito) {
            equipos.put(idEquipo, nuevoEquipo); // Agregar a la memoria
            System.out.println("Equipo agregado exitosamente.");
        } else {
            System.err.println("Error al agregar el equipo.");
        }
    }

    // Obtener todos los equipos desde la memoria
    public List<Equipo> obtenerEquipos() {
        return BaseDeDatos.obtenerEquipos();
    }

    public void cambiarEstadoEquipo(String idEquipo) {
        Equipo equipo = equipos.get(idEquipo);
        if (equipo != null) {
            equipo.cambiarEstado();
            // Actualizar el estado en la base de datos
            BaseDeDatos.actualizarEstadoEquipo(equipo);
            System.out.println("Estado del equipo " + idEquipo + " cambiado.");
        } else {
            System.err.println("Equipo no encontrado.");
        }
    }

    public void asignarPrestamo(String idEquipo, Prestamo prestamo) {
        Equipo equipo = equipos.get(idEquipo);
        if (equipo != null) {
            equipo.setPrestamo(prestamo);
            // Actualizar el préstamo en la base de datos
            BaseDeDatos.actualizarPrestamoEquipo(equipo);
            System.out.println("Préstamo asignado al equipo " + idEquipo + ".");
        } else {
            System.err.println("Equipo no encontrado.");
        }
    }
    public Equipo obtenerEquipoPorId(String idEquipo) {
        return equipos.get(idEquipo);
    }
}
