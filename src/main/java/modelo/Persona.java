package modelo;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;

@MappedSuperclass
public abstract class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Embedded
    private Rut rut;

    private LocalDate fechaNacimiento;

    private String nroTelefono; // no es final porque puede cambiar

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "persona_roles",
            joinColumns = @JoinColumn(name = "persona_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )

    private HashSet<Rol> roles = new HashSet<>();

    // Constructor
    public Persona(String nombre, Rut rut, LocalDate fechaNacimiento, String nroTelefono) {
        this.nombre = nombre;
        this.rut = rut;
        this.fechaNacimiento = fechaNacimiento;
        this.nroTelefono = nroTelefono;
        this.roles = new HashSet<>(2);
    }

    // Métodos getter y setter
    public String getNombre() {
        return nombre;
    }

    public Rut getRut() {
        return rut;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getNroTelefono() {
        return nroTelefono;
    }

    public void setNroTelefono(String nroTelefono) {
        this.nroTelefono = nroTelefono;
    }

    public HashSet<Rol> getRoles() {
        return roles;
    }

    public void setRol(Rol rol) {
        this.roles.add(rol);
    }

    public boolean esProfesor() {
        return this.roles.contains(Rol.PROFESOR);
    }

    public boolean esEstudiante() {
        return this.roles.contains(Rol.ESTUDIANTE);
    }
}
