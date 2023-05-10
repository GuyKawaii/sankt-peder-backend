package resturant.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import resturant.business.entity.Image;
import resturant.business.repository.FotoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FotoService {

    @Autowired
    FotoRepository fotoRepository;

    public List<Image> getFotos() {
        return fotoRepository.findAll();
    }
    public Optional<Image> getFotoById(Integer id) {
        return fotoRepository.findById(id);
    }
    public ResponseEntity<Image> putFoto(Image image, Integer fotoId) {
        if (doesFotoExist(fotoId)) {
            image.setId(fotoId);
            Image updatedImage = fotoRepository.save(image);
            return new ResponseEntity<>(updatedImage, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Image> deleteFoto(Integer fotoId) {
        Optional<Image> optionalFoto = fotoRepository.findById(fotoId);
        if (doesFotoExist(fotoId)) {
            Image deleteImage = optionalFoto.get();
            fotoRepository.deleteById(fotoId);
            return new ResponseEntity<>(deleteImage, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<Image> putFotos(List<Image> images) {
        for (Image image : images) {
            if (doesFotoExist(image)) fotoRepository.save(image);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    public ResponseEntity<Image> postFoto(Image image) {
        if (doesFotoExist(image)) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        Image postedImage = fotoRepository.save(image);
        return new ResponseEntity<>(postedImage, HttpStatus.OK);
    }
    private boolean doesFotoExist(Image image) {
        Integer fotoId = image.getId();
        return doesFotoExist(fotoId);
    }

    private boolean doesFotoExist(Integer id) {
        boolean isIdSet = id != null;
        return isIdSet && fotoRepository.existsById(id);
    }
}
