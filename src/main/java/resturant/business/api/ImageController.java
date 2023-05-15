package resturant.business.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import resturant.business.entity.Image;
import resturant.business.service.ImageService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/image")
@CrossOrigin("*")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImageById(@PathVariable Integer id) {
        Optional<Image> foto = imageService.getFotoById(id);
        if (foto.isPresent()) {
            Image foundImage = foto.get();

            // Build the response headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // or MediaType.IMAGE_PNG
            headers.setContentLength(foundImage.getData().length);
            headers.set("foto-id", String.valueOf(foundImage.getId())); // Add the foto id to headers

            return new ResponseEntity<>(foundImage.getData(), headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Image> postImage(@RequestBody Image image) {
        return imageService.postFoto(image);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Image> putImage(@PathVariable Integer id, @RequestBody Image image) {
        return imageService.putFoto(image, id);
    }

    // put multiple items
    @PutMapping
    public ResponseEntity<Image> putImages(@RequestBody List<Image> images) {
        return imageService.putFotos(images);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Image> deleteImage(@PathVariable Integer id) {
        return imageService.deleteFoto(id);
    }
}

