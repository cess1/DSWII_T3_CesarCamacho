package pe.edu.cibertec.DSWII_T3_CESAR_CAMACHO.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.DSWII_T3_CESAR_CAMACHO.model.bd.Publicacion;
import pe.edu.cibertec.DSWII_T3_CESAR_CAMACHO.model.dto.PublicacionDTO;
import pe.edu.cibertec.DSWII_T3_CESAR_CAMACHO.service.IPublicacionService;
import pe.edu.cibertec.DSWII_T3_CESAR_CAMACHO.util.DtoUtil;


import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("pubs-dto")
public class PublicacionesController {
    private final IPublicacionService publicacionService;

    @PreAuthorize("hasRole('COORDINADOR')")
    @GetMapping
    public ResponseEntity<List<Publicacion>> listarPublicaciones() {
        List<Publicacion> publicacionList = publicacionService.obtenerPublicacion();
        if (publicacionList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(publicacionList, HttpStatus.OK);
    }

    @GetMapping("/dto")
    public ResponseEntity<List<PublicacionDTO>> listarPublicacionesDto() {
        List<PublicacionDTO> publicacionDtoList = publicacionService.obtenerPublicacion()
                .stream()
                .map(publicacion -> (PublicacionDTO) new DtoUtil().convertirADto(publicacion, new PublicacionDTO()))
                .collect(Collectors.toList());
        if (publicacionDtoList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(publicacionDtoList, HttpStatus.OK);
    }
}
