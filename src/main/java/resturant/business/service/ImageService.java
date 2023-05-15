package resturant.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import resturant.business.entity.Image;
import resturant.business.repository.ImageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;

    public List<Image> getFotos() {
        return imageRepository.findAll();
    }
    public Optional<Image> getFotoById(Integer id) {
        return imageRepository.findById(id);
    }
    public ResponseEntity<Image> putFoto(Image image, Integer fotoId) {
        if (doesFotoExist(fotoId)) {
            image.setId(fotoId);
            Image updatedImage = imageRepository.save(image);
            return new ResponseEntity<>(updatedImage, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Image> deleteFoto(Integer fotoId) {
        Optional<Image> optionalFoto = imageRepository.findById(fotoId);
        if (doesFotoExist(fotoId)) {
            Image deleteImage = optionalFoto.get();
            imageRepository.deleteById(fotoId);
            return new ResponseEntity<>(deleteImage, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<Image> putFotos(List<Image> images) {
        for (Image image : images) {
            if (doesFotoExist(image)) imageRepository.save(image);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    public ResponseEntity<Image> postFoto(Image image) {
        if (doesFotoExist(image)) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        Image postedImage = imageRepository.save(image);
        return new ResponseEntity<>(postedImage, HttpStatus.OK);
    }
    private boolean doesFotoExist(Image image) {
        Integer fotoId = image.getId();
        return doesFotoExist(fotoId);
    }

    private boolean doesFotoExist(Integer id) {
        boolean isIdSet = id != null;
        return isIdSet && imageRepository.existsById(id);
    }
}
