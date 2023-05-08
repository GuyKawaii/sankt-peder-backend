package resturant.business.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import resturant.business.dto.FotoDetails;
import resturant.business.entity.Foto;
import resturant.business.entity.MenuItem;
import resturant.business.service.FotoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(value = "*")
public class FotoController {

    @Autowired
    FotoService fotoService;

    @GetMapping("/fotos")
    public List<Foto> getFotos() {
        return fotoService.getFotos();
    }

    @GetMapping("/fotos/{id}")
    public ResponseEntity<byte[]> getFotoById(@PathVariable Integer id) {
        Optional<Foto> foto = fotoService.getFotoById(id);
        if (foto.isPresent()) {
            Foto foundFoto = foto.get();

            // Build the response headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG
            headers.setContentLength(foundFoto.getData().length);
            headers.set("foto-id", String.valueOf(foundFoto.getId())); // Add the foto id to headers

            return new ResponseEntity<>(foundFoto.getData(), headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/foto")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Foto> postFoto(@RequestBody Foto foto) {
        return fotoService.postFoto(foto);
    }

    @PutMapping("/foto/{id}")
    public ResponseEntity<Foto> putItem(@PathVariable Integer id, @RequestBody Foto foto) {
        return fotoService.putFoto(foto, id);
    }

    // put multiple items
    @PutMapping("/foto")
    public ResponseEntity<Foto> putItems(@RequestBody List<Foto> fotos) {
        return fotoService.putFotos(fotos);
    }



    @DeleteMapping("/foto/{id}")
    public ResponseEntity<Foto> deleteShop(@PathVariable Integer id) {
        return fotoService.deleteFoto(id);
    }

}
