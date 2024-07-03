package pe.edu.cibertec.DSWII_T3_CESAR_CAMACHO.model.bd;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmpleado;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String fechaContrat;

    @ManyToOne
    @JoinColumn(name = "idDomicilio", nullable = false)
    private Domicilio domicilio;

}
