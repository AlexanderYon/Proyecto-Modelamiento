package modelo;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Prestamo> prestamos = new ArrayList<>();

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    public void agregarPrestamo(Prestamo prestamo) {
        this.prestamos.add(prestamo);
    }

    // Métodos getter y setter
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }
}
