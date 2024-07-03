package pe.edu.cibertec.DSWII_T3_CESAR_CAMACHO.model.bd;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAutor;

    @Column(nullable = false)
    private String nomAutor;

    @Column(nullable = false)
    private String apeAutor;

    @Column(nullable = false)
    private String fechNacAutor;

}