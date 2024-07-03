package pe.edu.cibertec.DSWII_T3_CESAR_CAMACHO.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DSWII_T3_CESAR_CAMACHO.model.bd.Publicacion;
import pe.edu.cibertec.DSWII_T3_CESAR_CAMACHO.repository.PublicacionRepository;

import java.util.List;

@Service
public class PublicacionService implements IPublicacionService{

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Override
    public List<Publicacion> obtenerPublicacion() {
        return publicacionRepository.findAll();
    }
}
