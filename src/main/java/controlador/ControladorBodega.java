package controlador;

import modelo.Equipo;

import java.util.HashMap;

public class ControladorBodega {
    private static final ControladorBodega INSTANCE = new ControladorBodega();
    private HashMap<String, Equipo> equipo = new HashMap<>(); // equipos identificados por su id

}
