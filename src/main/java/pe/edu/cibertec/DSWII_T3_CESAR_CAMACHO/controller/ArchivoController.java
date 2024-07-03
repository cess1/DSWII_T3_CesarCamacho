package pe.edu.cibertec.DSWII_T3_CESAR_CAMACHO.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@AllArgsConstructor
@RestController
@RequestMapping("filesimages")
public class ArchivoController {

    private static final String UPLOAD_DIR = "Images";

    @PreAuthorize("hasRole('GESTOR')")
    @PostMapping
    public ResponseEntity<String> subirArchivo(@RequestParam("archivo") MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (!fileName.endsWith(".png")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Solo se aceptan imagenes: Solo .png");
        }

        try {
            Path uploadPath = Paths.get(System.getProperty("user.dir"), UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(fileName);
            file.transferTo(filePath.toFile());

            return ResponseEntity.status(HttpStatus.OK).body("El se cargo con exito: " + fileName);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se cargo el archivo, error: " + e.getMessage());
        }
    }
}
